package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.CountryCodeQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Zip Code")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")


public class ZipCodeController {
    

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService ZipCodeQueryService;

    @Operation(summary="Get a ZipCode", description ="Info about zipcodes")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCode(
        @Parameter(name="zipcode", example="93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        String result = ZipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }

}
