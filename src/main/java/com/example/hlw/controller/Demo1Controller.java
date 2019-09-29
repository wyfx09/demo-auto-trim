package com.example.hlw.controller;

import com.example.hlw.dto.UserDto;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hlw on 2019/8/1.
 */
@RestController
@RequestMapping("demo")
public class Demo1Controller {
    @GetMapping("get/{name}")
    public String get(@PathVariable  String name){
        return name;
    }
    @GetMapping("get/int/{id}")
    public String getInt(@PathVariable Integer id){
        return id.toString();
    }

    @GetMapping("get/{name}/{code}")
    public String getString2(@PathVariable String name,@PathVariable String code){
        return name+";"+code;
    }

    @PostMapping("get/dto")
    public String getDto(@RequestBody UserDto userDto){

        return "";
     //   return new Gson().toJson(userDto);
    }
}
