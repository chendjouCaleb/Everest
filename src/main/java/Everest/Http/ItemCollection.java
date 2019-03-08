package Everest.Http;
import org.apache.commons.collections.map.HashedMap;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemCollection implements Map<Object, Object> {
    private Map<Object, Object> items = new HashMap<>();

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return items.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return items.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return items.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return items.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return items.remove(key);
    }

    @Override
    public void putAll(@Nonnull Map<?, ?> m) {
        items.putAll(m);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    @Nonnull
    public Set<Object> keySet() {
        return items.keySet();
    }

    @Nonnull
    public Collection<Object> values() {
        return items.values();
    }

    @Nonnull
    public Set<Entry<Object, Object>> entrySet() {
        return items.entrySet();
    }
}
