package UMC.WithYou.dto.cloud;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CloudRequestDTO {
    @Getter
    public static class JoinDto{

        //@ExistMember
        Long memberId;

        LocalDate date;

        List<MultipartFile> pictures;
    }
}
