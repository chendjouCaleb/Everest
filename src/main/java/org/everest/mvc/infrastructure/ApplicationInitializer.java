package org.everest.mvc.infrastructure;

import org.everest.mvc.component.servlet.MultiPartConfig;


public abstract class ApplicationInitializer {
    public abstract String[] getBasePackages();

    public abstract String getAppBaseUrl();

    public String getWebAppPath(){
        return "/src/main/webapp";
    }

    public String getViewFolderPath(){
        return "/WEB-INF/templates/";
    }

    public String getProjectBaseDir(){
        return System.getProperty("user.dir");
    }

    public String[] getStaticResourceFolders(){
        return new String[] {"/dist/"};
    }

    public abstract MultiPartConfig multiPartConfig();
}
