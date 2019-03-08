package org.everest.test.webApplication;

import Everest.Middleware.MiddlewareRegister;
import org.everest.mvc.http.CORSConfig;
import org.everest.mvc.infrastructure.MvcStartup;
import org.everest.mvc.component.MultiPartConfig;

public class AppInitializer extends MvcStartup {
    @Override
    public String[] getBasePackages() {
        return new String[]{
                "org.everest.test.webApplication.controller",
                "org.everest.test.webApplication.listener",
                "org.everest.test.webApplication.errorhandler"
        };
    }

    @Override
    public CORSConfig corsConfig() {
        return null;
    }

    @Override
    public void setMiddlewareChain(MiddlewareRegister register) {

    }

    @Override
    public String getAppBaseUrl() {
        return "/";
    }

    @Override
    public MultiPartConfig multiPartConfig() {
        return null;
    }
}
