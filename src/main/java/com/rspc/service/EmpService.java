package com.rspc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rspc.dto.EmpUpdateDTO;
import com.rspc.entity.Emp;
import com.rspc.exception.EmpAlreadyExistsException;
import com.rspc.exception.NoSuchEmpExistsException;
import com.rspc.repository.EmpRepository;

@Service
public class EmpService {
 private EmpRepository empRepo;
  @Autowired
  public EmpService(EmpRepository empRepo) {
	  this.empRepo=empRepo;
  }
  public String addEmp(Emp emptoAdd) {
	Emp e=empRepo.findByEname(emptoAdd.getEname()).orElse(null);
	if(e!= null) {
		throw new EmpAlreadyExistsException("Emp with"+emptoAdd.getEname()+"already exists");
	}
	empRepo.save(emptoAdd);
	return "Emp saved Successfully!!";
  }
  public Emp getEmp(Integer empId) {
	  Emp e=empRepo.findById(empId).orElse(null);
		if(e== null) {
			throw new NoSuchEmpExistsException("Emp with id"+empId+" does not exists");
		}
		return e;
  }
  public String updateEmp(EmpUpdateDTO empDTO,Integer empId) {
	  Emp e=empRepo.findById(empId).orElse(null);
		if(e== null) {
			throw new NoSuchEmpExistsException("Emp with id"+empId+" does not exists");
		}
		if(empDTO.getEname()==null && empDTO.getSal()==null) {
			throw new RuntimeException("Empty Object Not allowed for updation");
		}
		if(empDTO.getEname()!=null) {
		e.setEname(empDTO.getEname());
		}
		if(empDTO.getSal()!=null) {
		e.setEsal(empDTO.getSal());
		} 
		empRepo.save(e);
		return("Employee updated successfully!!");
  }
}
