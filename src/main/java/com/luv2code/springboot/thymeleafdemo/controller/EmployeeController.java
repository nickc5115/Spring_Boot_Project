package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController
{
	private EmployeeService empService;
	
	public EmployeeController(EmployeeService theEmployeeService)
	{
		empService = theEmployeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel)
	{
		List<Employee> theEmps = empService.findAll();
		theModel.addAttribute("employees", theEmps);
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel)
	{
		Employee theEmployee = empService.findById(theId);
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String showFormForDelete(@RequestParam("employeeId") int theId, Model theModel)
	{
		empService.deleteById(theId);
		
		return "redirect:/employees/list";
	}
	
	@PostMapping("/save")
	public String saveEmployee (@ModelAttribute("employee") Employee theEmployee)
	{
		empService.save(theEmployee);
		return "redirect:/employees/list";
	}

}
