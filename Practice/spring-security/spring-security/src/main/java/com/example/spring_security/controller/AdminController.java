//package com.example.spring_security.controller;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/admin")
//@PreAuthorize("hasRole('ADMIN')")
//@Tag(name = "Admin")
//public class AdminController  {
//
//    @PostMapping("/post")
//    @PreAuthorize("hasAuthority('admin:create')")
//    public String post() {
//        return "POST:: admin controller";
//    }
//
//    @GetMapping("/get")
//    @PreAuthorize("hasAuthority('admin:read')")
//    public String get() {
//        return "GET:: admin controller";
//    }
//
//    @PutMapping("/put")
//    @PreAuthorize("hasAuthority('admin:update')")
//    public String put() {
//        return "PUT:: admin controller";
//    }
//
//    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('admin:delete')")
//    public String delete() {
//        return "DELETE:: admin controller";
//    }
//}
