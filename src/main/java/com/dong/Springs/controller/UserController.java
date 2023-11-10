package com.dong.Springs.controller;

import com.dong.Springs.entity.User;
import com.dong.Springs.repository.UserRepository;
import com.dong.Springs.service.UserService;
import com.sun.jdi.PrimitiveValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController  {

    @Autowired
    private UserService userService;

    @GetMapping("/livre")
    public String rotaLivre(){
        return "<h1>ROTA LIVRE</h1>";
    }

    @PostMapping("/sdsafdss")
    public String rotelivreteste(){
        return "<h1>ROTA LIVRE TAMBEM</h1>";
    }

    @GetMapping("/userAuth")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String rotaUser(){
        return "<h1>ROTA DE USUSRIO AUTENTICADO</h1>";
    }

    @GetMapping("/adminAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String rotaAdmin(){
        return "<h1>ROTA DE USUSRIO AUTENTICADO</h1>";
    }


    @PostMapping("/register")
    public ResponseEntity<HttpStatus> cadastrarUser(@RequestBody User user){
        try{
            userService.registrar(user);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
