package api;

import domain.ProposedWord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProposedWordRepository extends Repository<Integer, ProposedWord> {
    List<ProposedWord> findByGameId(Integer id);
}
