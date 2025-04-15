package com.alibou.spring_security.modules.health;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Tag(name = "Health")
public class HealthController {
    @Autowired
    @Qualifier("customPrimaryHealthIndicator")
    private HealthIndicator healthIndicator;

    @GetMapping()
    public ResponseEntity<Health> healthCheck() {
        Health health = healthIndicator.health();
        Status status = health.getStatus();
        if (status.equals(Status.UP)) {
            return ResponseEntity.ok().body(health);
        } else {
            return ResponseEntity.status(503).body(health);
        }
    }
}
