package com.brunocorreia.todosimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunocorreia.todosimple.models.User;
import com.brunocorreia.todosimple.repositorys.TaskRepository;
import com.brunocorreia.todosimple.repositorys.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id){
        @SuppressWarnings("null")
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() ->
        new RuntimeException("Usuário não encontrado! ID: " + id + ", Tipo: " + User.class.getName()));

    }
    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.savAll(obj.getTasks());
        return obj;
    }
    @Transactional
    public User update (User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    @SuppressWarnings("null")
    public void delete (Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
            }catch (Exception e){
                throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");
            }
    }

   
    

    
    
}
