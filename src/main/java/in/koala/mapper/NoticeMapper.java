package in.koala.mapper;

import in.koala.domain.PushNotice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    void insertNotice(List<PushNotice> pushNoticeList);
}
