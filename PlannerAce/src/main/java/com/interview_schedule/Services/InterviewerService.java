package com.interview_schedule.Services;

import java.util.List;

import com.interview_schedule.Exceptions.InterviewerNotFoundException;
import com.interview_schedule.Models.Interviewer;



public interface InterviewerService {
	List<Interviewer> getAllInterviewers();

	Interviewer getInterviewerById(Long interviewerId) throws InterviewerNotFoundException;

	Interviewer saveInterviewer(Interviewer interviewer);

	Interviewer updateInterviewer(Long interviewerId, Interviewer updatedInterviewer)
			throws InterviewerNotFoundException;

	void deleteInterviewer(Long interviewerId) throws InterviewerNotFoundException;
}

