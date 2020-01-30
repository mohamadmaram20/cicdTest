package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.services.EstablishmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@RestController
@RequestMapping(WEB_ROUTE + "/establishments")
public class EstablishmentsWebController {

    private EstablishmentService establishmentService;

    @Autowired
    public EstablishmentsWebController(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    @GetMapping("/company/{companyId}")
    @ApiOperation(value = "GetEstablishments", notes = "Get all establishments of a company")
    public ResponseEntity<?> getEstablishments(@PathVariable long companyId) {
        return ResponseEntity.ok(establishmentService.getEstablishments(companyId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetEstablishment", notes = "Get a establishment by id")
    public ResponseEntity<?> getEstablishment(@PathVariable long id) {
        return ResponseEntity.ok(establishmentService.getEstablishment(id));
    }

    @PostMapping("/company/{companyId}")
    @ApiOperation(value = "AddEstablishment", notes = "Add a establishment contract to a company")
    public ResponseEntity<?> addEstablishment(@PathVariable long companyId, @RequestBody Establishment establishment) {
        return ResponseEntity.ok(establishmentService.addEstablishment(companyId, establishment));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateEstablishment", notes = "Update a establishment by id")
    public ResponseEntity<?> updateEstablishment(@PathVariable long id, @RequestBody Establishment establishment) {
        return ResponseEntity.ok(establishmentService.updateEstablishment(id, establishment));
    }

}
