package com.interview_schedule.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview_schedule.Models.Candidate;


public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
