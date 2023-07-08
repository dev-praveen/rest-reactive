package com.praveen.reactive;

import com.praveen.reactive.entity.Employee;
import com.praveen.reactive.repo.EmpRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
//@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmpResourceIT {

  @ServiceConnection
  private static final PostgreSQLContainer<?> postgreSqlContainer =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.3"));

  @Autowired EmpRepository empRepository;
  @Autowired private MockMvc mockMvc;

  @BeforeAll
  static void setup() {
    postgreSqlContainer.withReuse(true).start();
  }

  @BeforeEach
  void datasetup() {
    Employee employee =
        Employee.builder()
            .id(1)
            .birthDate(LocalDate.now())
            .hireDate(LocalDate.now())
            .firstName("praveen")
            .gender('M')
            .lastName("sana")
            .build();
    empRepository.save(employee);
  }

  @Test
  public void getAllTests_success() throws Exception {
    mockMvc
        .perform(get("/api/v1/employee/all").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
