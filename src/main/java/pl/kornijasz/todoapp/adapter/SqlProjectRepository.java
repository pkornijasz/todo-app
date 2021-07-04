package pl.kornijasz.todoapp.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kornijasz.todoapp.model.Project;
import pl.kornijasz.todoapp.model.ProjectRepository;
import pl.kornijasz.todoapp.model.TaskGroup;
import pl.kornijasz.todoapp.model.TaskGroupRepository;

import java.util.List;

@Repository
interface SqlProjectRepository extends ProjectRepository, JpaRepository<Project, Integer> {
    @Override
    @Query("select distinct p from Project p join fetch p.steps")
    List<Project> findAll();
}
