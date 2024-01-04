package com.example.controller;

import java.util.List;
import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.task;
import com.example.repository.taskrepository;
import com.example.status.responsestatus;

@Controller
public class controller {
	
	@Autowired
	taskrepository c;
	@GetMapping("/")
	public String m1() {
		return "user";
	}
	@GetMapping("/user")
	public String mp() {
		return "user";
	}
	
	@RequestMapping("/man")
	public String s(Model m) {
		m.addAttribute("tasks",new task());
		return "home";
		
	}
	@PostMapping("/addtask")
	
	public ModelAndView m3(@ModelAttribute("tasks") task t) {
		c.save(t);
		responsestatus x=new responsestatus();
		ModelAndView v=new ModelAndView("addtask");
		x.setStatuscode(HttpStatus.CREATED.value());
		x.setMessage("data  saved successfully");
		x.setData(t);
		v.addObject("status",x);
		return v;
	}
	@GetMapping("/fetch")
	public ModelAndView gettall(){
		List<task> f=c.findAll();
		ModelAndView v=new ModelAndView("alldetails");
		v.addObject("f",f);
		return v; 
		
		
	}
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam("id")int id){
		c.deleteById(id);
		ModelAndView r=new ModelAndView("delete");
		responsestatus x=new responsestatus();
		x.setStatuscode(HttpStatus.OK.value());
		x.setMessage("data deleted successfully");
		r.addObject("message",x);
		return r;
		
		
		
	}
	@GetMapping("/update")
	public ModelAndView update(@RequestParam("id")int id){
		Optional<task> t=c.findById(id);
		task f=t.get();
	
		ModelAndView r=new ModelAndView("update");
		r.addObject("data",f);
		
		return r;
		
		
		
	}



}
