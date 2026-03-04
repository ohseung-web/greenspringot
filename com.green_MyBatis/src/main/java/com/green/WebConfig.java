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
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 1. 현재 운영체제 확인
//        String os = System.getProperty("os.name").toLowerCase();
//        String uploadPath;
//
//        // 2. 운영체제에 따른 외부 저장 경로 설정
//        if (os.contains("win")) {
//            // 로컬 윈도우 환경 (팀별 실제 로컬 경로로 수정하세요)
//            uploadPath = "file:///C:/springPjt/greenspringot/com.green_MyBatis/frontend/public/img/"; 
//        } else {
//            // Cloudtype 리눅스 배포 환경 (일반적으로 /home/node/ 또는 /app/ 하위 사용)
//            uploadPath = "file:/home/node/uploads/img/";
//        }
//
//        // 3. /img/** 로 들어오는 요청을 위에서 정한 물리적 경로와 매핑
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("classpath:/static/img/", uploadPath);
//    }
//	
	// F5누르면 오류뜨는 이슈때문에 반드시 작성한다.
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {

			// 모든 계층의 경로를 index.html로 포워딩합니다. (가장 권장되는 방식)
		    registry.addViewController("/**/{path:[^\\.]*}")
		            .setViewName("forward:/index.html");
		
		}
	
		
		
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
       // React public 폴더 경로 (본인 프로젝트 경로에 맞게 수정)
       // registry.addResourceHandler("/img/**")
       //         .addResourceLocations("classpath:/static/img/","file:///C:/springPjt/greenspringot/com.green_MyBatis/frontend/public/img/");
   
		   registry.addResourceHandler("/img/**")
		    .addResourceLocations("classpath:/static/img/");
		
    }
	
	
	
}
