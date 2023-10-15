package de.telran.g10170123ebeshop.repository.jpa;

import de.telran.g10170123ebeshop.domain.entity.jpa.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}

//public interface TaskRepository extends JpaRepository<Task, Integer> {
//    List<Task> findTop5ByOrderByIdDesc();
//}

