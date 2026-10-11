package com.hospital.controller;

import com.hospital.model.BaseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Shared list / get / create / update / delete endpoints for every module. */
public abstract class CrudController<T extends BaseEntity> {

    protected final JpaRepository<T, Long> repo;

    protected CrudController(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }

    /** Validation hook, runs before create and update. */
    protected void beforeSave(T body) {}

    /** Hook, runs after a new record is created. */
    protected void afterCreate(T saved) {}

    @GetMapping
    public List<T> all() {
        return repo.findAll(Sort.by("id"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public T create(@RequestBody T body) {
        body.setId(null);
        beforeSave(body);
        T saved = repo.save(body);
        afterCreate(saved);
        return saved;
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T body) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        body.setId(id);
        beforeSave(body);
        return ResponseEntity.ok(repo.save(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
