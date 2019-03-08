package Everest.Http;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import Everest.Core.Exception.NullArgumentException;
import org.everest.exception.NotSupportedEncodingException;

import javax.annotation.Nonnull;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Chendjou DeGrace
 * Provides correct handling for QueryString value when receive http HttpRequest or
 * needed to reconstruct a request or redirect URI string
 */
public class QueryString {
    private String value;
    public static final QueryString Empty = new QueryString("");
    private static final String UTF_8_ENCODING = "UTF-8";

    /**
     * Initialize the query string with a given value. This value must be in escaped and delimited format with
     * a leading '?' character.
     */
    public QueryString() {
        this(StringUtils.EMPTY);
    }

    /**
     * Initialize the query string with a given value. This value must be in escaped and delimited format with
     * a leading '?' character.
     * @param value The query string to be assigned to the Value property.
     */
    public QueryString(@Nonnull String value){
        if(!StringUtils.isEmpty(value) && value.charAt(0) != '?'){
            throw new IllegalArgumentException("The leading '?' must be included for a non-empty query.");
        }
        this.value = value;
    }

    /**
     *
     * @return The escaped query string with the leading '?' character
     */
    public String value(){
        return  value;
    }

    /**
     * @return True if the query string is not empty
     */
    public boolean hasValue(){
        return StringUtils.isNotEmpty(value);
    }




    /**
     * Create a query string with a single given parameter name and value.
     * @param name The un-encoded parameter name
     * @param value The un-encoded parameter value
     * @return The resulting QueryString
     */
    public static QueryString create(String name, String value){
        if(name == null){
            throw new NullArgumentException("name");
        }
        if(value==null){
            value = StringUtils.EMPTY;
        }

        if(StringUtils.isNotEmpty(value)){
            try {
                value = URLEncoder.encode(value, UTF_8_ENCODING);
                name = URLEncoder.encode(name, UTF_8_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return new QueryString("?" + name + "=" + value);
    }

    /**
     * Returns an QueryString given the query as from a Uri object. Relative Uri objects are not supported.
     * @param uri The Uri object
     * @return The resulting QueryString
     */
    public static QueryString fromUri(URI uri){
        if(uri == null){
            throw new IllegalArgumentException("The URI must be not null value");
        }

        String queryValue = uri.getQuery();

        if(!StringUtils.isNotEmpty(queryValue)){
            queryValue = "?" + queryValue;
        }
        return new QueryString(queryValue);
    }

    /**
     * Get the queryString without The leading '?' at beginning
     * @return queryString without The leading '?' at beginning
     */
    public String segment(){
        if(!hasValue()){
            return StringUtils.EMPTY;
        }
        return this.value.substring(1);
    }

    /**
     * Returns an QueryString given the query as it is escaped in the URI format. The string MUST NOT contain any
     * value that is not a query.
     * @param uri The escaped query as it appears in the URI format.
     * @return The resulting QueryString
     */
    public static QueryString fromURI(String uri){
        if (StringUtils.isEmpty(uri))
        {
            return new QueryString(StringUtils.EMPTY);
        }
        return new QueryString(uri);
    }

    /**
     * Creates a query string composed from the given name value pairs.
     * @param parameters A map that contains all the key-value of a queryString
     * @return The resulting QueryString
     */
    public static QueryString createFromMap(Map<String, String> parameters){
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> pair: parameters.entrySet()){
            System.out.println("key="+pair.getKey()+";value"+pair.getValue());
            AppendKeyValuePair(builder, pair.getKey(), pair.getValue(), first);
            first = false;
        }

        return new QueryString(builder.toString());
    }

    /**
     * Creates a query string composed from the given name value pairs where pairs can be an array.
     * @param parameters A map that contains all the key-value of a queryString
     * @return The resulting QueryString
     */
    public static QueryString createFromArrayMap(Map<String, String[]> parameters){
        StringBuilder builder = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String[]> pair : parameters.entrySet())
        {
            // If nothing in this pair.Values, append null value and continue
            if (ArrayUtils.isEmpty(pair.getValue()))
            {
                AppendKeyValuePair(builder, pair.getKey(), null, first);
                first = false;
                continue;
            }
            // Otherwise, loop through values in pair.Value
            for (String value : pair.getValue())
            {
                AppendKeyValuePair(builder, pair.getKey(), value, first);
                first = false;
            }
        }

        return new QueryString(builder.toString());
    }

    /**
     * Add QueryString to other QueryString
     * @param other The other QueryString
     * @return A new QueryString which is the addition of two QueryString
     */
    public QueryString add(QueryString other)
    {
        if (!hasValue() || value.equals("?"))
        {
            return other;
        }
        if (!other.hasValue() || other.value().equals("?"))
        {
            return this;
        }

        // ?name1=value1 Add ?name2=value2 returns ?name1=value1&name2=value2
        return new QueryString(value + "&" + other.value.substring(1));
    }

    /**
     * Add the name-value pair to the queryString
     * @param name The name of a parameter
     * @param value The value of a parameter
     * @return A new QueryString with the added name-value pair
     */
    public QueryString add(String name, Object value) {
        if(name == null){
            throw new NullArgumentException("name");
        }
        if(value == null){
            value = "";
        }

        if (!hasValue() || value.equals("?"))
        {
            return create(name, value.toString());
        }

        StringBuilder builder = new StringBuilder(value());
        AppendKeyValuePair(builder, name, value.toString(), false);
        return new QueryString(builder.toString());
    }


    /**
     * Provides the query string escaped in a way which is correct for combining into the URI representation.
     *  A leading '?' character will be included unless the Value is null or empty. Characters which are potentially
     * dangerous are escaped.
     * @return The query string value
     */
    public String toURI(){
        return hasValue()? value.replace("#", "%23"):StringUtils.EMPTY;
    }

    /**
     * Provides the query string escaped in a way which is correct for combining into the URI representation.
     * A leading '?' character will be included unless the Value is null or empty. Characters which are potentially
     *  dangerous are escaped.
     * @return The query string value
     */
    public String toString() {
        return toURI();
    }

    public boolean equals(QueryString other){
        if (!hasValue() && !other.hasValue())
        {
            return true;
        }
        return StringUtils.equals(value, other.value);
    }


    public boolean equals(Object other) {
        if(other == null || !other.getClass().equals(QueryString.class)) {
            return false;
        }
        return equals((QueryString)other);
    }

    @Override
    public int hashCode() {
        return (hasValue() ? value.hashCode() : 0);
    }

    private static void AppendKeyValuePair(StringBuilder builder, String key, String value, boolean first)
    {
        builder.append(first ? "?" : "");
        builder.append((!first && builder.charAt(builder.length()-1) != '?') ? '&': "");
        try {
            builder.append(URLEncoder.encode(key, UTF_8_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new NotSupportedEncodingException(e);
        }
        builder.append("=");
        if (StringUtils.isNotEmpty(value))
        {
            try {
                builder.append(URLEncoder.encode(value, UTF_8_ENCODING));
            } catch (UnsupportedEncodingException e) {
                throw new NotSupportedEncodingException(e);
            }
        }
    }
}
