package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByEmail(String email);
}
