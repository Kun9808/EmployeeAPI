package com.rspc.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value=NoSuchEmpExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<ErrorResponce> handleNoSuchEmpException(NoSuchEmpExistsException ex) {
		ErrorResponce error=new ErrorResponce(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=EmpAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorResponce handleEmpAlreadyExistsException(EmpAlreadyExistsException ex) {
		ErrorResponce error=new ErrorResponce(HttpStatus.CONFLICT.value(),ex.getMessage());
		return error;
	}
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handlevalidationException(MethodArgumentNotValidException ex){
	Map<String,String>errors=new HashMap<>();
	BindingResult bindingResult= ex.getBindingResult();
	List<FieldError>errorList=bindingResult.getFieldErrors();
	for(FieldError error:errorList) {
		errors.put(error.getField(),error.getDefaultMessage());
	}
	return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
}
@ExceptionHandler(value=Exception.class)
public ResponseEntity<ErrorResponce> handleException(Exception ex) {
	ErrorResponce error=new ErrorResponce(HttpStatus.INTERNAL_SERVER_ERROR .value(),ex.getMessage());
	return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
}}