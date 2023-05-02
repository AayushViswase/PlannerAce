package com.interview_schedule.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview_schedule.Models.Candidate;
import com.interview_schedule.Models.FeedBack;
import com.interview_schedule.Models.Interview;
import com.interview_schedule.Models.Interviewer;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
	FeedBack findByInterview(Interview interview);

	List<FeedBack> findByInterviewer(Interviewer interviewer);

	List<FeedBack> findByCandidate(Candidate candidate);
}
