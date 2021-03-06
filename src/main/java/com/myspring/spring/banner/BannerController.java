package com.myspring.spring.banner;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/banner")
public class BannerController {
	private BannerService bannerService;

	@Autowired
	public BannerController(BannerService bannerService) {
		this.bannerService = bannerService;
	}
	
	@GetMapping(value = "/getAllBanners")
	public ResponseEntity<?> getAllBanners() {
		return bannerService.getAllBanners();
	}
	
	@GetMapping(value = "/getBanners")
	public ResponseEntity<?> getBanners(@RequestParam("page") int page, @RequestParam("perPage") int perPage) {
		return bannerService.getBanners(page, perPage);
	}

	@PostMapping(value = "/insertBanner")
	public ResponseEntity<?> insertBanner(@RequestPart(value = "data") BannerVO data,
			@RequestParam("banner") List<MultipartFile> banner) throws NotFoundException {
		return bannerService.insertBanner(data, banner);
	}
	
	@PutMapping(value = "/update/{oldimage}")
	public ResponseEntity<?> updateBanner(@PathVariable("oldimage") String old,@RequestPart(value = "data") BannerVO data, @RequestParam("banner") List<MultipartFile> banner) throws NotFoundException {
		return bannerService.updateBanner(old, data, banner);
	}
	
	@GetMapping("/image/{image}")
	public ResponseEntity<?> getImage(@PathVariable("image") String image) throws IOException {
		return bannerService.getImage(image);
	}
	
	@DeleteMapping(value = "/delete/{image}")
	public ResponseEntity<?> deleteBanner(@PathVariable("image") String image)  {
		return bannerService.deleteBanner(image);
	}
}
