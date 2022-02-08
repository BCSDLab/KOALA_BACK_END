package in.koala.service;

import in.koala.domain.Notice;

import java.util.List;

public interface HistoryService {

    List<Notice> getEveryNotice(int pageNum, String sortType);
    Boolean deleteNotice(List<Integer> noticeList);
    Boolean deleteNoticeUndo(List<Integer> noticeList);
    Boolean noticeRead(String noticeId);
}
