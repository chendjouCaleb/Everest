package org.everest.mvc.infrastructure;

import org.everest.context.ApplicationContext;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.mvc.component.MultiPartConfig;
import org.everest.mvc.http.CORSConfig;
import org.everest.mvc.renderer.ThymeleafViewRenderer;
import org.everest.mvc.renderer.ViewRenderer;


public abstract class ApplicationInitializer {

    @AutoWired private ApplicationContext applicationContext;
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

    public ViewRenderer viewRenderer(){
        return new ThymeleafViewRenderer(applicationContext);
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public abstract CORSConfig corsConfig();
}
