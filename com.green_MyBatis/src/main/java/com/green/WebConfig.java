package com.green;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	/**
     * addResourceHandlers: 정적 리소스(이미지, CSS, JS 등)를 관리하는 메서드이다.
     * 외부의 물리적인 경로를 웹에서 사용하는 URL 주소로 매핑하는 설정을 담당한다.
     */
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
       // React public 폴더 경로 (본인 프로젝트 경로에 맞게 수정)
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/","file:///D:/springbootPjt/com.green_MyBatis/frontend/public/img/");
  
    }
	
}
