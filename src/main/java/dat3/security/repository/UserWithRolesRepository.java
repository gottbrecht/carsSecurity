package dat3.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface
    UserWithRolesRepository extends JpaRepository<UserWithRoles,String> {
        Boolean existsByEmail(String email);
    }



