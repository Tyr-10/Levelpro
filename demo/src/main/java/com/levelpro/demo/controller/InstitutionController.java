package com.levelpro.demo.controller;

import com.levelpro.demo.model.Institution;
import com.levelpro.demo.repository.InstitutionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/super/institutions/create")
    public String createInstitutionForm() {
        return "create-institution";
    }

    @PostMapping("/super/institutions/save")
    public String saveInstitution(
            @RequestParam String name,
            @RequestParam String domain
    ) {
        Institution institution = new Institution();
        institution.setName(name);
        institution.setDomain(domain);
        institution.setActive(true);

        institutionRepository.save(institution);
        return "redirect:/super/home";
    }
}
