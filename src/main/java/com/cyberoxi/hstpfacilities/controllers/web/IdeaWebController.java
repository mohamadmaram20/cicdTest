package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.Idea;
import com.cyberoxi.hstpfacilities.services.IdeaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cyberoxi.hstpfacilities.GlobalVariables.IDEA_FIELDS;
import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@RestController
@RequestMapping(WEB_ROUTE + "/ideas")
public class IdeaWebController {

    private IdeaService ideaService;

    @Autowired
    public IdeaWebController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping("/unit/{unitId}")
    @ApiOperation(value = "GetIdeas", notes = "Get all ideas of a unit")
    public ResponseEntity<?> getIdeas(@PathVariable long unitId) {
        return ResponseEntity.ok(ideaService.getIdeas(unitId));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetIdea", notes = "Get a idea by id")
    public ResponseEntity<?> getIdea(@PathVariable long id) {
        return ResponseEntity.ok(ideaService.getIdea(id));
    }

    @PostMapping("/unit/{unitId}")
    @ApiOperation(value = "AddIdea", notes = "Add a idea to a unit")
    public ResponseEntity<?> addIdea(@PathVariable long unitId, @RequestBody Idea idea) {
        return ResponseEntity.ok(ideaService.addIdea(unitId, idea));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "UpdateIdea", notes = "Update a idea by id")
    public ResponseEntity<?> updateIdea(@PathVariable long id, @RequestBody Idea idea) {
        return ResponseEntity.ok(ideaService.updateIdea(id, idea));
    }

    @GetMapping("/fields")
    @ApiOperation(value = "UnitPrimitivesFields", notes = "Include branch, receptionType, type")
    public ResponseEntity<?> ideaPrimitivesFields() {
        return ResponseEntity.ok(IDEA_FIELDS);
    }

}
