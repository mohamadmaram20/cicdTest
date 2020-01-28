package com.cyberoxi.hstpfacilities.configurations;

import com.cyberoxi.hstpfacilities.models.*;
import com.cyberoxi.hstpfacilities.models.responses.CompanyReport;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {

    @Bean
    public Docket apiDocket(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.cyberoxi.hstpfacilities"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .additionalModels(
                        typeResolver.resolve(Admin.class),
                        typeResolver.resolve(Company.class),
                        typeResolver.resolve(Establishment.class),
                        typeResolver.resolve(Facility.class),
                        typeResolver.resolve(Idea.class),
                        typeResolver.resolve(ReceptionDate.class),
                        typeResolver.resolve(CompanyReport.class),
                        typeResolver.resolve(Person.class)
                );
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
