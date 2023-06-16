package api;

import domain.Proposal;

import java.util.List;

public interface ProposalRepository extends Repository<Integer, Proposal> {
    List<Proposal> getGhicite(Integer gameId, Integer playerId);
}
