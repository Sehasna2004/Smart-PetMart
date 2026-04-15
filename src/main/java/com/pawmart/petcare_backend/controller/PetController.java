package com.pawmart.petcare_backend.controller;

import com.pawmart.petcare_backend.model.Pet;
import com.pawmart.petcare_backend.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "http://localhost:5173")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    // PHYSICAL LOCATION: Where the file actually sits on your hard drive
    private final String UPLOAD_DIR = "C:" + File.separator + "pawmart_uploads" + File.separator;

    @PostMapping("/add")
    public Pet addPet(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("breed") String breed,
            @RequestParam("age") int age,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) throws IOException {

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Clean filename: Replace spaces with underscores so URLs don't break
        String originalName = image.getOriginalFilename();
        String safeName = (originalName != null) ? originalName.replace(" ", "_") : "pet.jpg";
        String fileName = System.currentTimeMillis() + "_" + safeName;

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Pet pet = new Pet();
        pet.setName(name);
        pet.setType(type);
        pet.setBreed(breed);
        pet.setAge(age);
        pet.setDescription(description);

        // VIRTUAL PATH: This is what the browser will use in the <img> tag
        pet.setImageUrl("/images/" + fileName);

        return petRepository.save(pet);
    }

    @GetMapping("/all")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}