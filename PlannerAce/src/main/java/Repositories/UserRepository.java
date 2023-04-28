package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
