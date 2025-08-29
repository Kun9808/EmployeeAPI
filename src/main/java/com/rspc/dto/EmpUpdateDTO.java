package com.rspc.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpUpdateDTO {
@Size(min=1,message="Ename cannot be empty")
private String ename;
@Min(value=10000,message="Salary must be at least 10000")
private Double sal;
	
}
