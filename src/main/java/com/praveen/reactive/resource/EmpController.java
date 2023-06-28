package com.praveen.reactive.resource;

import com.praveen.reactive.entity.Employee;
import com.praveen.reactive.repo.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/employee")
public class EmpController {

  @Autowired private EmpRepository empRepository;

  @GetMapping(value = "/all")
  public Flux<Employee> getEmployees() {
    return empRepository.findAll();
  }
}
