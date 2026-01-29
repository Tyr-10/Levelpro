package com.levelpro.demo.config;

import com.levelpro.demo.model.*;
import com.levelpro.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            UserRepository userRepository,
            InstitutionRepository institutionRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // 1️ Verificar si ya existe un SUPER_ADMIN
            boolean superAdminExists = userRepository.existsByRole(Role.SUPER_ADMIN);
            if (superAdminExists) {
                return;
            }

            // 2️ Crear institución técnica del sistema
            Institution systemInstitution = new Institution();
            systemInstitution.setName("SYSTEM");
            systemInstitution.setDomain("system");
            institutionRepository.save(systemInstitution);

            // 3️ Crear SUPER_ADMIN
            User superAdminJ = new User();
            superAdminJ.setEmail("AdminJuan@gmail.com");
            superAdminJ.setPassword(passwordEncoder.encode("Admin123."));
            superAdminJ.setRole(Role.SUPER_ADMIN);
            superAdminJ.setInstitution(systemInstitution);
            superAdminJ.setActive(true);

            userRepository.save(superAdminJ);



            User superAdminL = new User();
            superAdminL.setEmail("AdminLuis@gmail.com");
            superAdminL.setPassword(passwordEncoder.encode("Admin123."));
            superAdminL.setRole(Role.SUPER_ADMIN);
            superAdminL.setInstitution(systemInstitution);
            superAdminL.setActive(true);

            userRepository.save(superAdminL);

            System.out.println(" SUPER_ADMINS LOS FUKINGS REYES DEL TRAP creados por defecto");
        };
    }
}

