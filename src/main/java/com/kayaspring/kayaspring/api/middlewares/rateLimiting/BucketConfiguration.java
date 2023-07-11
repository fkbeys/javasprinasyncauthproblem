package com.kayaspring.kayaspring.api.middlewares.rateLimiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BucketConfiguration {

    @Bean
    public Bucket bucket() {
        long overdraft = 20;
        Refill refill = Refill.greedy(2, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(overdraft, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

}

