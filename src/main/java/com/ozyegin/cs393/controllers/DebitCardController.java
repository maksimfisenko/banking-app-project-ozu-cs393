package com.ozyegin.cs393.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozyegin.cs393.dto.AccountDTO;
import com.ozyegin.cs393.dto.DebitCardDTO;
import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.services.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/debitCards")
public class DebitCardController {

    @Autowired
    private DebitCardService debitCardService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<DebitCardDTO>> getAllDebitCards(){

        List<DebitCardDTO> debitCardDTOs = debitCardService.getAllDebitCards();

        if (debitCardDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(debitCardDTOs, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<DebitCardDTO> createDebitCard(@RequestBody DebitCardDTO debitCardDTO){

        DebitCardDTO createdDebitCard = debitCardService.createDebitCard(debitCardDTO);

        if (createdDebitCard == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(createdDebitCard, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebitCardDTO> getDebitCardById(@PathVariable Long id){

        DebitCardDTO debitCardDTO = debitCardService.getDebitCardById(id);

        if (debitCardDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(debitCardDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> handlePostOnExistingDebitCard() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebitCardDTO> updateDebitCard(@RequestBody DebitCardDTO debitCardDTO){

        DebitCardDTO updatedDebitCard = debitCardService.updateDebitCard(debitCardDTO);

        if (updatedDebitCard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedDebitCard, HttpStatus.OK);
        }
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

    //Service 7: Open a Debit Card
    @PostMapping("/open")
    public ResponseEntity<DebitCardDTO> openDebitCard(@RequestBody Map<String, Object> requestBody){

        Long accountNumber = objectMapper.convertValue(requestBody.get("accountNumber"), Long.class);
        String cardName = objectMapper.convertValue(requestBody.get("cardName"), String.class);

        DebitCardDTO createdDebitCard = debitCardService.openDebitCard(accountNumber, cardName);

        if (createdDebitCard == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(createdDebitCard, HttpStatus.CREATED);
        }
    }

    // Service 8
    @PutMapping("/{amount}/pay")
    public ResponseEntity<Void> makePayment(@RequestBody Map<String, Object> requestBody,
                                            @PathVariable double amount){

        DebitCardDTO debitCardDTO = objectMapper.convertValue(requestBody.get("debitCardDTO"), DebitCardDTO.class);
        AccountDTO receivingAccountDTO = objectMapper.convertValue(requestBody.get("receivingAccountDTO"), AccountDTO.class);

        if (debitCardDTO == null || receivingAccountDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean res = debitCardService.makePayment(debitCardDTO, receivingAccountDTO, amount);
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
