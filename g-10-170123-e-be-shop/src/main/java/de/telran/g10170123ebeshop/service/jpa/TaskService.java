package de.telran.g10170123ebeshop.service.jpa;

import de.telran.g10170123ebeshop.domain.entity.jpa.Task;
import de.telran.g10170123ebeshop.repository.interfaces.ProductRepository;
import de.telran.g10170123ebeshop.repository.jpa.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;


    public void save(Task task) {
        repository.save(task);
    }

// ===============================================================================

// Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
// Время выполнения предыдущей задачи не должно влиять на старт следующей.

//  public void displayLastFiveTasks() {
//        List<Task> lastFiveTasks = repository.findTop5ByOrderByIdDesc();
//        if (lastFiveTasks.isEmpty()) {
//            System.out.println("Список задач пустой.");
//        } else {
//            System.out.println("Список последних 5 задач:");
//            for (Task task : lastFiveTasks) {
//                System.out.println(task.getDescription());
//            }
//        }
//    }

}
