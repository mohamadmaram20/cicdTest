package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.Field;
import com.cyberoxi.hstpfacilities.services.UnitsService;
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
        Map<String, List<Field>> fields = new HashMap<>();

        List<Field> branches = new ArrayList<>();
        branches.add(new Field((byte) 1, "پذیرش شده در پارک"));
        branches.add(new Field((byte) 2, "مرکز رشد همدان"));
        branches.add(new Field((byte) 3, "مرکز رشد ملایر"));
        branches.add(new Field((byte) 4, "مرکز رشد لالجین"));
        branches.add(new Field((byte) 5, "مرکز رشد کبودرآهنگ"));
        branches.add(new Field((byte) 6, "مرکز رشد رزن"));
        branches.add(new Field((byte) 7, "مرکز رشد کشاورزی"));
        branches.add(new Field((byte) 8, "مرکز نوآوری اسدآباد"));
        branches.add(new Field((byte) 9, "مرکز نوآوری نهاوند"));
        branches.add(new Field((byte) 10, "مرکز نوآوری تویسرکان"));
        fields.put("branch", branches);

        List<Field> receptionTypes = new ArrayList<>();
        receptionTypes.add(new Field((byte) 1, "کانون"));
        receptionTypes.add(new Field((byte) 2, "پیش رشد - جدید"));
        receptionTypes.add(new Field((byte) 3, "پیش رشد - تبدیل وضعیت از کانون"));
        receptionTypes.add(new Field((byte) 4, "رشد - جدید"));
        receptionTypes.add(new Field((byte) 5, "رشد - تبدیل وضعیت از دوره رشد مقدماتی"));
        receptionTypes.add(new Field((byte) 6, "موسسه - جدید"));
        receptionTypes.add(new Field((byte) 7, "موسسه - تبدیل وضعیت از دوره رشد "));
        fields.put("receptionType", receptionTypes);

        List<Field> receptionDateTypes = new ArrayList<>();
        receptionDateTypes.add(new Field((byte) 1, "کانون"));
        receptionDateTypes.add(new Field((byte) 2, "پیش رشد"));
        receptionDateTypes.add(new Field((byte) 3, "رشد"));
        receptionDateTypes.add(new Field((byte) 4, "موسسه"));
        fields.put("receptionDateTypes", receptionDateTypes);

        List<Field> types = new ArrayList<>();
        types.add(new Field((byte) 1, "مسئولیت محدود"));
        types.add(new Field((byte) 2, "سهامی خاص"));
        types.add(new Field((byte) 3, "تعاونی"));
        types.add(new Field((byte) 4, "سایر"));
        fields.put("types", types);

        return ResponseEntity.ok(fields);
    }

}
