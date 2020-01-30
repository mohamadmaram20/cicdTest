package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Idea;
import com.cyberoxi.hstpfacilities.models.responses.Field;
import com.cyberoxi.hstpfacilities.services.IdeasService;
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
@RequestMapping(WEB_ROUTE + "/ideas")
public class IdeasWebController {

    private IdeasService ideasService;

    @Autowired
    public IdeasWebController(IdeasService ideasService) {
        this.ideasService = ideasService;
    }

    @GetMapping("/company/{companyId}")
    @ApiOperation(value = "GetIdeas", notes = "Get all ideas of a company")
    public ResponseEntity<?> getIdeas(@PathVariable long companyId) {
        return ResponseEntity.ok(ideasService.getIdeas(companyId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetIdea", notes = "Get a Idea by id")
    public ResponseEntity<?> getIdea(@PathVariable long id) {
        return ResponseEntity.ok(ideasService.getIdea(id));
    }

    @PostMapping("/company/{companyId}")
    @ApiOperation(value = "AddIdea", notes = "Add a idea to a company")
    public ResponseEntity<?> addIdea(@PathVariable long companyId, @RequestBody Idea idea) {
        return ResponseEntity.ok(ideasService.addIdea(companyId, idea));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateIdea", notes = "Update a idea by id")
    public ResponseEntity<?> updateIdea(@PathVariable long id, @RequestBody Idea idea) {
        return ResponseEntity.ok(ideasService.updateIdea(id, idea));
    }

    @GetMapping("/fields")
    @ApiOperation(value = "CompanyPrimitivesFields", notes = "Include branch, receptionType, type")
    public ResponseEntity<?> ideaPrimitivesFields() {
        Map<String, List<Field>> fields = new HashMap<>();

        List<Field> ideaFields = new ArrayList<>();
        ideaFields.add(new Field((byte) 1, "فناوری اطلاعات و ارتباطات"));
        ideaFields.add(new Field((byte) 2, "برق"));
        ideaFields.add(new Field((byte) 3, "الکترونیک قدرت و مخابرات و سخت‌افزار"));
        ideaFields.add(new Field((byte) 4, "مکانیک و طراحی صنعتی"));
        ideaFields.add(new Field((byte) 5, "کشاورزی، منابع طبیعی، صنایع غذایی و محیط زیست"));
        ideaFields.add(new Field((byte) 6, "پزشکی و سلامت"));
        ideaFields.add(new Field((byte) 7, "شیمی و مواد"));
        ideaFields.add(new Field((byte) 8, "عمران و معماری و صنعت ساختمان"));
        ideaFields.add(new Field((byte) 9, "سایر"));
        fields.put("fields", ideaFields);

        return ResponseEntity.ok(fields);
    }

}
