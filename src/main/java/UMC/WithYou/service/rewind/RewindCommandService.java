package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.dto.RewindRequest;

public interface RewindCommandService {

    Rewind createRewind(String token, Long travelId, RewindRequest.CreateRewindDto requestDto);

}
