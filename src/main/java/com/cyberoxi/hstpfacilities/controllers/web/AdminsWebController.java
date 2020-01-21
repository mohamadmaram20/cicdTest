package com.cyberoxi.hstpfacilities.controllers.web;

import com.cyberoxi.hstpfacilities.models.requests.AdminLogin;
import com.cyberoxi.hstpfacilities.services.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cyberoxi.hstpfacilities.GlobalVariables.WEB_ROUTE;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@RestController
@RequestMapping(WEB_ROUTE + "/admins")
public class AdminsWebController {

    private AdminsService adminsService;

    @Autowired
    public AdminsWebController(AdminsService adminsService) {
        this.adminsService = adminsService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getAdminInformation() {
        return ResponseEntity.ok(adminsService.getAdminInformation());
    }

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody AdminLogin adminLogin) {
        return ResponseEntity.ok(adminsService.adminLogin(adminLogin.getUsername(), adminLogin.getPassword()));
    }

}
