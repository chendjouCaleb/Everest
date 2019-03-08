package org.everest.test.formTest;

import org.junit.jupiter.api.Test;

public class RouterTest {
    Class<?> controllers[] = {UserController.class, PostController.class};

    @Test
    public void testRouter(){
        /*Router org.everest.router = new Router(controllers);
        try {
            org.everest.router.run("/5/5/6", "GET");
            System.out.println(org.everest.router.url("id", "5"));
            System.out.println(org.everest.router.url("post", "5", "slug", "6"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*Package[] packages = Package.getPackages();
        for (Package p: packages){
            System.out.println(p.getName());
        }*/
    }
}
