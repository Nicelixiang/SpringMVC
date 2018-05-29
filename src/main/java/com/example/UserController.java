package com.example;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
	
	@RequestMapping(value="/save",method=RequestMethod.PUT)
	public String save(User user) {
		
		System.out.println(user.toString());
		return "success";
	}
	
//	Scanner scanner = new Scanner(System.in);
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(String id) {
		System.out.print("你要删除的id:"+id);
		return "delete";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@RequestParam Map<String, String> map) {
		
		Iterator<Entry<String,String>> iterator = map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<String,String> entry = iterator.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		return "delete";
	}
	
	@RequestMapping("/redirectTest")
	public String redirectTest(Model model) {
		User user = new User();
		user.setAge(18);
		user.setName("jjj");
		user.setPhoneNum("15522773763");
		model.addAttribute(user);
		return "s";
	}
	
	@RequestMapping("/sss")
	public String doGet(HttpServletRequest request,final HttpServletResponse response) throws ServletException,IOException {
		
	      PrintWriter out = response.getWriter();
	      
	      out.println("jjj");
	      return "delete";
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
//	@RequestMapping("/sss")
//	@ResponseBody
//	public User jsonTest(HttpServletRequest request,Model model) {
//		model.addAttribute("");
//		User user = new User();
//		user.setAge(18);
//		return user;
//	}
	
	@RequestMapping(value="/json",method=RequestMethod.POST)
	@ResponseBody
	public void jsonTest2(@RequestBody User user) {
		
		System.out.println(user.toString());
	}
	
	//上传文件
	@RequestMapping(value="/upload")
	public String upload(@RequestParam(value="file", required = false) MultipartFile file,Model model) {
		String path = "F:/data/";
		String fileName = file.getOriginalFilename();
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			System.out.println("创建"+targetFile.isDirectory());
			System.out.println(targetFile.mkdirs());
		}
		//保存
		try {
			file.transferTo(new File(path+fileName));
			System.out.println(file.getSize());
			model.addAttribute("msg","操作成功");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";		
	}
}
