package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.rewind.Rewind;

import java.util.List;

public interface RewindQueryService {
    List<Rewind> retrieveRewindsInTravel(String token, Long travelId, Integer day);
    Rewind retrieveRewindById(String token, Long travelId, Long rewindId);
    boolean checkRewindIdExist(Long rewindId);
}
