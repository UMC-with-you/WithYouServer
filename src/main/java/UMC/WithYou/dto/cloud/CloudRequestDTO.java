package UMC.WithYou.dto.cloud;

import UMC.WithYou.common.validation.annotation.ExistClouds;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CloudRequestDTO {

    @Data
    public static class CloudJoinDto{
        @NotNull
        LocalDate date;
        Long travelId;
    }

    @Getter
    public static class DeleteDto{
        @ExistClouds
        Long cloudId;
        List<String> files;
    }

}
