package com.azulseguros;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/v1")
public class MyApplication extends Application {
    public MyApplication(@Context ServletConfig servletConfig){
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("API Cadastro Endereço");
        beanConfig.setBasePath("/api-azul/v1");
        beanConfig.setResourcePackage("com.azulseguros");
        beanConfig.setScan(true);
    }
}
