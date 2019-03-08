package Everest.Http;

import org.everest.utils.StringUtils;

import javax.annotation.Nonnull;
import java.util.*;

public class FormCollection implements Map<String, List<String>>{
    private Map<String, List<String>> collections = new HashMap<>();

    public int size() {
        return collections.size();
    }

    @Override
    public boolean isEmpty() {
        return collections.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return collections.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return collections.containsValue(value);
    }

    @Override
    public List<String> get(Object key) {
        return collections.get(key);
    }

    @Override
    public List<String> put(String key, List<String> value) {
        return collections.put(key, value);
    }

    public void append(@Nonnull String key, String value){
        if(StringUtils.isEmpty(key)){
            throw new IllegalArgumentException("Cannot add null or empty key to queryList");
        }
        if(containsKey(key)){
            get(key).add(value);
        }else {
            put(key,new ArrayList<>());
            get(key).add(value);
        }
    }

    @Override
    public List<String> remove(Object key) {
        return collections.remove(key);
    }

    @Override
    public void putAll(@Nonnull Map<? extends String, ? extends List<String>> m) {
        collections.putAll(m);
    }

    @Override
    public void clear() {
        collections.clear();
    }

    @Override
    @Nonnull
    public Set<String> keySet() {
        return collections.keySet();
    }

    @Nonnull
    public Collection<List<String>> values() {
        return collections.values();
    }

    @Nonnull
    public Set<Map.Entry<String, List<String>>> entrySet() {
        return collections.entrySet();
    }
}
