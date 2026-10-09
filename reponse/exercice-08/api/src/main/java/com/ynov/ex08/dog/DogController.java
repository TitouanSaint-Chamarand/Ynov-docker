package com.ynov.ex08.dog;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/dogs")
public class DogController {

  private final DogRepository repository;

  public DogController(DogRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Dog> list() {
    return repository.findAll();
  }

  @GetMapping("/{dogId}")
  public Dog get(@PathVariable Long dogId) {
    return repository
        .findById(dogId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chien introuvable"));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Dog create(@RequestBody Dog dog) {
    dog.setId(null);
    return repository.save(dog);
  }

  @PutMapping("/{dogId}")
  public Dog update(@PathVariable Long dogId, @RequestBody Dog dog) {
    if (!repository.existsById(dogId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chien introuvable");
    }
    dog.setId(dogId);
    return repository.save(dog);
  }

  @DeleteMapping("/{dogId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long dogId) {
    if (!repository.existsById(dogId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chien introuvable");
    }
    repository.deleteById(dogId);
  }
}
