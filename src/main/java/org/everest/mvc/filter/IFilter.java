package org.everest.mvc.filter;

public interface IFilter<T> {
    void init(T annotation);
}
