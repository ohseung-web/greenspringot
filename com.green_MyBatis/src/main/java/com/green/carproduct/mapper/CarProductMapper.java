package com.green.carproduct.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.carproduct.CarProductDTO;

@Mapper
public interface CarProductMapper {

	// 추상메소드
//		carProduct 상품 모두 검색하는 메소드
		// getAllCarProduct()메소드는 => carproduct-mappe.xml 
		// 연결하여 select SQL 쿼리문을 작성한다.
		public List<CarProductDTO> getAllCarProduct();
		
		public void insertCarProduct(CarProductDTO dto);
}
