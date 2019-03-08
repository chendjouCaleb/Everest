package org.everest.mvc.infrastructure;

import Everest.Core.Exception.UnauthorizedException;
import Everest.Middleware.MiddlewareRegister;
import Everest.Mvc.ExceptionHandler.ExceptionStatusCodeGetter;
import org.everest.context.ApplicationContext;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.mvc.component.MultiPartConfig;
import org.everest.mvc.http.CORSConfig;

import java.util.NoSuchElementException;


public abstract class MvcStartup {

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


    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public abstract CORSConfig corsConfig();

    public abstract void setMiddlewareChain(MiddlewareRegister register);

    public void setExceptionStatusCode(ExceptionStatusCodeGetter statusCodes){
        statusCodes.put(NoSuchElementException.class, 404);
        statusCodes.put(UnauthorizedException.class, 404);
    }
}
