package com.interview_schedule.Services;

import java.util.List;

import com.interview_schedule.Exceptions.CandidateNotFoundException;
import com.interview_schedule.Exceptions.InterviewNotFoundException;
import com.interview_schedule.Exceptions.InterviewerNotFoundException;
import com.interview_schedule.Models.FeedBack;
import com.interview_schedule.Payload.Request.FeedbackRequest;



public interface FeedbackService {
	FeedBack addFeedback(Long InterviewId, Long interviewerId, FeedbackRequest feedbackRequest)
			throws InterviewNotFoundException, InterviewerNotFoundException, CandidateNotFoundException;

	FeedBack getFeedbackByInterviewId(Long interviewId) throws InterviewNotFoundException;

	List<FeedBack> getFeedbackByInterviewerId(Long interviewerId) throws InterviewerNotFoundException;

	List<FeedBack> getFeedbackByCandidateId(Long candidateId) throws CandidateNotFoundException;
}

