package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.services.UnitsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cyberoxi.hstpfacilities.GlobalVariables.UNIT_FIELDS;
import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/22/2020
 */
@RestController
@RequestMapping(WEB_ROUTE + "/units")
public class UnitsWebController {

    private UnitsService unitsService;

    @Autowired
    public UnitsWebController(UnitsService unitsService) {
        this.unitsService = unitsService;
    }

    @GetMapping
    @ApiOperation(value = "GetUnits", notes = "Get all units")
    public ResponseEntity<?> getUnits() {
        return ResponseEntity.ok(unitsService.getUnits());
    }

    @GetMapping("/brief")
    @ApiOperation(value = "GetUnitsBrief", notes = "Get all units brief")
    public ResponseEntity<?> getUnitsBrief() {
        return ResponseEntity.ok(unitsService.getUnitsBrief());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetUnit", notes = "Get one unit by id")
    public ResponseEntity<?> getUnit(@PathVariable long id) {
        return ResponseEntity.ok(unitsService.getUnit(id));
    }

    @PostMapping
    @ApiOperation(value = "AddUnit", notes = "Add new unit")
    public ResponseEntity<?> insertUnit(@RequestBody Unit unit) {
        return ResponseEntity.ok(unitsService.saveUnit(unit));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateUnit", notes = "Update one unit by id")
    public ResponseEntity<?> updateUnit(@PathVariable long id, @RequestBody Unit unit) {
        return ResponseEntity.ok(unitsService.updateUnit(id, unit));
    }

    @GetMapping("/report/{id}")
    @ApiOperation(value = "UnitReport", notes = "Get one unit report by id")
    public ResponseEntity<?> unitReport(@PathVariable long id) {
        return ResponseEntity.ok(unitsService.getUnitReport(id));
    }

    @GetMapping("/fields")
    @ApiOperation(value = "UnitPrimitivesFields", notes = "Include branch, receptionType, type")
    public ResponseEntity<?> unitPrimitivesFields() {
        return ResponseEntity.ok(UNIT_FIELDS);
    }

}
