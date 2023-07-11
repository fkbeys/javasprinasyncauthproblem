package com.kayaspring.kayaspring.api.middlewares.healthCheck;

import com.kayaspring.kayaspring.data.repositories.IUserRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    private final IUserRepository userService;

    public HealthCheck(IUserRepository userService) {
        this.userService = userService;
    }

    @Override
    public Health health() {

        if (userService.count()>0) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}

