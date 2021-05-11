package com.songguoliang.mybatis.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songguoliang.mybatis.service.ExportService;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 14:59
 */
@RestController
public class ExportController {

	@Autowired
	private ExportService exportService;

	@GetMapping("/export")
	public void export(HttpServletResponse response) throws Exception {
		exportService.export(response);

	}
	
	@GetMapping("/exportToLocal")
	public void exportToLocal() throws Exception {
		exportService.exportToLocal();
		
	}

}
