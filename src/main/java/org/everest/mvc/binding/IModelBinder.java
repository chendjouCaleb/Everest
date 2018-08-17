package org.everest.mvc.binding;

import org.apache.commons.beanutils.Converter;

import java.util.List;
import java.util.Map;

/**
 * @author chendjou deGrace
 * Service permettant de convertir un dictionnaire de données en Object
 * @version 1.0
 */
public interface IModelBinder {
    /**
     *
     * @param converter object permettant de convertir une chaine
     * de charactères en un type précisé dans le convertisseur
     */
    void addConverter(IConverter converter);
    List<IConverter> getConverters();

    /**
     *
     * @param object Object déjà instancié dont les valeurs de champs seront extraites de {@param values}
     * @param values Contient les valeurs de type {@code String}
     */
    void bind(Object object, Map<String, ?> values);

    <T> T bind(Class<T> type, Map<String, ?> values);

    <T> T convert(Object object, Class<T> target);

}
