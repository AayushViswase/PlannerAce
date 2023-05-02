package com.interview_schedule.Services;

import java.time.LocalDateTime;
import java.util.List;

import com.interview_schedule.Exceptions.CandidateNotFoundException;
import com.interview_schedule.Exceptions.InterviewNotFoundException;
import com.interview_schedule.Exceptions.InterviewScheduleConflictException;
import com.interview_schedule.Exceptions.InterviewerNotFoundException;
import com.interview_schedule.Models.Interview;
import com.interview_schedule.Payload.Request.NewInterviewRequest;



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

