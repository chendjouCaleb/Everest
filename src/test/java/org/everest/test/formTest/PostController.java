package org.everest.test.formTest;

import org.everest.mvc.httpContext.Controller;
import org.everest.mvc.httpContext.decorator.HttpController;
import org.everest.mvc.httpContext.decorator.Path;

@HttpController(prefix = "")
public class PostController extends Controller {

    @Path(route = "/:gi/([0-9]+)/:slug", name = "post")
    public void getPost(String id, String slug, String id2){
        System.out.println("GET ALL POSTS: " + id);
    }

    public void init() {

    }
}
