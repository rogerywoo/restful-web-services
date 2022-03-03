package com.rwoo.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Controller
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoJpaResource {
    @Autowired
    private TodoHardcodedService todoService;

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @GetMapping("/jpa/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username){
        return todoJpaRepository.findByUsername(username);
        //return todoService.findAll();
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
//        Todo todo = todoService.deleteById(id);
//        if (todo !=null){
//            return ResponseEntity.noContent().build();
//        } else {
//          return ResponseEntity.notFound().build();
//        }

        todoJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id){
        return todoJpaRepository.findById(id).get();
        //return todoService.findById(id);
    }

    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo>  updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
//        return new ResponseEntity<Todo>(todoService.save(todo), HttpStatus.OK);
        todo.setUsername(username);
        return new ResponseEntity<Todo>(todoJpaRepository.save(todo), HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<Void>  addTodo(@PathVariable String username, @RequestBody Todo todo){

//        Todo createdTodo = todoService.save(todo);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
//
//        return ResponseEntity.created(uri).build();

        todo.setUsername(username);
        Todo createdTodo = todoJpaRepository.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
