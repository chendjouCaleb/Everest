package org.everest.test.formTest;

import org.junit.Test;
import router.Router;

public class RouterTest {
    Class<?> controllers[] = {UserController.class, PostController.class};

    @Test
    public void testRouter(){
        /*Router router = new Router(controllers);
        try {
            router.run("/5/5/6", "GET");
            System.out.println(router.url("id", "5"));
            System.out.println(router.url("post", "5", "slug", "6"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*Package[] packages = Package.getPackages();
        for (Package p: packages){
            System.out.println(p.getName());
        }*/
    }
}
