package com.ozyegin.cs393.controllers;

import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.services.DebitCardService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/debitCards")
public class DebitCardController {

    @Autowired
    private DebitCardService debitCardService;

    @GetMapping
    public ResponseEntity<List<DebitCardDTO>> getAllDebitCards(){

        List<DebitCardDTO> debitCardDTOS = debitCardService.getAllDebitCards();
        if (debitCardDTOS.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(debitCardDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DebitCardDTO> createDebitCard(@RequestBody DebitCardDTO debitCardDTO){

        DebitCardDTO createdDebitCard = debitCardService.createDebitCard(debitCardDTO);
        if (createdDebitCard == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(createdDebitCard, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebitCardDTO> getDebitCardById(@PathVariable Long id){
        DebitCardDTO debitCardDTO = debitCardService.getDebitCardById(id);

        if (debitCardDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         else
            return new ResponseEntity<>(debitCardDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> handlePostOnExistingDebitCard() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebitCardDTO> updateDebitCard(@RequestBody DebitCardDTO debitCardDTO){
        DebitCardDTO updatedDebitCard = debitCardService.updateDebitCard(debitCardDTO);

        if(updatedDebitCard == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(updatedDebitCard, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllDebitCards(){
        debitCardService.deleteAllDebitCards();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDebitCardById(@PathVariable Long id){
        debitCardService.deleteDebitCardById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
