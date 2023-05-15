package com.example.gym1.BootStrap;

import com.example.gym1.Model.Role;
import com.example.gym1.Repo.RoleRepo;
import com.example.gym1.Repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {
    private final UserRepo userRepository;

    private final RoleRepo roleRepository;

    public LoadData(UserRepo userRepository, RoleRepo roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }




    @Override
    public void run(String... args) throws Exception {
        saveUsers();

    }

    private void saveUsers() {
        if (userRepository.count() == 0) {
            Role savedUserRole = roleRepository.save(new Role("USER"));

            Role savedAdminRole = roleRepository.save(new Role("ADMIN"));

        }
    }
}
