package com.business_app.authservice.roleinitializer;

import com.business_app.authservice.model.Role;
import com.business_app.authservice.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Roleinit {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findByName("CUSTOMER").isEmpty()) {
            Role role = new Role();
            role.setName("CUSTOMER");
            roleRepository.save(role);
        }
    }
}
