package UMC.WithYou.dto.cloud;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CloudRequestDTO {

    @Data
    public static class JoinDto{
        @NotNull
        LocalDate date;
        Long travelId;
    }

}
