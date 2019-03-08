package org.everest.test.formTest;

import org.everest.mvc.httpContext.Controller;
public class PostController extends Controller {


    public void getPost(String id, String slug, String id2){
        System.out.println("GET ALL POSTS: " + id);
    }

    public void init() {

    }
}
