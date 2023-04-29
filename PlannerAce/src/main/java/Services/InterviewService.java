package Services;

import java.time.LocalDateTime;
import java.util.List;

import Exceptions.CandidateNotFoundException;
import Exceptions.InterviewNotFoundException;
import Exceptions.InterviewScheduleConflictException;
import Exceptions.InterviewerNotFoundException;
import Models.Interview;
import Payload.Request.NewInterviewRequest;

public interface InterviewService {

	List<Interview> getAllInterviews();

	Interview getInterviewById(Long interviewId) throws InterviewNotFoundException;

	List<Interview> getInterviewsByCandidateId(Long candidateId);

	List<Interview> getInterviewsByInterviewerId(Long interviewerId);

	List<Interview> getInterviewsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

	Interview scheduleInterview(NewInterviewRequest newInterviewRequest)
			throws InterviewScheduleConflictException, CandidateNotFoundException, InterviewerNotFoundException;

	Interview updateInterview(Interview interview);

	void deleteInterviewById(Long interviewId);
}

