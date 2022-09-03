package com.diego.supermercado;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @RequestMapping("/hola")
    public String hola(){
        return "nunca pares de aprender 🐱‍👤";
    }
}
