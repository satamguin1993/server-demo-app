package com.server.example.serverdemo.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class HealthCheckResource {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckResource.class);

    @GetMapping("/v1/health-check")
    public ResponseEntity<String> healthCheck() {
        logger.info("Health check is working fine !!!");
        return ResponseEntity.ok().body(new String("Status:Ok"));
    }

}
