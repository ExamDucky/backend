package com.unihack.smart_usb.api.controller;

import com.unihack.smart_usb.api.dto.TestDto;
import com.unihack.smart_usb.facade.TestFacade;
import com.unihack.smart_usb.persistance.model.Professor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
public class TestController {
    private final TestFacade testFacade;


    @Operation(summary = "Get test by id", description = "Method for getting a test owned by the professor, by the given id")
    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(Authentication authentication, @NotNull @PathVariable Long id) {
        Professor professor = (Professor) authentication.getPrincipal();
        log.info("Get test by id for professor with id: " + professor.getId());
        return ResponseEntity.ok(testFacade.getTestById(id, professor));
    }

//    @Operation(summary = "Get test by title", description = "Method for getting a test owned by the professor, by the given title")
//    @GetMapping()
//    public ResponseEntity<TestDto> getTestByTitle(Authentication authentication, @RequestParam String title) {
//        Professor professor = (Professor) authentication.getPrincipal();
//        log.info("Get test by title for professor with id: " + professor.getId());
//        return ResponseEntity.ok(testFacade.getTestByTitle(title, professor));
//    }

    @Operation(summary = "Search tests by title", description = "Method for getting the tests owned by the professor, where the titles start with the given query")
    @GetMapping("/search")
    public ResponseEntity<List<TestDto>> getTestsByTitleQuery(Authentication authentication, @RequestParam String titleQuery) {
        Professor professor = (Professor) authentication.getPrincipal();
        log.info("Get tests by title query for professor with id: " + professor.getId());
        return ResponseEntity.ok(testFacade.getTestByTitleQuery(titleQuery, professor));
    }

    @Operation(summary = "Search tests by title", description = "Method for getting the tests owned by the professor, where the titles start with the given query")
    @PostMapping("/create")
    public ResponseEntity<TestDto> createNewTest(Authentication authentication, @RequestBody TestDto testDto) {
        Professor professor = (Professor) authentication.getPrincipal();
        log.info("Create new test by the professor with id: " + professor.getId());
        return ResponseEntity.ok(testFacade.createNewTest(testDto));
    }
}
