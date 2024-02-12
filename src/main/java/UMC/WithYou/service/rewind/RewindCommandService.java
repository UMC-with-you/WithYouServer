package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.dto.rewind.RewindRequest;

public interface RewindCommandService {
    Rewind createRewind(Member member, Long travelId, RewindRequest.CreateRewindDto requestDto);
    Rewind updateRewindById(Member member, Long travelId, Long rewindId, RewindRequest.UpdateRewindDto requestDto);
    void deleteRewindById(Member member, Long travelId, Long rewindId);
}
