package com.fresh.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {

	public DummyController() {
		System.out.println("in ctor of " + getClass());
	}
	
	@GetMapping
	public List<Integer> getNumberList() {
		System.out.println("in get num list");
		return List.of(10, 20, 30, 40, 50);
	}
}
