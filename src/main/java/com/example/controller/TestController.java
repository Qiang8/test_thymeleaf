package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.domian.Person;

@Controller
@RequestMapping("/TestController")
public class TestController {
	
	private Map<String,Person>map=new HashMap<String,Person>();
	private List<Person>pList=new ArrayList();
	@RequestMapping("/begin")
	public String test1() {		
		
		return "index";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/save")
	public String save(Person person,Model model,MultipartFile file) throws IllegalStateException, IOException {
		//获得图片后缀名
		String fileName = file.getOriginalFilename();
		String fileTypeName = fileName.substring(fileName.lastIndexOf("."));
		//生成UUID
		String uName = UUID.randomUUID().toString();
		//生成时间戳
		Long tName = new Date().getTime();
		String strName = tName.toString();
		//拼接文件名称保存到本地
		file.transferTo(new File("e:/test/"+uName+"_"+strName+fileTypeName));
		//保存用户信息到map集合
		map.put(person.getId(), person);
		for (String key : map.keySet()) {
			pList.add(map.get(key));
		}
		model.addAttribute("pList", pList);
		return "index";
	}
	
	
	@RequestMapping("/update")
	public String select(String id,HttpServletRequest req) {	
		Person person = map.get(id);
		req.setAttribute("person", person);
		return "update";
	}

	

}
