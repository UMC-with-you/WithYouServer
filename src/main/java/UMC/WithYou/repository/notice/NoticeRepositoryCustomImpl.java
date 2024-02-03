package UMC.WithYou.repository.notice;

import UMC.WithYou.domain.notice.Notice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static UMC.WithYou.domain.member.QMember.member;
import static UMC.WithYou.domain.notice.QNotice.notice;

@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Notice> findByTravelLogFetchJoinMember(Long travelId){
        return jpaQueryFactory
                .selectFrom(notice)
                .join(notice.member,member).fetchJoin()
                .where(notice.travel.id.eq(travelId))
                .fetch();
    }
}
