package com.stormtech.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class NetworkingHomeWork {
	@GetMapping
	public String HelloTeacher() {
		return "Hello Teacher Here is an Api I exposed Using Ip";
	}
}
