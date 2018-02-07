package org.everest.test.formTest;

import annotation.HttpController;
import annotation.Path;
import filter.Authentication;
import filter.Debug;
import org.everest.main.component.http.Controller;

@HttpController(prefix = "")
public class PostController extends Controller{

    @Authentication()
    @Debug(priority = 1)
    @Path(route = "/:gi/([0-9]+)/:slug", name = "post")
    public void getPost(String id, String slug, String id2){
        System.out.println("GET ALL POSTS: " + id);
    }

    @Override
    public void init() {

    }
}
