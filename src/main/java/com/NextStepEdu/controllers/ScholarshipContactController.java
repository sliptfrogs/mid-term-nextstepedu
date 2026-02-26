package com.NextStepEdu.controllers;

import com.NextStepEdu.dto.requests.ScholarshipContactRequest;
import com.NextStepEdu.dto.responses.ScholarshipContactResponse;
import com.NextStepEdu.models.ScholarshipContactModel;
import com.NextStepEdu.services.ScholarshipContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scholarship-contact")
@AllArgsConstructor
public class ScholarshipContactController {

    private final ScholarshipContactService scholarshipContactService;

    @GetMapping
    public ResponseEntity<List<ScholarshipContactResponse>> getAllContacts() {
        List<ScholarshipContactResponse> contacts = scholarshipContactService.findAll();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/scholarship/{scholarshipId}")
    public ResponseEntity<List<ScholarshipContactModel>> getContactsByScholarshipId(
            @PathVariable Integer scholarshipId
    ) {
        List<ScholarshipContactModel> contacts = scholarshipContactService.findByScholarshipId(scholarshipId);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScholarshipContactResponse> getContactById(@PathVariable Integer id) {
        ScholarshipContactResponse contact = scholarshipContactService.findById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<ScholarshipContactModel> createContact(
            @Valid @RequestBody ScholarshipContactRequest request
    ) {
        ScholarshipContactModel contact = scholarshipContactService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScholarshipContactModel> updateContact(
            @PathVariable Integer id,
            @Valid @RequestBody ScholarshipContactRequest request
    ) {
        ScholarshipContactModel contact = scholarshipContactService.update(id, request);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        scholarshipContactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
