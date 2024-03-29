package com.ozyegin.cs393.controllers;

import com.ozyegin.cs393.dto.PaymentDTO;
import com.ozyegin.cs393.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments(){

        List<PaymentDTO> paymentDTOs = paymentService.getAllPayments();

        if (paymentDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {

        PaymentDTO paymentDTO = paymentService.getPaymentById(id);

        if (paymentDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO){

        PaymentDTO createdPaymentDTO= paymentService.createPayment(paymentDTO);

        if (createdPaymentDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(createdPaymentDTO, HttpStatus.CREATED);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> handlePostOnExistingPayment() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@RequestBody PaymentDTO paymentDTO){

        PaymentDTO updatedPayment = paymentService.updatePayment(paymentDTO);

        if (updatedPayment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllPayments(){
        paymentService.deleteAllPayments();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable Long id){
        paymentService.deletePaymentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
