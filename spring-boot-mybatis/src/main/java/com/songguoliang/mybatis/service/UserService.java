package com.songguoliang.mybatis.service;

import com.alibaba.fastjson.JSON;
import com.songguoliang.mybatis.entity.User;
import com.songguoliang.mybatis.mapper.UserMapper;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.ftp.Ftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 15:01
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	public int addUsers() {
		ExecutorService executorService = new ThreadPoolExecutor(50, 300, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5000, true),Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		List<User> get1000Users = get1000Users();
		
		for(int i = 0;i<1000;i++) {
			executorService.execute(()->saveUsers(get1000Users));
		}
		
		return 1;
		 
	}
 
	
	
	public int deleteUser(int flag) {

		
		
		
		for(int i=0;i<100;i++){
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int randomInt = RandomUtil.randomInt(1, 20);
			int count = userMapper.deleteUsers(randomInt);
			System.out.println("==========================删除的数量："+count+"========================");
		}
		return 1;
	}

	private int saveUsers(List<User> users) {
		int count = userMapper.addUsers(users);
		//int k = 1/0;
		//System.out.println(Thread.currentThread().getName()+"插入成功："+count);
		return count;

	}
	
	
	private List<User> get1000Users() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 1000; i++) {
			int randomInt = RandomUtil.randomInt(1, 20);
			User user = User.builder().userName("lisi" + i).flag(randomInt).sex(true).address("四川简阳").phone("18352565896").userAge(25).build();
			user.setHobby(JSON.toJSONString(user));
			users.add(user);
		}

		return users;
	}

}
