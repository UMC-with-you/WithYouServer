package UMC.WithYou.repository.cloud;

import UMC.WithYou.domain.cloud.Cloud;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static UMC.WithYou.domain.cloud.QCloud.cloud;
import static UMC.WithYou.domain.cloud.QCloudMedia.cloudMedia;

@Repository
@AllArgsConstructor
public class CloudCustomRepositoryImpl implements CloudCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Cloud findByTravelFetchJoinCloud(Long cloudId){
        return jpaQueryFactory
                .selectFrom(cloud)
                .where(cloud.id.eq(cloudId))
                .leftJoin(cloud.pictureDate, cloudMedia).fetchJoin()
                .fetchOne();
    }

}
