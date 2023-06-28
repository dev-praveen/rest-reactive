package com.praveen.reactive.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Table("employees")
public class Employee {

  @Id
  @Column("emp_no")
  private Integer id;

  @Column("birth_date")
  private LocalDate birthDate;

  @Column("first_name")
  private String firstName;

  @Column("last_name")
  private String lastName;

  private Character gender;

  @Column("hire_date")
  private LocalDate hireDate;
}
