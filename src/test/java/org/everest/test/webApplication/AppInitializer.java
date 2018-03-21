package org.everest.test.webApplication;

import org.everest.main.ApplicationInitializer;
import org.everest.main.component.errorHandler.IErrorHandler;

import java.util.List;

public class AppInitializer extends ApplicationInitializer {
    @Override
    public String[] getBasePackages() {
        return new String[]{
                "org.everest.test.webApplication.controller",
                "org.everest.test.webApplication.listener",
                "org.everest.test.webApplication.errorhandler"
        };
    }

    @Override
    public String getAppBaseUrl() {
        return "/";
    }
}
