package com.stormtech.demo.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stormtech.demo.models.studentModel;

@RestController
@RequestMapping("/management/api/v1/student")
public class StudentManagementController {

	private static final List<studentModel> students = Arrays.asList(
			new studentModel(1, "Ntagungira Ali"),
			new studentModel(2, "Salman Ali"),
			new studentModel(3, "Isite Yves"));

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	public List<studentModel> getAllStudents() {
		return students;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student:write','course:write')")
	public void registerStudent(@RequestBody studentModel student) {
		System.out.println("Register Student: { names : " + student.getStudentNames() + " Id: "
				+ student.getStudentId() + "}");
	}

	@DeleteMapping(path = "{Id}")
	@PreAuthorize("hasAnyAuthority('student:write','course:write')")
	public void deleteStudent(@PathVariable("Id") Integer Id) {
		System.out.println("Delete Student with Id: "+Id);
	}

	@PutMapping(path = "{Id}")
	@PreAuthorize("hasAnyAuthority('student:write','course:write')")
	public void updateStudent(@PathVariable("Id") Integer Id, @RequestBody studentModel student) {
		System.out.println("Update Student: { names : " + student.getStudentNames() + " Id: "
				+ Id + "}");
	}
}
