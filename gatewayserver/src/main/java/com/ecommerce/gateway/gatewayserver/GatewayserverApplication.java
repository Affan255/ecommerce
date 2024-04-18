package com.ecommerce.gateway.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator ecommerceAppRouteConfig(RouteLocatorBuilder builder){
		return builder.routes()
				.route(p -> p.path("/ecom/products/**")
						.filters(f -> f.rewritePath("/ecom/products/(?<segment>.*)","/${segment}"))
						.uri("lb://PRODUCTS"))
				.route(p -> p.path("/ecom/cart/**")
						.filters(f -> f.rewritePath("/ecom/cart/(?<segment>.*)","/${segment}"))
						.uri("lb://CART"))
				.build();
	}
}
