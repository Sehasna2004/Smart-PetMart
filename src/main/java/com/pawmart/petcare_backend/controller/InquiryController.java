package com.pawmart.petcare_backend.controller;

import com.pawmart.petcare_backend.model.Inquiry;
import com.pawmart.petcare_backend.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
@CrossOrigin(origins = "http://localhost:5173")
public class InquiryController {

    @Autowired
    private InquiryRepository inquiryRepository;

    /**
     * POST: Save a new inquiry from the Pet Catalog.
     */
    @PostMapping("/submit")
    public ResponseEntity<?> submitInquiry(@RequestBody Inquiry inquiry) {
        try {
            // Debugging logs for the IntelliJ console
            System.out.println("Processing Inquiry for Pet: " + inquiry.getPetName());
            System.out.println("Submitted by: " + inquiry.getUserName());

            // Save the data to MySQL
            Inquiry savedInquiry = inquiryRepository.save(inquiry);

            // Return 201 Created status
            return new ResponseEntity<>(savedInquiry, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Backend Database Error: " + e.getMessage());
        }
    }

    /**
     * GET: Fetch all inquiries for the Admin Panel.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Inquiry>> getAllInquiries() {
        try {
            List<Inquiry> inquiries = inquiryRepository.findAll();
            return ResponseEntity.ok(inquiries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * DELETE: Remove an inquiry once it has been addressed (The Green Tick logic).
     * This matches the axios.delete call from your frontend.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInquiry(@PathVariable Long id) {
        try {
            // Check if it exists before trying to delete
            if (inquiryRepository.existsById(id)) {
                inquiryRepository.deleteById(id);
                System.out.println("Inquiry with ID " + id + " has been resolved and deleted.");
                return ResponseEntity.ok("Inquiry marked as completed.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquiry not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting inquiry: " + e.getMessage());
        }
    }
}