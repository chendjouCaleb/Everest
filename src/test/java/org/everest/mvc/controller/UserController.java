package org.everest.mvc.controller;

import org.everest.mvc.decorator.*;
import org.everest.mvc.httpContext.HttpMethod;
import org.everest.mvc.httpContext.decorator.HttpController;

@HttpMapping("/user")
@HttpController
public class UserController {

    @HttpMapping("/{id}")
    public void Get(Integer id){

    }

    @GetMapping("/{id}")
    public void GetById(Integer id){

    }


    public void doNothing(){

    }

    @PostMapping
    public void Add(){

    }

    @PutMapping
    public void update(){

    }

    @DeleteMapping
    public void delete(){

    }

    @PathMapping
    public void path(){

    }

    @OptionsMapping
    public void options(){

    }
}
