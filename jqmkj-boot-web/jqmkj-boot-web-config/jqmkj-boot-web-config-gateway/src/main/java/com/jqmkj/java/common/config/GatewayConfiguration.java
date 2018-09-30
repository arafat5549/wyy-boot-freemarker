package com.jqmkj.java.common.config;

import com.jqmkj.java.common.config.gateway.accesscontrol.AccessControlFilter;
import com.jqmkj.java.common.config.gateway.ratelimiting.RateLimitingFilter;
import com.jqmkj.java.common.config.gateway.responserewriting.SwaggerBasePathRewritingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Configuration
    public static class SwaggerBasePathRewritingConfiguration {

        @Bean
        public SwaggerBasePathRewritingFilter swaggerBasePathRewritingFilter(){
            return new SwaggerBasePathRewritingFilter();
        }
    }

    @Configuration
    public static class AccessControlFilterConfiguration {

        @Bean
        public AccessControlFilter accessControlFilter(RouteLocator routeLocator, JqmkjProperties jqmkjProperties){
            return new AccessControlFilter(routeLocator, jqmkjProperties);
        }
    }

    /**
     * Configures the Zuul filter that limits the number of API calls per user.
     * <p>
     * This uses Bucket4J to limit the API calls
     */
    @Configuration
    @ConditionalOnProperty("jqmkj.gateway.rate-limiting.enabled")
    public static class RateLimitingConfiguration {

        private final JqmkjProperties jqmkjProperties;

        public RateLimitingConfiguration(JqmkjProperties jqmkjProperties) {
            this.jqmkjProperties = jqmkjProperties;
        }

        @Bean
        public RateLimitingFilter rateLimitingFilter() {
            return new RateLimitingFilter(jqmkjProperties);
        }
    }
}
