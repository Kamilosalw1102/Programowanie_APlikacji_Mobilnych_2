package stm.stm.service;

import ch.qos.logback.core.status.Status;
import javafx.scene.control.TableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import stm.stm.entity.Task;
import stm.stm.repozytorium.TaskRepository;


import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> selectTasks(){
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "dateAdded"));
    }

    public List<Task> getTaskByTitleOrStatusOrType(String title, Type type, Status status) {
        return taskRepository.findByTitleOrStatusOrType(title,type,status);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public Optional<Task> getTaskById(int taskId){
        return  taskRepository.findById(taskId);
    }


    public boolean deleteTaskById(int taskId){
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        getTaskById(taskId).ifPresent(user -> {
            taskRepository.deleteById(taskId);
            isDeleted.set(true);
        });
        return isDeleted.get();
    }

    public Task changeTaskStatus(int taskId, Status status) {
        Task task = taskRepository.findByTaskId(taskId);
        task.setStatus(status);
        return taskRepository.save(task);
    }


}
