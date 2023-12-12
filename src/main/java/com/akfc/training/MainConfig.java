package com.akfc.training;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:hello.properties")
@ComponentScan(basePackages = "com.akfc.training")
@EnableAspectJAutoProxy
public class MainConfig {}
