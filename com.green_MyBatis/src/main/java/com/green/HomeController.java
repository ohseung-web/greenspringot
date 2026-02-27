package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;

@Controller
public class HomeController {
	
	@Autowired
	CarProductService carProductservice;
	
	@GetMapping({"","/"})
	//public String home(Model model) {
		public String home() {
	 System.out.println("HomeController home()");		
	 
	 // List<CarProductDTO>
	 // carProductservice.getAllCarProduct()의 역활은
	 // '21', 'Veyron', '2000000000', 'Bugatti', '1.jpg', '고성능 슈퍼카입니다.'
	 // 위의 자료를 DB에서 꺼내와 List =>  ArrayList<>배열 저장한다.
	// List<CarProductDTO> carlist = carProductservice.getAllCarProduct();
	 
	 // carlist를 model.addAttribute()로 담아서 home.html로 내보낸다.
	 // 단, model은 한 번 담아내보내면 다른 페이지로 이동해도 자료를 가지고 갈 수 없다.
	// model.addAttribute("carlist",carlist);
	 return "forward:/index.html";	 
	  // return "home";
	}
}

