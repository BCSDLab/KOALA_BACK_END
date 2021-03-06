package in.koala.serviceImpl;

import in.koala.domain.Crawling;
import in.koala.domain.Scrap;
import in.koala.domain.user.User;
import in.koala.enums.ErrorMessage;
import in.koala.exception.NonCriticalException;
import in.koala.mapper.ScrapMapper;
import in.koala.service.ScrapService;
import in.koala.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service
public class ScrapServiceImpl implements ScrapService {
    @Resource
    ScrapMapper scrapMapper;

    @Resource
    UserService userService;

    // 보관함으로 이동
    @Override
    public void Scrap(Scrap scrap) throws Exception {

        User user = userService.getLoginUserInfo();
        if(user == null){
            throw new NonCriticalException(ErrorMessage.USER_NOT_EXIST);
        }
        Long userId = user.getId();

        if(!scrapMapper.checkBoardExist(scrap.getCrawling_id())){
            throw new NonCriticalException(ErrorMessage.BOARD_NOT_EXIST);
        }

        if(scrapMapper.checkAlreadyScraped(userId, scrap.getCrawling_id()) != 0 ){
            throw new NonCriticalException(ErrorMessage.ALREADY_SCRAP_BOARD);
        }
        scrapMapper.scrapBoard(userId, scrap.getCrawling_id());
    }

    // 보관함 조회
    @Override
    public List<Crawling> getScrap() throws Exception {
        User user = userService.getLoginUserInfo();
        if(user == null){
            throw new NonCriticalException(ErrorMessage.USER_NOT_EXIST);
        }

        return scrapMapper.getScrapList(user.getId());
    }

    // 보관함 선택 삭제
    @Override
    public void deleteScrap(List<Long> boardId) throws Exception {
        User user = userService.getLoginUserInfo();
        if(user == null){
            throw new NonCriticalException(ErrorMessage.USER_NOT_EXIST);
        }

        for(int i=0; i<boardId.size(); i++ ){
            if(!scrapMapper.checkBoardExist(boardId.get(i))) {
                throw new NonCriticalException(ErrorMessage.BOARD_NOT_EXIST);
            }
            if(!scrapMapper.checkScrapExist(boardId.get(i))) {
                throw new NonCriticalException(ErrorMessage.SCRAP_NOT_EXIST);
            }
        }
        scrapMapper.deleteScrap(boardId);
    }

}
