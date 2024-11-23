package com.app.my_tracker.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public String getPingCall() {
        return "SUCCESS";
    }


}