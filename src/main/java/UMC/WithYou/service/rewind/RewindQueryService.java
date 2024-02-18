package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.domain.rewind.RewindQuestion;

import java.util.List;

public interface RewindQueryService {
    List<Rewind> retrieveRewindsInTravel(Member member, Long travelId, Integer day);
    Rewind retrieveRewindById(Member member, Long travelId, Long rewindId);
    boolean checkRewindIdExist(Long rewindId);
    List<RewindQuestion> retrieveAllRewindQuestions();
}
