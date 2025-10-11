package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    // List all todos
    public List<Todo> listAll() {
        return repository.findAll();
    }

    // Create a new todo
    public Todo create(Todo todo) {
        return repository.save(todo);
    }

    // Get a todo by ID
    public Optional<Todo> get(Long id) {
        return repository.findById(id);
    }

    // Update a todo by ID
    public Optional<Todo> update(Long id, Todo updatedTodo) {
        return repository.findById(id).map(todo -> {
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todo.setCompleted(updatedTodo.isCompleted());
            return repository.save(todo);
        });
    }

    // Delete a todo by ID
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
