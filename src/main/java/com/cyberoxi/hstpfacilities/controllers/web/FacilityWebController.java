package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.services.FacilityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cyberoxi.hstpfacilities.GlobalVariables.FACILITY_FIELDS;
import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@RestController
@RequestMapping(WEB_ROUTE + "/facilities")
public class FacilityWebController {

    private FacilityService facilityService;

    @Autowired
    public FacilityWebController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("/unit/{unitId}")
    @ApiOperation(value = "GetFacilities", notes = "Get all facilities of a unit")
    public ResponseEntity<?> getFacilities(@PathVariable long unitId) {
        return ResponseEntity.ok(facilityService.getFacilities(unitId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetFacility", notes = "Get a facility by id")
    public ResponseEntity<?> getFacility(@PathVariable long id) {
        return ResponseEntity.ok(facilityService.getFacility(id));
    }

    @PostMapping("/unit/{unitId}")
    @ApiOperation(value = "AddFacility", notes = "Add a facility contract to a unit")
    public ResponseEntity<?> addFacility(@PathVariable long unitId, @RequestBody Facility facility) {
        return ResponseEntity.ok(facilityService.addFacility(unitId, facility));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateFacility", notes = "Update a facility by id")
    public ResponseEntity<?> updateFacility(@PathVariable long id, @RequestBody Facility facility) {
        return ResponseEntity.ok(facilityService.updateFacility(id, facility));
    }

    @GetMapping("/fields")
    @ApiOperation(value = "FacilitiesPrimitivesFields", notes = "Include branch, receptionType, type")
    public ResponseEntity<?> facilityPrimitivesFields() {
        return ResponseEntity.ok(FACILITY_FIELDS);
    }
}
