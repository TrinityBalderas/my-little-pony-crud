package com.mlp.trinity.demo.controller;

import com.mlp.trinity.demo.exception.PonyNameAlreadyExists;
import com.mlp.trinity.demo.exception.PonyNameLengthEception;
import com.mlp.trinity.demo.exception.PonyNotFoundException;
import com.mlp.trinity.demo.models.PonyModel;
import com.mlp.trinity.demo.response.PonyResponse;
import com.mlp.trinity.demo.service.PonyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pony")
public class PonyController {

    private PonyService ponyService;
    public PonyController(PonyService ponyService){
        this.ponyService = ponyService;
    }

    @GetMapping
    public List<PonyModel> getAllPonys(){
        return ponyService.getAllPonys();
    }
    @GetMapping("{id}")
    public ResponseEntity<PonyModel> getPontById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(ponyService.getPonyById(id));
        } catch (PonyNotFoundException e){
            return new ResponseEntity(new PonyResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
     @PostMapping
    public ResponseEntity<PonyModel> createPony(@RequestBody PonyModel body) {
         try{
             return ResponseEntity.ok(ponyService.createPony(body));
         } catch(PonyNameAlreadyExists e) {
             return new ResponseEntity(new PonyResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
         } catch (PonyNameLengthEception e){
             return new ResponseEntity(new PonyResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
         }
     }
     @PutMapping("{id}")
    public ResponseEntity updatePony(@PathVariable Long id, @RequestBody PonyModel body){
        try{
            return ResponseEntity.ok(ponyService.updatePony(id, body));
        } catch (PonyNotFoundException e){
            return new ResponseEntity(new PonyResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
         }
     }
     @DeleteMapping("{id}")
    public ResponseEntity<PonyResponse> deletePony(@PathVariable Long id){
        ponyService.deletePony(id);
        return ResponseEntity.ok(new PonyResponse(null, "Pony " + id + " was deleted."));
     }
}