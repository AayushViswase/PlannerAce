package com.interview_schedule.Services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview_schedule.Enums.InterviewStatus;
import com.interview_schedule.Exceptions.CandidateNotFoundException;
import com.interview_schedule.Exceptions.InterviewNotFoundException;
import com.interview_schedule.Exceptions.InterviewScheduleConflictException;
import com.interview_schedule.Exceptions.InterviewerNotFoundException;
import com.interview_schedule.Models.Candidate;
import com.interview_schedule.Models.Interview;
import com.interview_schedule.Models.Interviewer;
import com.interview_schedule.Payload.Request.NewInterviewRequest;
import com.interview_schedule.Repositories.CandidateRepository;
import com.interview_schedule.Repositories.InterviewRepository;
import com.interview_schedule.Repositories.InterviewerRepository;



@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private InterviewerRepository interviewerRepository;



	@Override
	public List<Interview> getAllInterviews() {
		return interviewRepository.findAll();
	}

	@Override
	public Interview getInterviewById(Long interviewId) throws InterviewNotFoundException {
		return interviewRepository.findById(interviewId).orElseThrow(()-> new InterviewNotFoundException("Interview not found with Id "+interviewId));
	}

	@Override
	public List<Interview> getInterviewsByCandidateId(Long candidateId) {
		Optional<Candidate> candidate = candidateRepository.findById(candidateId);
		return candidate.isPresent() ? interviewRepository.findByCandidate(candidate.get()) : Collections.emptyList();
	}

	@Override
	public List<Interview> getInterviewsByInterviewerId(Long interviewerId) {
		Optional<Interviewer> interviewer = interviewerRepository.findById(interviewerId);
		return interviewer.isPresent() ? interviewRepository.findByInterviewer(interviewer.get())
				: Collections.emptyList();
	}

	@Override
	public List<Interview> getInterviewsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		return interviewRepository.findByStartTimeBetween(startDate, endDate);
	}

	@Override
	public Interview scheduleInterview(NewInterviewRequest interviewRequest)
			throws InterviewScheduleConflictException, CandidateNotFoundException, InterviewerNotFoundException {

		Interview interview =  new Interview();
		interview.setComments(interviewRequest.getComments());
		interview.setStartTime(interviewRequest.getStartTime());
		interview.setEndTime(interviewRequest.getEndTime());
		interview.setLocation(interviewRequest.getLocation());
		interview.setStatus(InterviewStatus.SCHEDULED);

		Candidate candidate = candidateRepository.findById(interviewRequest.getCandidiateId())
				.orElseThrow(() -> new CandidateNotFoundException(
						"Candidate not found with Id " + interviewRequest.getCandidiateId()));

		Interviewer interviewer = interviewerRepository.findById(interviewRequest.getInterviewerId())
				.orElseThrow(() -> new InterviewerNotFoundException(
						"Interviewer Not found with Id " + interviewRequest.getInterviewerId()));

		interview.setCandidate(candidate);
		interview.setInterviewer(interviewer);

		LocalDateTime interviewStartTime = interview.getStartTime();
		LocalDateTime interviewEndTime = interview.getEndTime();

		List<Interview> conflictingInterviews = interviewRepository
				.findByInterviewerAndStartTimeBetween(interview.getInterviewer(), interviewStartTime, interviewEndTime);

		conflictingInterviews.addAll(interviewRepository.findByCandidateAndStartTimeBetween(interview.getCandidate(),
				interviewStartTime, interviewEndTime));

		if (conflictingInterviews.isEmpty())
			return interviewRepository.save(interview);
		else
			throw new InterviewScheduleConflictException("Interview schedule conflict detected");
	}


	@Override
	public Interview updateInterview(Interview interview) {
		return interviewRepository.save(interview);
	}

	@Override
	public void deleteInterviewById(Long interviewId) {
		interviewRepository.deleteById(interviewId);
	}
}
