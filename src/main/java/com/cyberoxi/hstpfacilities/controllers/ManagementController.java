package com.cyberoxi.hstpfacilities.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import static com.cyberoxi.hstpfacilities.GlobalVariables.MANAGE_ROUTE;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@RestController
@RequestMapping(MANAGE_ROUTE)
public class ManagementController {

    private ApplicationContext applicationContext;

    @Autowired
    public ManagementController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/{command}")
    public ResponseEntity<?> command(@PathVariable String command, @RequestParam Map<String, String> params) {
        String result = "unsuccessful!";
        switch (command) {
            case "":
                break;
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/log")
    public void getLogFile(HttpServletResponse httpServletResponse) throws IOException {
        IOUtils.copy(new FileInputStream("hstpf.log"), httpServletResponse.getOutputStream());
        httpServletResponse.flushBuffer();
    }

    @GetMapping("/sDo")
    public void shutDown() {
        SpringApplication.exit(applicationContext);
    }
}
