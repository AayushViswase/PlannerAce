package Services;

import java.util.List;

import Exceptions.CandidateNotFoundException;
import Models.Candidate;

public interface CandidateService {

	public Candidate createCandidate(Candidate candidate);

	public Candidate getCandidateById(Long id) throws CandidateNotFoundException;

	public List<Candidate> getAllCandidates();

	public Candidate updateCandidate(Long id, Candidate candidate) throws CandidateNotFoundException;

	public void deleteCandidate(Long id) throws CandidateNotFoundException;

}
