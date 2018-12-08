package com.cgn.bbs;

import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@MapperScan("com.cgn.bbs.*")
@SpringBootApplication
@Configuration
@ServletComponentScan
public class Run extends WebMvcConfigurerAdapter {

	@Override
    public void addViewControllers( ViewControllerRegistry registry ) {
//        registry.addViewController( "/" ).setViewName( "index" );
        registry.addRedirectViewController("/", "/index");
//        registry.addViewController("/toLogin").setViewName("account/login");
        registry.addViewController("/toRegist").setViewName("account/register");
        registry.addViewController("/myFeatures").setViewName("account/features");
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    } 
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		        FastJsonConfig fastJsonConfig = new FastJsonConfig();
		        fastJsonConfig.setSerializerFeatures(
		                SerializerFeature.PrettyFormat
		        );
		        fastConverter.setFastJsonConfig(fastJsonConfig);
		    	converters.add(fastConverter);
	}
	@Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //单个文件最大  
        factory.setMaxFileSize("20480KB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();  
    }  
	
	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}

}
