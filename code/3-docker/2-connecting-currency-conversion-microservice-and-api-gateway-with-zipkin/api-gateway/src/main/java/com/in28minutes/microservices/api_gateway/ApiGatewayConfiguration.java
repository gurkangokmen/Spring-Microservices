package com.in28minutes.microservices.api_gateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        //http://localhost:8765/get
        Function<PredicateSpec, Buildable<Route>> routeFunction
                = p -> p.path("/get") //A predicate that checks  if the path of the request matches the given pattern.


                .filters(f->f.addRequestHeader("MyHeader","MyURI") //Add a request header to the request before it is routed by the Gateway.
                        .addRequestParameter("Param","MyValue"))//Add a request parameter to the request before it is routed by the Gateway.
                .uri("http://httpbin.org:80")
                ;

        /**
         *         return builder.routes() //This line starts the process of building the routes using the RouteLocatorBuilder.
         *                 .route(p->p.path("/currency-exchange/**") //This specifies that any request with a path that matches the pattern /currency-exchange/** should be routed.
         *                 .uri("lb://currency-exchange-server")) //This specifies that the matched requests should be routed to the URI lb://currency-exchange-server. The lb:// prefix indicates that this URI is a logical name for a load-balanced service. The actual service instances will be discovered by a load balancer (typically provided by Spring Cloud).
         *                 .build(); //This finalizes the route creation and builds the RouteLocator object.
         *
         */


        /**
         *           return builder.routes() //This starts the definition of a series of routes using the provided RouteLocatorBuilder. routes() is a method of RouteLocatorBuilder that returns a fluent builder to define routes.
         *                           .route(p -> p.path("/currency-conversion-new/**") //This defines a route. The route method takes a lambda expression as a parameter. The lambda expression receives a PredicateSpec object p and configures it. Here, p.path("/currency-conversion-new/**") specifies that this route will match any request with a path that starts with /currency-conversion-new/.
         *                           .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}")) // This configures filters for the route. The filters method takes a lambda expression as a parameter, which receives a GatewayFilterSpec object f. The rewritePath filter rewrites the path of the request before forwarding it. Here, it captures the part of the path after /currency-conversion-new/ and appends it to /currency-conversion-feign/.
         *                           .uri("lb://currency-conversion-service")) //This sets the URI of the route. The uri method specifies the destination for the matched request. Here, lb://currency-conversion-service indicates that the request should be load-balanced to instances of the currency-conversion-service registered with the service registry.
         *                           .build(); //This finalizes the route definitions and builds the RouteLocator object, which is then returned by the gatewayRouter method.
         */

        /**
         * Segment
         *
         * We capture rest of url, and reuse them!
         */
        return builder.routes()
                .route(routeFunction)
                .route(p->p.path("/currency-exchange/**").uri("lb://currency-exchange-server")) //name of eureka server of ces
                .route(p->p.path("/currency-conversion/**").uri("lb://currency-conversion-service")) //name of eureka server of ccs
                .route(p->p.path("/currency-conversion-feign/**").uri("lb://currency-conversion-service")) //name of eureka server of ccs
                .route(p->p.path("/currency-conversion-new/**")
                                .filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)","/currency-conversion-feign/${segment}"))

                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
