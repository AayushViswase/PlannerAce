package com.interview_schedule.Repositories;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview_schedule.Models.Candidate;
import com.interview_schedule.Models.Interview;
import com.interview_schedule.Models.Interviewer;


public interface InterviewRepository extends JpaRepository<Interview, Long> {

	List<Interview> findByCandidate(Candidate candidate);

	List<Interview> findByInterviewer(Interviewer interviewer);

	List<Interview> findByInterviewerAndStartTimeBetween(Interviewer interviewer, LocalDateTime interviewStartTime,
			LocalDateTime interviewEndTime);

	Collection<? extends Interview> findByCandidateAndStartTimeBetween(Candidate candidate,
			LocalDateTime interviewStartTime, LocalDateTime interviewEndTime);

	List<Interview> findByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

}
