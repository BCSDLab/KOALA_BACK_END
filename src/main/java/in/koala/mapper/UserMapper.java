package in.koala.mapper;

import in.koala.domain.User;
import in.koala.domain.naverLogin.NaverUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    String test();
    void signUp(User user);
    void snsSingUp(User user);
    Long getIdBySnsEmail(String email);
    User getUserByAccount(String account);
    Integer checkNickname(String nickname);
}
