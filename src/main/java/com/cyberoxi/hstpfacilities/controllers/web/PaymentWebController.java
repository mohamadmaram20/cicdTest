package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Payment;
import com.cyberoxi.hstpfacilities.services.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 2/2/2020
 */
@RestController
@RequestMapping(WEB_ROUTE + "/payments")
public class PaymentWebController {

    private PaymentService paymentService;

    @Autowired
    public PaymentWebController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @ApiOperation(value = "GetAllPayments", notes = "Get all payments")
    public ResponseEntity<?> getPayments() {
        return ResponseEntity.ok(paymentService.getPayments());
    }

    @GetMapping("/unit/{unitId}")
    @ApiOperation(value = "GetPayments", notes = "Get all payments of a unit")
    public ResponseEntity<?> getPayments(@PathVariable long unitId) {
        return ResponseEntity.ok(paymentService.getPayments(unitId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetPayment", notes = "Get a payment by id")
    public ResponseEntity<?> getPayment(@PathVariable long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @PostMapping
    @ApiOperation(value = "AddPayment", notes = "Add new payment")
    public ResponseEntity<?> insertPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.savePayment(payment));
    }
}
