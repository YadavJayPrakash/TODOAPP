package com.app.todopp.repository;

import com.app.todopp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRespository extends JpaRepository<Task,Long> {


}
