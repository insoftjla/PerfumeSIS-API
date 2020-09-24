package ru.perfumess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perfumess.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String name);
}
