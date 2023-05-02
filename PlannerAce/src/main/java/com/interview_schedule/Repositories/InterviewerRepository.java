package com.interview_schedule.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview_schedule.Models.Interviewer;

public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

}
