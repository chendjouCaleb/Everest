package org.everest.mvc.controller;

import org.everest.mvc.decorator.HttpMapping;

@HttpMapping("/user/{userId}/comment/{commentId}/")
public class CommentController {

    @HttpMapping("/response/{responseID}.{length}-{id}")
    public void Response(){}
}
