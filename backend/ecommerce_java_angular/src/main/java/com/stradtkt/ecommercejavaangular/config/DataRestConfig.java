package com.stradtkt.ecommercejavaangular.config;


import com.stradtkt.ecommercejavaangular.entity.Country;
import com.stradtkt.ecommercejavaangular.entity.Product;
import com.stradtkt.ecommercejavaangular.entity.ProductCategory;
import com.stradtkt.ecommercejavaangular.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
    private EntityManager entityManager;
    @Autowired
    public DataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] httpMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        disabledHttpMethods(Product.class, config, httpMethods);
        disabledHttpMethods(ProductCategory.class, config, httpMethods);
        disabledHttpMethods(Country.class, config, httpMethods);
        disabledHttpMethods(State.class, config, httpMethods);
        exposeIds(config);
    }

    private void disabledHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] httpMethods) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods1) -> httpMethods1.disable(httpMethods))
                .withCollectionExposure((metdata, httpMethods1) -> httpMethods1.disable(httpMethods));
    }


    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for(EntityType type : entityTypes) {
            entityClasses.add(type.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
