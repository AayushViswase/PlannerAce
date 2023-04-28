package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Models.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
