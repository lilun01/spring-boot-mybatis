package com.songguoliang.mybatis.controller;

import com.songguoliang.mybatis.entity.User;
import com.songguoliang.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 14:59
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> lists() {
		return userService.getUsers();
	}

	@GetMapping("/addUser")
	public int addUser() {
		return userService.addUsers();
	}
	@GetMapping("/deleteUser/{flag}")
	public int deleteUser(@PathVariable(name="flag") int flag) {
		return userService.deleteUser(flag);
	}

}
