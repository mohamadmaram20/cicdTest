package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.responses.Field;
import com.cyberoxi.hstpfacilities.services.CompaniesService;
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
@RequestMapping(WEB_ROUTE + "/companies")
public class CompaniesWebController {

    private CompaniesService companiesService;

    @Autowired
    public CompaniesWebController(CompaniesService companiesService) {
        this.companiesService = companiesService;
    }

    @GetMapping
    @ApiOperation(value = "GetCompanies", notes = "Get all companies")
    public ResponseEntity<?> getCompanies() {
        return ResponseEntity.ok(companiesService.getCompanies());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetCompany", notes = "Get one company by id")
    public ResponseEntity<?> getCompany(@PathVariable long id) {
        return ResponseEntity.ok(companiesService.getCompany(id));
    }

    @PostMapping
    @ApiOperation(value = "AddCompany", notes = "Add new company")
    public ResponseEntity<?> insertCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companiesService.saveCompany(company));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateCompany", notes = "Update one company by id")
    public ResponseEntity<?> updateCompany(@PathVariable long id, @RequestBody Company company) {
        return ResponseEntity.ok(companiesService.updateCompany(id, company));
    }

    @GetMapping("/fields")
    @ApiOperation(value = "CompanyPrimitivesFields", notes = "Include branch, receptionType, type")
    public ResponseEntity<?> companyPrimitivesFields() {
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
