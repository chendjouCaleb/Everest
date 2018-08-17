package org.everest.mvc.binding;

public interface IConverter<Target, Source> {
    Target convert(Source object);
}
