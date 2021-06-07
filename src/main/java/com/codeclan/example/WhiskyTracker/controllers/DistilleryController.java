package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;


    @GetMapping(value = "/distilleries")
    public ResponseEntity getDistilleries(
            @RequestParam(name="region", required = false) String region
    ){
        if (region != null){
            return new ResponseEntity(distilleryRepository.findByRegion(region), HttpStatus.OK);
        }

        return new ResponseEntity(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/whiskies")
    public ResponseEntity getDistilleriesByWhisky(
            @RequestParam(name="whiskyAge", required = false) Integer age
    ) {
        return new ResponseEntity(distilleryRepository.findDistinctByWhiskiesAge(age), HttpStatus.OK);
    }

}
