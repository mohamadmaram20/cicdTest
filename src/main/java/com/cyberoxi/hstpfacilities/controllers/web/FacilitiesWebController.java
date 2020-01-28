package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.services.FacilitiesService;
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
@RequestMapping(WEB_ROUTE + "/facilities")
public class FacilitiesWebController {

    private FacilitiesService facilitiesService;

    @Autowired
    public FacilitiesWebController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }

    @GetMapping("/{companyId}")
    @ApiOperation(value = "GetFacilities", notes = "Get all Facilities of a company")
    public ResponseEntity<?> getFacilities(@PathVariable long companyId) {
        return ResponseEntity.ok(facilitiesService.getFacilities(companyId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetFacility", notes = "Get a facility by id")
    public ResponseEntity<?> getFacility(@PathVariable long id) {
        return ResponseEntity.ok(facilitiesService.getFacility(id));
    }

    @PostMapping("/{companyId}")
    @ApiOperation(value = "AddFacility", notes = "Add a facility contract to a company")
    public ResponseEntity<?> addFacility(@PathVariable long companyId, @RequestBody Facility facility) {
        return ResponseEntity.ok(facilitiesService.addFacility(companyId, facility));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateFacility", notes = "Update a facility by id")
    public ResponseEntity<?> updateFacility(@PathVariable long id, @RequestBody Facility facility) {
        return ResponseEntity.ok(facilitiesService.updateFacility(id, facility));
    }
}
