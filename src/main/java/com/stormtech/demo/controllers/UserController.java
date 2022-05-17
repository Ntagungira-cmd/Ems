package com.stormtech.demo.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stormtech.demo.models.studentModel;

@RestController
@RequestMapping("api/v1/student")
public class UserController {

	private static final List<studentModel> students = Arrays.asList(
			new studentModel(1, "Ntagungira Ali"),
			new studentModel(2, "Salman Ali"),
			new studentModel(3, "Isite Yves"));

	@GetMapping(path = "{Id}")
	public studentModel getStudent(@PathVariable("Id") Integer Id) {
		return students.stream().filter(student -> Id.equals(student.getStudentId())).findFirst()
				.orElseThrow(()->new IllegalStateException("Student "+ Id + "does not exist"));
	}
}
