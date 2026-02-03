package com.levelpro.demo.controller;

import com.levelpro.demo.model.Institution;
import com.levelpro.demo.model.Role;
import com.levelpro.demo.model.User;
import com.levelpro.demo.repository.InstitutionRepository;
import com.levelpro.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class SuperAdminController {

    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SuperAdminController(
            InstitutionRepository institutionRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /* ===============================
       SELECCIÓN DE TIPO DE USUARIO
       =============================== */

    @GetMapping("/super/users/create")
    public String selectUserType() {
        return "select-user-type";
    }

    /* ===============================
       FORMULARIOS DE CREACIÓN
       =============================== */

    @GetMapping("/super/users/create/institution-admin")
    public String createInstitutionAdmin(Model model) {
        model.addAttribute("role", Role.INSTITUTION_ADMIN);
        model.addAttribute("institutions", institutionRepository.findAll());
        return "create-user";
    }

    @GetMapping("/super/users/create/teacher")
    public String createTeacher(Model model) {
        model.addAttribute("role", Role.DOCENTE);
        model.addAttribute("institutions", institutionRepository.findAll());
        return "create-user";
    }

    @GetMapping("/super/users/create/student")
    public String createStudent(Model model) {
        model.addAttribute("role", Role.ESTUDIANTE);
        model.addAttribute("institutions", institutionRepository.findAll());
        return "create-user";
    }

    /* ===============================
       GUARDAR USUARIO
       =============================== */

    @PostMapping("/super/users/save")
public String saveUser(
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam Role role,
        @RequestParam Long institutionId,
        RedirectAttributes redirectAttributes
) {
    Institution institution = institutionRepository.findById(institutionId)
            .orElseThrow(() -> new RuntimeException("Institución no encontrada"));

    User user = new User();
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password)); // 🔐 AQUÍ
    user.setRole(role);
    user.setInstitution(institution);
    user.setActive(true);

    userRepository.save(user);

    redirectAttributes.addFlashAttribute(
            "success",
            "Usuario creado correctamente"
    );

    return "redirect:/super/home";
}

}
