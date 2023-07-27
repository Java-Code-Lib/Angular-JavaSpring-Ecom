package com.stradtkt.ecommercejavaangular.config;


import com.stradtkt.ecommercejavaangular.entity.Product;
import com.stradtkt.ecommercejavaangular.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] httpMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods1) -> httpMethods1.disable(httpMethods))
                .withCollectionExposure((metdata, httpMethods1) -> httpMethods1.disable(httpMethods));
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods1) -> httpMethods1.disable(httpMethods))
                .withCollectionExposure((metdata, httpMethods1) -> httpMethods1.disable(httpMethods));
    }
}
