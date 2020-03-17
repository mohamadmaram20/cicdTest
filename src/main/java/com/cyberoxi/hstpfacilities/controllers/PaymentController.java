package com.cyberoxi.hstpfacilities.controllers;

import com.cyberoxi.hstpfacilities.components.PaymentIdGenerator;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.services.AuditorService;
import com.cyberoxi.hstpfacilities.services.UnitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 3/16/20
 */
@Controller
@RequestMapping("/payment")
public class PaymentController {

    private UnitService unitService;
    private AuditorService auditorService;
    private PaymentIdGenerator paymentIdGenerator;

    @Autowired
    public PaymentController(UnitService unitService, AuditorService auditorService, PaymentIdGenerator paymentIdGenerator) {
        this.unitService = unitService;
        this.auditorService = auditorService;
        this.paymentIdGenerator = paymentIdGenerator;
    }

    @GetMapping("/{unitId}")
    @ApiOperation(value = "Payment", notes = "Payment")
    public String payment(@PathVariable long unitId, @RequestParam("type") char type, Model model) {
        Unit unit = unitService.getUnit(unitId);
        long amount = 0;
        String debtType = "", paymentId = "";
        if (type == 'e') {
            amount = auditorService.establishmentsDebt(unit.getEstablishments());
            debtType = "بدهی استقرار";
        } else if (type == 'f') {
            amount = auditorService.facilitiesDebt(unit.getFacilities());
            debtType = "بدهی تسهیلات";
        }
        if (unit.getType() == 1)
            paymentId = paymentIdGenerator.forLegalPerson(unit.getPerson().getNationalCode(), "020", "82");
        else
            paymentId = paymentIdGenerator.forNaturalPerson(unit.getNationalId(), "20", "82");
        model.addAttribute("unitName", unit.getName());
        model.addAttribute("amount", amount);
        model.addAttribute("paymentId", paymentId);
        model.addAttribute("type", debtType);
        return "payment";
    }
}
