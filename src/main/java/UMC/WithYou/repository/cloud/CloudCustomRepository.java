package UMC.WithYou.repository.cloud;

import UMC.WithYou.domain.cloud.Cloud;

public interface CloudCustomRepository {
    Cloud findByTravelFetchJoinCloud(Long cloudId);
}
