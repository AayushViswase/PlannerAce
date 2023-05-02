package com.interview_schedule.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview_schedule.Exceptions.CandidateNotFoundException;
import com.interview_schedule.Exceptions.InterviewNotFoundException;
import com.interview_schedule.Exceptions.InterviewerNotFoundException;
import com.interview_schedule.Models.FeedBack;
import com.interview_schedule.Models.Interview;
import com.interview_schedule.Models.Interviewer;
import com.interview_schedule.Payload.Request.FeedbackRequest;
import com.interview_schedule.Payload.Request.InterviewerRequest;
import com.interview_schedule.Payload.Response.InterviewerResponse;
import com.interview_schedule.Services.FeedbackService;
import com.interview_schedule.Services.InterviewService;
import com.interview_schedule.Services.InterviewerService;



@RestController
@RequestMapping("/planner-ace/interviewer")
public class InterviewerController {

	private InterviewerService interviewerService;
	private FeedbackService feedbackService;
	private InterviewService interviewService;

	public InterviewerController(InterviewerService interviewerService, FeedbackService feedbackService,
			InterviewService interviewService) {
		this.interviewerService = interviewerService;
		this.feedbackService = feedbackService;
		this.interviewService = interviewService;
	}

	@PostMapping("/add_interviewer")
	public ResponseEntity<InterviewerResponse> registerInterviewer(@RequestBody InterviewerRequest interviewerRequest) {
		ModelMapper mapper = new ModelMapper();
		Interviewer interviewer = mapper.map(interviewerRequest, Interviewer.class);
		Interviewer newInterviewer = interviewerService.saveInterviewer(interviewer);
		InterviewerResponse response = mapper.map(newInterviewer, InterviewerResponse.class);
		return new ResponseEntity<InterviewerResponse>(response, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<InterviewerResponse> getInterviewerById(@PathVariable("id") Long interviewerId)
			throws InterviewerNotFoundException {
		Interviewer interviewer = interviewerService.getInterviewerById(interviewerId);
		ModelMapper mapper = new ModelMapper();
		InterviewerResponse response = mapper.map(interviewer, InterviewerResponse.class);
		return new ResponseEntity<InterviewerResponse>(response, HttpStatus.OK);

	}

	@PutMapping("/{interviewerId}/update")
	public ResponseEntity<Interviewer> updateInterviewer(@PathVariable Long interviewerId,
			@RequestBody Interviewer interviewer) throws InterviewerNotFoundException {
		Interviewer updatedInterviewer = interviewerService.updateInterviewer(interviewerId, interviewer);
		return new ResponseEntity<Interviewer>(updatedInterviewer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{interviewerId}")
	public ResponseEntity<String> deleteInterviewer(@PathVariable Long interviewerId)
			throws InterviewerNotFoundException {
		interviewerService.deleteInterviewer(interviewerId);
		String message = "Interviewer Deleted sucessfully";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping("/{interviewerId}/feedback/{interviewId}")
	public ResponseEntity<FeedBack> addFeedback(@PathVariable Long interviewerId, @PathVariable Long interviewId,
			@RequestBody FeedbackRequest feedbackRequest)
					throws InterviewNotFoundException, InterviewerNotFoundException, CandidateNotFoundException {
		FeedBack feedback = feedbackService.addFeedback(interviewId, interviewerId, feedbackRequest);
		return new ResponseEntity<FeedBack>(feedback, HttpStatus.CREATED);
	}

	@GetMapping("/get/interviews/{interviewId}")
	// TODO add a checking for the interview is of the interviewer requesting.
	public ResponseEntity<Interview> getInterviewById(@PathVariable Long interviewId)
			throws InterviewNotFoundException {
		Interview interview = interviewService.getInterviewById(interviewId);
		return new ResponseEntity<Interview>(interview, HttpStatus.OK);
	}

	@GetMapping("/{interviewerId}/interviews/{interviewStatus}")
	public ResponseEntity<List<Interview>> getAllInterviews(@PathVariable Long interviewerId,
			@PathVariable String interviewStatus) {

		List<Interview> interviews = interviewService.getInterviewsByInterviewerId(interviewerId).stream()
				.filter(i -> i.getStatus().toString().equals(interviewStatus.toUpperCase()))
				.collect(Collectors.toList());

		return new ResponseEntity<List<Interview>>(interviews, HttpStatus.OK);

	}
}
