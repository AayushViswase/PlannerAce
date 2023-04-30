package Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Exceptions.CandidateNotFoundException;
import Exceptions.InterviewNotFoundException;
import Exceptions.InterviewerNotFoundException;
import Models.Candidate;
import Models.FeedBack;
import Models.Interview;
import Models.Interviewer;
import Payload.Request.FeedbackRequest;
import Repositories.CandidateRepository;
import Repositories.FeedBackRepository;
import Repositories.InterviewRepository;
import Repositories.InterviewerRepository;



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

