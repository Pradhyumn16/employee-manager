package com.example.employee_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management.employee.employees;
import com.example.employee_management.repository.employeesRepo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class employeesController {
	
	@Autowired
	employeesRepo employees_repo;
	
	@GetMapping("/viewEmployees")
	public List<employees> displayMessage(){
		return employees_repo.findAll();
				
	}
	
	@PostMapping("/addEmployee")
	public employees addEmployee(@RequestBody employees emp){
		employees getEmp = employees_repo.save(emp);
		return getEmp;
	}
	
	@GetMapping("/viewEmployees/{id}")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) work very well. It doesn't miss any reference objects and resolve the problem. Use it before lazy load opertion done by the function ".getReferenceById(id)" here
	public ResponseEntity<employees> getEmployeeById(@PathVariable Long id)
	{
		employees getEmp = employees_repo.getReferenceById(id);
		return ResponseEntity.ok(). body(getEmp);
	}
	
	@PutMapping("/viewEmployees/{id}")
	public ResponseEntity<employees> updateEmployeeById(@PathVariable Long id, @RequestBody employees emp)
	{
		employees getEmp = employees_repo.getReferenceById(id);
		getEmp.setFirstName(emp.getFirstName());
		getEmp.setLastName(emp.getLastName());
		getEmp.setEmail(emp.getEmail());
		getEmp.setDepartment(emp.getDepartment());
		getEmp.setPosition(emp.getPosition());
		
		employees updateEmp = employees_repo.save(getEmp);
		
		return ResponseEntity.ok(). body(updateEmp);
	}
	
	
     @DeleteMapping("/viewEmployees/{id}")
     public String deleteEmployeeById(@PathVariable Long id)
 	{
 		employees getEmp = employees_repo.getReferenceById(id);
 		employees_repo.delete(getEmp);
 		return "record deleted successfully!!";
 	}
}
