package cse.skku.edu.dailycs.repository;

import cse.skku.edu.dailycs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
