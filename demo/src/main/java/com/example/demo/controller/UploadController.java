package com.example.demo.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	@RequestMapping("/upload")
	public String upLoad(@RequestPart("file") MultipartFile multipartFile) {
		///Users/u-hanlin/Downloads
		System.out.println("文件上传开始");
		System.out.println("文件{}" + multipartFile.getOriginalFilename());
		if (!multipartFile.isEmpty()) {
			try {
				// 上传的文件需要保存的路径和文件名称，路径需要存在，否则报错
				multipartFile.transferTo(new File("/Users/u-hanlin/" + multipartFile.getOriginalFilename()));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return "上传失败";
			}
		} else {
			return "请上传文件";
		}
		return "上传成功";
	}

//链接：https://juejin.cn/post/7055128976615800839

}
