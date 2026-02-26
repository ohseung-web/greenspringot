package com.green.carproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.carproduct.mapper.CarProductMapper;

@Service
public class CarProductService {

//	CarProductMapper을 @Autowride로 의존객체 삽입해야함
//  의존객체 삽입을 하지 않으면 carproduct-mapper.xml의 SQL문을
// 사용할 수 없다.
	
	@Autowired
	CarProductMapper carProductmapper;
	
//	메소드는 CarProductMapper인터페이스의 메소드 복사 붙이기 한다.
	public List<CarProductDTO> getAllCarProduct(){
		return carProductmapper.getAllCarProduct();
	}
	
	public void insertCarProduct(CarProductDTO dto) {
	    carProductmapper.insertCarProduct(dto);
	}
}


