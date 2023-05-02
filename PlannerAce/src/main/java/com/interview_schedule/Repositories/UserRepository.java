package com.interview_schedule.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview_schedule.Models.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
