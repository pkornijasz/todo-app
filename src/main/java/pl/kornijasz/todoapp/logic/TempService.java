package pl.kornijasz.todoapp.logic;

import org.springframework.stereotype.Service;
import pl.kornijasz.todoapp.model.Task;
import pl.kornijasz.todoapp.model.TaskGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
class TempService {
    List<String> temp(TaskGroupRepository repository) {
        // FIXME: N + 1 SELECT
        return repository.findAll().stream()
                .flatMap(taskGroup -> taskGroup.getTasks().stream())
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }
}
