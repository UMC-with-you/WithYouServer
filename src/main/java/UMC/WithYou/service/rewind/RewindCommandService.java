package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.dto.rewind.RewindRequest;

public interface RewindCommandService {
    Rewind createRewind(String token, Long travelId, RewindRequest.CreateRewindDto requestDto);
    Rewind updateRewindById(String token, Long travelId, Long rewindId, RewindRequest.UpdateRewindDto requestDto);
    void deleteRewindById(String token, Long travelId, Long rewindId);
}
