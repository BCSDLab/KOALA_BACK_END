package in.koala.serviceImpl;

import in.koala.domain.Memo;
import in.koala.domain.user.User;
import in.koala.enums.ErrorMessage;
import in.koala.exception.NonCriticalException;
import in.koala.mapper.MemoMapper;
import in.koala.mapper.ScrapMapper;
import in.koala.service.MemoService;
import in.koala.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class MemoServiceImpl implements MemoService {

    @Resource
    UserService userService;

    @Resource
    MemoMapper memoMapper;

    @Resource
    ScrapMapper scrapMapper;

    @Override
    public void addMemo(Memo memo) throws Exception {
        User user = userService.getLoginUserInfo();
        if(user == null){
            throw new NonCriticalException(ErrorMessage.USER_NOT_EXIST);
        }

        if(!scrapMapper.checkScrapExistByMemo(user.getId(), memo.getUser_scrap_id())){
            throw new NonCriticalException(ErrorMessage.SCRAP_NOT_EXIST);
        }

        if(memoMapper.checkMemoExist(memo.getUser_scrap_id())){
            throw new NonCriticalException(ErrorMessage.ALREADY_MEMO_EXIST);
        }

        memoMapper.addMemo(memo);
    }

    @Override
    public List<Memo> getMemo() throws Exception{
        User user = userService.getLoginUserInfo();
        if(user == null){
            throw new NonCriticalException(ErrorMessage.USER_NOT_EXIST);
        }

        return memoMapper.getMemo(user.getId());
    }

    @Override
    public void updateMemo(Memo memo) throws Exception{
        User user = userService.getLoginUserInfo();
        if(user == null){
            throw new NonCriticalException(ErrorMessage.USER_NOT_EXIST);
        }

        if(!scrapMapper.checkScrapExistByMemo(user.getId(), memo.getUser_scrap_id())){
            throw new NonCriticalException(ErrorMessage.SCRAP_NOT_EXIST);
        }

        if(!memoMapper.checkMemoExist(memo.getUser_scrap_id())){
            throw new NonCriticalException(ErrorMessage.MEMO_NOT_EXIST);
        }

        memoMapper.updateMemo(memo);
    }

}
