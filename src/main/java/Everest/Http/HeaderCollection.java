package Everest.Http;

import Everest.Core.Exception.KeyNotFoundException;
import Everest.Core.Exception.NullArgumentException;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Represents HttpRequest and HttpResponse headers
 * @author Chendjou
 */
public class HeaderCollection implements Map<String, String> {

    private final String[] emptyKeys = new String[]{};
    private final String emptyValue = "";

    public HeaderCollection(){}

    public HeaderCollection(Map<String, String> store){
        this.store = store;
    }

    private void ensureStore(int capacity){
        store = new HashMap<>(capacity);
    }



    private Map<String, String> store = new HashMap<>();
    /**
     * * Strongly typed access to the Content-Length header.
     *  * Implementations must keep this in sync with the string representation.
     * @return The size of a content-Length
     */
    public long contentLength(){
        return Long.valueOf(get("content-length"));
    }




    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return store.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return store.containsValue(value);
    }

    @Override
    public String get(Object key) {

        if(!containsKey(key)){
            throw new KeyNotFoundException(String.format("Header with key %s not found", key));
        }
        return store.get(key);
    }

    /**
     * @param key The header name.
     * @param value The header value.
     * @return
     */
    public String put(String key, String value) {
        if(key == null){
            throw new NullPointerException("The key is null");
        }
        return store.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return store.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        store.putAll(m);
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Nonnull
    public Set<String> keySet() {
        return store.keySet();
    }

    @Nonnull
    public Collection<String> values() {
        return store.values();
    }

    @Nonnull
    public Set<Entry<String, String>> entrySet() {
        return store.entrySet();
    }
}
