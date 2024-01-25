package UMC.WithYou.repository.notice;

import UMC.WithYou.domain.notice.Notice;

import java.util.List;

public interface NoticeRepositoryCustom {
    List<Notice> findByTravelLogFetchJoinMember(Long travelId);
}
