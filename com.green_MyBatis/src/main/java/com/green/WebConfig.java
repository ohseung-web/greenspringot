package com.green;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	/**
     * addResourceHandlers: 정적 리소스(이미지, CSS, JS 등)를 관리하는 메서드이다.
     * 외부의 물리적인 경로를 웹에서 사용하는 URL 주소로 매핑하는 설정을 담당한다.
     */
	
	// 이미지 업로드가 안되는 이슈때문에 아래처럼 코드르 수정한다.
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
       // React public 폴더 경로 (본인 프로젝트 경로에 맞게 수정)
       // registry.addResourceHandler("/img/**")
       //         .addResourceLocations("classpath:/static/img/","file:///C:/springPjt/greenspringot/com.green_MyBatis/frontend/public/img/");
   
		   registry.addResourceHandler("/img/**")
		    .addResourceLocations("classpath:/static/img/");
		
    }
	
	// F5누르면 오류뜨는 이슈때문에 반드시 작성한다.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	     // 모든 경로를 index.html로 포워딩 (확장자가 없는 경로 대상)
		// 중첩된 경로(예: /member/signup)까지 모두 index.html로 포워딩하는 설정이다.
		// {path:[^\\.]*} 대신 아래 패턴을 사용해 보세요.
        registry.addViewController("/{path1:[^\\.]*}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{path1:[^\\.]*}/{path2:[^\\.]*}")
                .setViewName("forward:/index.html");
        registry.addViewController("/{path1:[^\\.]*}/{path2:[^\\.]*}/{path3:[^\\.]*}")
                .setViewName("forward:/index.html");
	
	}
	
}
