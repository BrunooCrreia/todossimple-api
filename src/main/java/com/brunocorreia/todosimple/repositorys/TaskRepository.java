package com.brunocorreia.todosimple.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.brunocorreia.todosimple.models.Task;
import com.brunocorreia.todosimple.models.User;
import java.util.List;


public interface TaskRepository extends JpaRepository<User, Long>{

  // List<Task> findByUser_id(Long id);

    // Consulta JPQL ou Java Persistence Query Languag
  @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
  List<Task> findByUserIdd(@Param("id") Long id);

  // Consulta nativa
  @Query(value = "SELECT * FROM TASK t WHERE t.user_id = :id",nativeQuery = true)
  List<Task> findByUserId(@Param("id") Long id); // param vincula o nome inserido a referencia dentro da query nativa

void savAll(List<Task> tasks);



  
    
}
