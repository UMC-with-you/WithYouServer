package UMC.WithYou.service.cloud;

import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.dto.cloud.CloudRequestDTO;

public interface CloudService {
    Cloud createCloud(CloudRequestDTO.JoinDto request);
}
