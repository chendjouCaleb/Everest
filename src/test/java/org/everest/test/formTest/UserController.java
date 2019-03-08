package org.everest.test.formTest;

import org.everest.mvc.httpContext.Controller;
public class UserController extends Controller {


    public void get(String id){
        System.out.println("GTE USER " + id);
    }


    public void getAll(){
        System.out.println("GET ALL USERS");
    }

    public void init() {

    }
}
