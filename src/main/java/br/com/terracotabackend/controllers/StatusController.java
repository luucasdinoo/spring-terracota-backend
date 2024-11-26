package br.com.terracotabackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class StatusController {

    @GetMapping
    public String statusController() {
        return "OK";
    }
}
