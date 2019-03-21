package Everest.Mvc.ValueResolver.TypedResolver;

import Everest.Http.HttpContext;
import Everest.Http.ItemCollection;
import Everest.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class ItemCollectionResolver  implements ITypedValueResolver<ItemCollection> {
    @Override
    public ItemCollection getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getItems();
    }
}
