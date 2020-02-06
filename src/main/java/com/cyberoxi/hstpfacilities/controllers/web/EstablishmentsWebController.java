package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.services.EstablishmentsService;
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

    private EstablishmentsService establishmentsService;

    @Autowired
    public EstablishmentsWebController(EstablishmentsService establishmentsService) {
        this.establishmentsService = establishmentsService;
    }

    @GetMapping("/unit/{unitId}")
    @ApiOperation(value = "GetEstablishments", notes = "Get all establishments of a unit")
    public ResponseEntity<?> getEstablishments(@PathVariable long unitId) {
        return ResponseEntity.ok(establishmentsService.getEstablishments(unitId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetEstablishment", notes = "Get a establishment by id")
    public ResponseEntity<?> getEstablishment(@PathVariable long id) {
        return ResponseEntity.ok(establishmentsService.getEstablishment(id));
    }

    @PostMapping("/unit/{unitId}")
    @ApiOperation(value = "AddEstablishment", notes = "Add a establishment contract to a unit")
    public ResponseEntity<?> addEstablishment(@PathVariable long unitId, @RequestBody Establishment establishment) {
        return ResponseEntity.ok(establishmentsService.addEstablishment(unitId, establishment));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateEstablishment", notes = "Update a establishment by id")
    public ResponseEntity<?> updateEstablishment(@PathVariable long id, @RequestBody Establishment establishment) {
        return ResponseEntity.ok(establishmentsService.updateEstablishment(id, establishment));
    }

}
