package com.app.my_tracker.controller;

@RestController
@RequestMapping("/ping")
public class UserController {

    @GetMapping
    public String getPingCall() {
        return "SUCCESS";
    }


}