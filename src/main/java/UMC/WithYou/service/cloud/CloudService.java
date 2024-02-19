package UMC.WithYou.service.cloud;

import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.dto.cloud.CloudRequestDTO;
import UMC.WithYou.dto.cloud.CloudResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudService {
    Cloud createCloud(CloudRequestDTO.CloudJoinDto request, List<MultipartFile> files);
    List<CloudResponseDTO.PictureDto> getPictures(Long travelLog);
    Cloud deletePictures(Long cloudId, List<String> files);
}
