package org.everest.test.formTest;

import org.everest.mvc.httpContext.Controller;
import org.everest.mvc.httpContext.decorator.HttpController;
import org.everest.mvc.httpContext.decorator.Path;

@HttpController(prefix = "user")
public class UserController extends Controller {

    @Path(route = "/:id", name = "id")
    public void get(String id){
        System.out.println("GTE USER " + id);
    }

    @Path(route = "/")
    public void getAll(){
        System.out.println("GET ALL USERS");
    }

    public void init() {

    }
}
