package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "University info from universities.hipolabs.com")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {

  ObjectMapper mapper = new ObjectMapper();

  @Autowired
  UniversityQueryService universityQueryService;

  @Operation(summary = "Get list of universities that match a given name", description = "Uses API documented here: http://universities.hipolabs.com/search")
  @GetMapping("/get")
  public ResponseEntity<String> getUniversity(
      @Parameter(name = "name", description = "name to search", example = "'Harvard' or 'Stanford'") @RequestParam String name)
      throws JsonProcessingException {
    log.info("getUniversity: university={}", name);
    String result = universityQueryService.getJSON(name);
    return ResponseEntity.ok().body(result);
  }
}