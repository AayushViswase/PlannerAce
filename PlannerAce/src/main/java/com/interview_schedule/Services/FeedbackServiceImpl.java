package com.interview_schedule.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview_schedule.Exceptions.CandidateNotFoundException;
import com.interview_schedule.Exceptions.InterviewNotFoundException;
import com.interview_schedule.Exceptions.InterviewerNotFoundException;
import com.interview_schedule.Models.Candidate;
import com.interview_schedule.Models.FeedBack;
import com.interview_schedule.Models.Interview;
import com.interview_schedule.Models.Interviewer;
import com.interview_schedule.Payload.Request.FeedbackRequest;
import com.interview_schedule.Repositories.CandidateRepository;
import com.interview_schedule.Repositories.FeedBackRepository;
import com.interview_schedule.Repositories.InterviewRepository;
import com.interview_schedule.Repositories.InterviewerRepository;





@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedBackRepository feedbackRepository;

	@Autowired
	private InterviewRepository interviewRepository;

	@Autowired
	private InterviewerRepository interviewerRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Override
	public FeedBack addFeedback(Long InterviewId, Long interviewerId, FeedbackRequest feedbackRequest)
			throws InterviewNotFoundException, InterviewerNotFoundException, CandidateNotFoundException {
		Interview interview = interviewRepository.findById(InterviewId)
				.orElseThrow(() -> new InterviewNotFoundException("Interview not found"));

		Interviewer interviewer = interviewerRepository.findById(interviewerId)
				.orElseThrow(() -> new InterviewerNotFoundException("Interviewer not found"));

		FeedBack feedback = new FeedBack();
		feedback.setInterview(interview);
		feedback.setInterviewer(interviewer);
		feedback.setCandidate(interview.getCandidate());
		feedback.setComments(feedbackRequest.getComments());
		feedback.setRating(feedbackRequest.getRating());

		return feedbackRepository.save(feedback);
	}

	@Override
	public FeedBack getFeedbackByInterviewId(Long interviewId) throws InterviewNotFoundException {
		Interview interview = interviewRepository.findById(interviewId)
				.orElseThrow(() -> new InterviewNotFoundException("Interview not found"));

		return feedbackRepository.findByInterview(interview);
	}

	@Override
	public List<FeedBack> getFeedbackByInterviewerId(Long interviewerId) throws InterviewerNotFoundException {
		Interviewer interviewer = interviewerRepository.findById(interviewerId)
				.orElseThrow(() -> new InterviewerNotFoundException("Interviewer not found"));

		return feedbackRepository.findByInterviewer(interviewer);
	}

	@Override
	public List<FeedBack> getFeedbackByCandidateId(Long candidateId) throws CandidateNotFoundException {
		Candidate candidate = candidateRepository.findById(candidateId)
				.orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));

		return feedbackRepository.findByCandidate(candidate);
	}
}

