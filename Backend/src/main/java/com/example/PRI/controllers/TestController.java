package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Delete;
import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.controllers.annotations.Put;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/app")
public class TestController {

    @Get
    public String testGet(){
        return "Success";
    }

    @Post
    public String testPost(@Valid @RequestBody String data){
        System.out.println(data);
        return "Success";
    }

    @Put
    public String testPut(@Valid @RequestBody String data){
        System.out.println(data);
        return "Success";
    }

    @Delete
    public String testDelete(@Valid @RequestBody String data){
        System.out.println(data);
        return "Success";
    }




}
