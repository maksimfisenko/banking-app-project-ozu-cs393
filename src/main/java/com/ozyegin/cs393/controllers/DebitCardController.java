package com.ozyegin.cs393.controllers;

import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.services.DebitCardService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    //Service 7
    @PostMapping("/{accountNumber}/openNew")
    public ResponseEntity<DebitCardDTO> openDebitCard(@PathVariable Long accountNumber,
                                                      @RequestBody String cardName){
        DebitCardDTO createdDebitCard = debitCardService.openDebitCard(accountNumber, cardName);
        if (createdDebitCard == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(createdDebitCard, HttpStatus.CREATED);
    }

    //Service 8
    @PutMapping("/{amount}/pay")
    public ResponseEntity<Void> makePayment(@RequestBody DebitCardDTO debitCardDTO,
                                            @RequestBody AccountDTO receivingAccount,
                                            @PathVariable double amount){
        boolean res = debitCardService.makePayment(debitCardDTO, receivingAccount, amount);
        if (!res)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    //Service 10
    @GetMapping("/paymentsByDate")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByDates(@RequestBody DebitCardDTO debitCardDTO,
                                                               @RequestBody LocalDate startDate,
                                                               @RequestBody LocalDate endDate){
        List<PaymentDTO> paymentDTOS = debitCardService.getPaymentsByDates(debitCardDTO, startDate, endDate);
        if (paymentDTOS.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(paymentDTOS, HttpStatus.OK);
    }

}
