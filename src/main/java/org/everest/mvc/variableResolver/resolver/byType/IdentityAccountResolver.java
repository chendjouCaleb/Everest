package org.everest.mvc.variableResolver.resolver.byType;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.identity.IdentityAccount;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class IdentityAccountResolver implements IVariableResolverByType<IdentityAccount> {

    public Class<IdentityAccount> getType() {
        return IdentityAccount.class;
    }

    public IdentityAccount getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getIdentityAccount();

    }
}
