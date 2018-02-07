package org.everest.test.formTest;

import annotation.HttpController;
import annotation.Path;
import filter.Authentication;
import filter.Debug;
import org.everest.main.component.http.Controller;

@HttpController(prefix = "user")
public class UserController extends Controller{

    @Path(route = "/:id", name = "id")
    public void get(String id){
        System.out.println("GTE USER " + id);
    }

    @Authentication
    @Debug
    @Path(route = "/")
    public void getAll(){
        System.out.println("GET ALL USERS");
    }

    @Override
    public void init() {

    }
}
