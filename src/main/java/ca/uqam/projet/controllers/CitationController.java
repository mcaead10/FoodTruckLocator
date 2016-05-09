package ca.uqam.projet.controllers;

import java.util.*;
import java.time.*;

import ca.uqam.projet.repositories.*;
import ca.uqam.projet.resources.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CitationController {

  @Autowired CitationRepository repository;

  @RequestMapping("/citations")
  public List<Citation> findAll() {
    return repository.findAll();
  }

  @RequestMapping("/citations/{id}")
  public Citation findById(@PathVariable("id") int id) {
    return repository.findById(id);
  }
}
