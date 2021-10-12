package in.koala.service;

import in.koala.domain.Memo;

public interface MemoService {
    void addMemo(Memo memo) throws Exception;
    void deleteMemo(Memo memo) throws Exception;
    void updateMemo(Memo memo) throws Exception;
    String getMemo(Long user_scrap_id) throws Exception;
}