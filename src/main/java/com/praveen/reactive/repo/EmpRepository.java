package com.praveen.reactive.repo;

import com.praveen.reactive.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends ReactiveCrudRepository<Employee, Integer> {}
