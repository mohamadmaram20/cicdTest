package com.cyberoxi.hstpfacilities.configurations;

import com.cyberoxi.hstpfacilities.models.*;
import com.cyberoxi.hstpfacilities.models.responses.AdminReport;
import com.cyberoxi.hstpfacilities.models.responses.UnitReport;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket apiDocket(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.cyberoxi.hstpfacilities"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .additionalModels(
                        typeResolver.resolve(User.class),
                        typeResolver.resolve(Unit.class),
                        typeResolver.resolve(Establishment.class),
                        typeResolver.resolve(Facility.class),
                        typeResolver.resolve(Idea.class),
                        typeResolver.resolve(ReceptionDate.class),
                        typeResolver.resolve(UnitReport.class),
                        typeResolver.resolve(AdminReport.class),
                        typeResolver.resolve(Person.class),
                        typeResolver.resolve(Payment.class)
                );
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "HSTPFacilities",
                "HSTP facilities system server APIs",
                "0.1",
                "",
                new Contact("M. Kahool", "www.cyberoxi.com", "mmk@cyberoxi.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }
}
