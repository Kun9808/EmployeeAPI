package com.rspc.controller;

import java.util.Optional;

import com.rspc.dto.EmpUpdateDTO;
import com.rspc.entity.Emp;
import com.rspc.exception.EmpAlreadyExistsException;
import com.rspc.exception.ErrorResponce;
import com.rspc.exception.NoSuchEmpExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rspc.service.EmpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/emp")
public class EmpController {
	private EmpService serv;
	
	@Autowired
	public EmpController(EmpService serv) {
		this.serv = serv;
	}
	@PostMapping("/add")
	public String addEmp(@Valid @RequestBody Emp emp) {
		return serv.addEmp(emp);
	}
	@GetMapping("/{empId}")
	public Emp getEmp(@PathVariable("empId") Integer empId) {
	return serv.getEmp(empId);
	}
	@PutMapping("/update/{empId}")
	public String updateEmp(@Valid @RequestBody EmpUpdateDTO empDTO,@PathVariable("empId")Integer empId) {
		return serv.updateEmp(empDTO, empId);
	}
	//Local Level Exception Handler
	@ExceptionHandler(value=NoSuchEmpExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponce handleNoSuchEmpException(NoSuchEmpExistsException ex) {
		ErrorResponce error=new ErrorResponce(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		return error;
	}
	@ExceptionHandler(value=EmpAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponce handleEmpAlreadyExistsException(EmpAlreadyExistsException ex) {
		ErrorResponce error=new ErrorResponce(HttpStatus.CONFLICT.value(),ex.getMessage());
		return error;
	}
}
