package com.example.collenar;


import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.User;



@Controller
public class DemoController {
	
	@RequestMapping("/first")
	public String first() {
		return "add";
	}
	
	@RequestMapping(value="/save")
	public String  save(@Validated User user,BindingResult br,Model model) {
		
		if(br.hasErrors()) {
			model.addAttribute("status", "失败");
		}else {
			System.out.println(user);
			model.addAttribute("status", "success");
		}
		
		return  "status";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String deleteById(@RequestParam Map<String,String> params) {
		Set<String> keySet = params.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String key = params.get(iterator.next());
			System.out.println(key);
		}
		System.out.println("您要删除的ID： "+params.get("id"));
		return "index";
	}
	@RequestMapping(value="/showData",method=RequestMethod.POST)
	public String showData(Model model,HttpServletRequest request) {
		
		User user = new User("小小",18,"123123");
//		model.addAttribute("data","这是数据");
//		model.addAttribute("user",user);
		request.setAttribute("data", "这是数据");
		request.setAttribute("user", user);
		
		
		return "index";
	}
	@ResponseBody
	@RequestMapping(value="/json")
	public User json() {
		User user = new User("校长",18,"123456");
		
		return user;
	}
	@ResponseBody
	@RequestMapping("/aa")
	public Map<String,String> aa(@RequestBody User user) {
		System.out.println(user);
		Map<String,String> map = new HashMap<>();
		map.put("状态", "保存成功");
		return map;
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@RequestParam(value="file",required=false) MultipartFile file,Model model) {
		String path="F:/data/";
		File targetFile = new File("F:/data/");
		if(!targetFile.exists()) {
			System.out.println("创建"+targetFile.isDirectory());
			System.out.println(targetFile.mkdirs());
		}
		try {
			file.transferTo(new File("F:/data/"+"github帐号密码.txt"));
			System.out.println(file.getSize());
			model.addAttribute("msg", "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
}
