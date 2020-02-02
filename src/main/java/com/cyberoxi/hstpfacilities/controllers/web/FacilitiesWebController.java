package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.models.responses.Field;
import com.cyberoxi.hstpfacilities.services.FacilitiesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/unit/{unitId}")
    @ApiOperation(value = "GetFacilities", notes = "Get all Facilities of a unit")
    public ResponseEntity<?> getFacilities(@PathVariable long unitId) {
        return ResponseEntity.ok(facilitiesService.getFacilities(unitId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetFacility", notes = "Get a facility by id")
    public ResponseEntity<?> getFacility(@PathVariable long id) {
        return ResponseEntity.ok(facilitiesService.getFacility(id));
    }

    @PostMapping("/unit/{unitId}")
    @ApiOperation(value = "AddFacility", notes = "Add a facility contract to a unit")
    public ResponseEntity<?> addFacility(@PathVariable long unitId, @RequestBody Facility facility) {
        return ResponseEntity.ok(facilitiesService.addFacility(unitId, facility));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateFacility", notes = "Update a facility by id")
    public ResponseEntity<?> updateFacility(@PathVariable long id, @RequestBody Facility facility) {
        return ResponseEntity.ok(facilitiesService.updateFacility(id, facility));
    }

    @GetMapping("/fields")
    @ApiOperation(value = "FacilitiesPrimitivesFields", notes = "Include branch, receptionType, type")
    public ResponseEntity<?> facilityPrimitivesFields() {
        Map<String, List<Field>> fields = new HashMap<>();

        List<Field> changeType = new ArrayList<>();
        changeType.add(new Field((byte) 1, "استمهال"));
        changeType.add(new Field((byte) 2, "افزایش سقف"));
        changeType.add(new Field((byte) 3, "عدم تعلق فاز بعدی"));
        fields.put("changeType", changeType);

        return ResponseEntity.ok(fields);
    }
}
