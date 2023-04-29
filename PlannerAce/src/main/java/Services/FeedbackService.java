package Services;

import java.util.List;

import Exceptions.CandidateNotFoundException;
import Exceptions.InterviewNotFoundException;
import Exceptions.InterviewerNotFoundException;
import Models.FeedBack;
import Payload.Request.FeedbackRequest;


public interface FeedbackService {
	FeedBack addFeedback(Long InterviewId, Long interviewerId, FeedbackRequest feedbackRequest)
			throws InterviewNotFoundException, InterviewerNotFoundException, CandidateNotFoundException;

	FeedBack getFeedbackByInterviewId(Long interviewId) throws InterviewNotFoundException;

	List<FeedBack> getFeedbackByInterviewerId(Long interviewerId) throws InterviewerNotFoundException;

	List<FeedBack> getFeedbackByCandidateId(Long candidateId) throws CandidateNotFoundException;
}

