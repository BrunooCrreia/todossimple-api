package com.brunocorreia.todosimple.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brunocorreia.todosimple.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    
}
