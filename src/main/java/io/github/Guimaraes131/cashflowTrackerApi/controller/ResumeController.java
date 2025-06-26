package io.github.Guimaraes131.cashflowTrackerApi.controller;

import io.github.Guimaraes131.cashflowTrackerApi.model.Resume;
import io.github.Guimaraes131.cashflowTrackerApi.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeService service;

    @Autowired
    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Resume> index(@RequestParam(value = "month") int month,
                                        @RequestParam(value = "year") int year) {

        Resume resume = service.generateMonthResume(month, year);

        return ResponseEntity.ok(resume);
    }
}
