package org.everest.test.webApplication;

import org.everest.mvc.infrastructure.ApplicationInitializer;
import org.everest.mvc.component.servlet.MultiPartConfig;

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

    @Override
    public MultiPartConfig multiPartConfig() {
        return null;
    }
}
