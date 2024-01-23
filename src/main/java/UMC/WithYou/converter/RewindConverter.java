package UMC.WithYou.converter;

import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.domain.rewind.RewindQna;
import UMC.WithYou.dto.RewindRequest;
import UMC.WithYou.dto.RewindResponse;

import java.util.List;

public class RewindConverter {

    public static Rewind toRewind(RewindRequest.CreateRewindDto requestDto) {
        return Rewind.builder()
                .day(requestDto.getDay())
                .mvpCandidateId(requestDto.getMvpCandidateId())
                .mood(requestDto.getMood())
                .comment(requestDto.getComment()).build();
    }

    public static RewindResponse.CreateRewindResultDto tocreateRewindResultDto(Rewind rewind) {
        return RewindResponse.CreateRewindResultDto.builder()
                .rewindId(rewind.getId())
                .createdAt(rewind.getCreatedAt()).build();
    }
}
