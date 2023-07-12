package com.kayaspring.kayaspring.api.controllers;

import com.kayaspring.kayaspring.business.services.CategoryService;
import com.kayaspring.kayaspring.common.GenericResultClass;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@RequestMapping("api/Home")
public class HomeController {

    private final CategoryService service;

    public HomeController(CategoryService service) {
        this.service = service;
    }


    @GetMapping("getAll")
    public GenericResultClass getAll() {
        return service.getAll();
    }

    @GetMapping("getAllAsync")
    public CompletableFuture<GenericResultClass> getAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            return service.getAll();
        });
    }


}
