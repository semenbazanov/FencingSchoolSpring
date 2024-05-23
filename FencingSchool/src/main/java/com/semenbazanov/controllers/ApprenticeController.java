package com.semenbazanov.controllers;

import com.semenbazanov.dto.ResponseResult;
import com.semenbazanov.model.Apprentice;
import com.semenbazanov.service.ApprenticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprentice")
public class ApprenticeController {
    private ApprenticeService apprenticeService;

    @Autowired
    public void setApprenticeService(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Apprentice>> post(@RequestBody Apprentice apprentice) {
        try {
            this.apprenticeService.add(apprentice);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseResult<List<Apprentice>>> get() {
        List<Apprentice> apprentices = this.apprenticeService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, apprentices), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> get(@PathVariable long id) {
        try {
            Apprentice apprentice = this.apprenticeService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Apprentice>> update(@RequestBody Apprentice apprentice) {
        try {
            Apprentice update = this.apprenticeService.update(apprentice);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> delete(@PathVariable long id) {
        try {
            Apprentice apprentice = this.apprenticeService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
