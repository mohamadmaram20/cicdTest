package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.User;
import com.cyberoxi.hstpfacilities.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@RestController
@RequestMapping(WEB_ROUTE + "/admins")
public class AdminWebController {

    private AdminService adminService;

    @Autowired
    public AdminWebController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getAdminInformation() {
        return ResponseEntity.ok(adminService.getAdminInformation());
    }

    @PostMapping("/register")
    public ResponseEntity<?> adminRegister(@RequestBody User user) {
        return ResponseEntity.ok(adminService.save(user));
    }
}
