package com.songguoliang.mybatis.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 14:59
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@ExcelIgnore
	private Long userId;

	@ExcelProperty(value = "姓名", index = 0)
	private String userName;
	@ExcelProperty(value = "年齡", index = 1)
	private Integer userAge;

	@ExcelProperty(value = "性別", index = 2)
	private boolean sex;
	@ExcelProperty(value = "住址", index = 3)
	private String address;
	@ExcelProperty(value = "爱好", index = 4)
	private String hobby;
	@ExcelProperty(value = "电话", index = 5)
	private String phone;
	@ExcelIgnore
	private int flag;

}
