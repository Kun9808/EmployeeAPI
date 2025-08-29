package com.rspc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 private Integer eid;
	@NotBlank(message="Emp Name is Required")
 private String ename;
	@NotNull(message="Salary is Required")
	@Min(value=10000,message="Salary must be atleast 1000")
 private Double esal;
}
