package com.songguoliang.mybatis.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.songguoliang.mybatis.entity.User;
import com.songguoliang.mybatis.mapper.UserMapper;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 15:01
 */
@Service
public class ExportService {

	@Autowired
	private UserMapper userMapper;

	public void export(HttpServletResponse response) throws Exception {

		ServletOutputStream outputStream = response.getOutputStream();
		// 添加响应头信息
		response.setHeader("Content-disposition", "attachment; filename=ceshi.xlsx");
		// 设置类型
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		// 设置头
		response.setHeader("Pragma", "No-cache");
		// 设置头
		response.setHeader("Cache-Control", "no-cache");
		// 设置日期头
		response.setDateHeader("Expires", 0);

		// 获取总数据量
		Integer count = userMapper.selectCount();
		// 如果总数据量多余10万，分页导出
		// 每页多少个
		int max = 100000;
		// 必须放到循环外，否则会刷新流
		ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
		WriteSheet writeSheet = EasyExcel.writerSheet(0, "查询结果").head(User.class).build();
		for (int i = 0; i < (count / max) + 1; i++) {
			if (i * max > 800000) {
				break;
			}
			List<User> exportList = userMapper.selectUserByPage(i * max, max);
			excelWriter.write(exportList, writeSheet);
		}
		// 刷新流
		excelWriter.finish();
		outputStream.flush();
		response.getOutputStream().close();

	}

	public void exportToLocal() throws Exception {
		OutputStream outputStream = new FileOutputStream("D:/myexcel3.xlsx");
		ExcelWriter excelWriter = EasyExcelFactory.getWriter(outputStream);
		// 获取总数据量
		Integer count = userMapper.selectCount();
		// 如果总数据量多余10万，分页导出
		// 每页多少个
		int max = 100000;
		// 必须放到循环外，否则会刷新流
		WriteSheet writeSheet = EasyExcel.writerSheet(0, "查询结果").head(User.class).build();
		for (int i = 0; i < (count / max) + 1; i++) {
			if (i * max > 800000) {
				break;
			}
			List<User> exportList = userMapper.selectUserByPage(i * max, max);
			excelWriter.write(exportList, writeSheet);
		}
		// 刷新流
		excelWriter.finish();
		outputStream.flush();
	}

}
