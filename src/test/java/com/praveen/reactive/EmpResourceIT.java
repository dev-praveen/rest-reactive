package com.praveen.reactive;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmpResourceIT {

  @ServiceConnection
  private static final PostgreSQLContainer postgreSqlContainer =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.3"));

  @Autowired private MockMvc mockMvc;

  @BeforeAll
  static void setup() {
    postgreSqlContainer.withReuse(true).start();
  }

  @Test
  @Sql({"/data/testData.sql"})
  public void getAllTests_success() throws Exception {
    mockMvc
        .perform(get("/api/v1/employee/all").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
