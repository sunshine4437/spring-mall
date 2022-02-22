package com.myspring.spring.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private ProductMapper productMapper;

	@Autowired
	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	// 상품 리스트 조회
	public ResponseEntity<?> getProductListByType(int page, int perPage, String type1, String type2) {
		List<ProductVO> res = null;
		int start = (page - 1) * perPage;
		if (type2.equals("all")) {
			res = productMapper.getProductListByType1(start, perPage, type1);
		} else {
			res = productMapper.getProductListByTypeAll(start, perPage, type1, type2);
		}
		if (res == null)
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// 상품 리스트 전체 개수 조회
	public ResponseEntity<?> getProductCountByType(String type1, String type2) {
		int res = 0;
		if (type2.equals("all")) {
			res = productMapper.getProductCountByType1(type1);
		} else {
			res = productMapper.getCountByTypeAll(type1, type2);
		}
		if (res == 0)
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// 많이 팔린 상품 조회
	public ResponseEntity<?> getBestProductListByType(String type1, String type2) {
		List<ProductVO> res = null;
		if (type2.equals("all")) {
			res = productMapper.getBestProductListByType1(type1);
		} else {
			res = productMapper.getBestProductListByTypeAll(type1, type2);
		}
		if (res == null)
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// 상품 정보 조회
	public ResponseEntity<?> getProductByNo(int productNo) {
		ProductVO res = productMapper.getProductByNo(productNo);
		if (res == null)
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
