package pl.kornijasz.todoapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pl.kornijasz.todoapp.model.Task;
import pl.kornijasz.todoapp.model.TaskRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository repo;


    @Test
    void httpGet_returnsAllTasks() {
        // given
        var initialSize = repo.findAll().size();
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));
        // when
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);
        // then
        assertThat(result).hasSize(initialSize + 2);
    }
}
