package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Payment;
import com.cyberoxi.hstpfacilities.services.PaymentsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 2/2/2020
 */
@RestController
@RequestMapping(WEB_ROUTE + "/payments")
public class PaymentsWebController {

    private PaymentsService paymentsService;

    @Autowired
    public PaymentsWebController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @PostMapping
    @ApiOperation(value = "AddPayment", notes = "Add new payment")
    public ResponseEntity<?> insertPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentsService.savePayment(payment));
    }
}
