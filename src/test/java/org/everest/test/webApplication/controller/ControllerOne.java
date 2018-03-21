package org.everest.test.webApplication.controller;

import annotation.HttpController;
import annotation.HttpMethod;
import annotation.Path;
import org.everest.main.component.http.Controller;

@HttpController
public class ControllerOne extends Controller{
    @Path(route = "home", name = "home")
    public void home(Integer id){

    }

    @Path(route = "/edit", name = "edit", httpMethod = HttpMethod.POST)
    public void edit(){

    }
    @Path(route = "/edit/:id", name = "editIdGET")
    public void editGET(){

    }

    @Path(route = "/edit/:id", name = "editId", httpMethod = HttpMethod.POST)
    public void editWithId(){

    }
}
