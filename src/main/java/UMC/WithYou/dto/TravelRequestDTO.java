package UMC.WithYou.dto;

import java.time.LocalDate;
import lombok.Getter;

public class TravelRequestDTO {

    @Getter
    public static class ConfigurationRequestDTO {
        private String title;
        private LocalDate startDate;
        private LocalDate endDate;
        private String url;
    }
}
