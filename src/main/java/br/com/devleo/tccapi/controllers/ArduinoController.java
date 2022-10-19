package br.com.devleo.tccapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devleo.tccapi.models.Arduino;
import br.com.devleo.tccapi.services.FBService;

@RestController
@RequestMapping("/arduino")
public class ArduinoController {

    @Autowired
    FBService firebaseService;

    @PostMapping("/send")
    public String sendDetails(@RequestBody Arduino arduino) {
        return firebaseService.save(arduino);
    }

    @GetMapping("/get")
    public Arduino getInformations(@RequestHeader() String id) {
        return firebaseService.get(id);
    }

}
