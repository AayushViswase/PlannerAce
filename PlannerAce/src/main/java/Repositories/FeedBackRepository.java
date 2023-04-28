package Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Models.Candidate;
import Models.FeedBack;
import Models.Interview;
import Models.Interviewer;

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
	FeedBack findByInterview(Interview interview);

	List<FeedBack> findByInterviewer(Interviewer interviewer);

	List<FeedBack> findByCandidate(Candidate candidate);
}
