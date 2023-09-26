package com.springboot.thymeleafsecuritydemo.controller;
import com.springboot.thymeleafsecuritydemo.entity.Employee;
import com.springboot.thymeleafsecuritydemo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/managers")
public class EmployeeController {
	private EmployeeService employeeService;

	@InitBinder
	public void binder(WebDataBinder dataBinder){
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> employeeList = employeeService.findAll();
		theModel.addAttribute("employees", employeeList);
		return "managers";
	}
	@GetMapping("/add")
	public String addEmployee(Model model){
			Employee employee = new Employee();
			model.addAttribute("employee", employee);
			return "/addEmployee";
	}
	@GetMapping("/update")
	public String updateEmployee(@RequestParam("employeeId") int id, Model model){
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		return "/addEmployee";
	}
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int id){
		employeeService.deleteById(id);
		return "redirect:/managers/list";
	}
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult){
		if (!bindingResult.hasErrors()) {
			employeeService.save(employee);
			return "redirect:/managers/list";
		} else {
			return "/addEmployee";
		}
	}
}









