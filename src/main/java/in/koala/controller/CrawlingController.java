package in.koala.controller;

import in.koala.annotation.ValidationGroups;
import in.koala.domain.CrawlingToken;
import in.koala.controller.response.BaseResponse;
import in.koala.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping(value = "/crawling/test")
    public String test() {
        return crawlingService.test();
    }

    @GetMapping(value="/crawling/portal")
    public ResponseEntity portalCrawling() throws Exception{
        Timestamp crawlingAt = new Timestamp(System.currentTimeMillis());
        if (crawlingService.portalCrawling(crawlingAt))
            return new ResponseEntity(BaseResponse.of("아우누리 크롤링에 성공하였습니다.", HttpStatus.OK), HttpStatus.OK);
        else
            return new ResponseEntity(BaseResponse.of("아우누리 크롤링에 실패했습니다.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/crawling/dorm")
    public ResponseEntity dormCrawling() throws Exception{
        Timestamp crawlingAt = new Timestamp(System.currentTimeMillis());
        if (crawlingService.dormCrawling(crawlingAt))
            return new ResponseEntity(BaseResponse.of("아우미르 크롤링에 성공하였습니다.", HttpStatus.OK), HttpStatus.OK);
        else
            return new ResponseEntity(BaseResponse.of("아우미르 크롤링에 실패했습니다.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/crawling/youtube")
    public ResponseEntity youtubeCrawling() throws Exception{
        Timestamp crawlingAt = new Timestamp(System.currentTimeMillis());
        if (crawlingService.youtubeCrawling(crawlingAt))
            return new ResponseEntity(BaseResponse.of("유튜브 크롤링에 성공하였습니다.", HttpStatus.OK), HttpStatus.OK);
        else
            return new ResponseEntity(BaseResponse.of("유튜브 크롤링에 실패했습니다.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/crawling/facebook/{token-id}")
    public ResponseEntity facebookCrawling(@PathVariable(name = "token-id") Long tokenId) throws Exception{
        Timestamp crawlingAt = new Timestamp(System.currentTimeMillis());
        if (crawlingService.facebookCrawling(tokenId, crawlingAt))
            return new ResponseEntity(BaseResponse.of("페이스북 크롤링에 성공하였습니다.", HttpStatus.OK), HttpStatus.OK);
        else
            return new ResponseEntity(BaseResponse.of("페이스북 크롤링에 실패했습니다.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/crawling/instagram/{token-id}")
    public ResponseEntity instagramCrawling(@PathVariable(name = "token-id") Long tokenId) throws Exception{
        Timestamp crawlingAt = new Timestamp(System.currentTimeMillis());
        if (crawlingService.instagramCrawling(tokenId, crawlingAt))
            return new ResponseEntity(BaseResponse.of("인스타그램 크롤링에 성공하였습니다.", HttpStatus.OK), HttpStatus.OK);
        else
            return new ResponseEntity(BaseResponse.of("인스타그램 크롤링에 실패했습니다.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    // 토큰 관련 API
    @PostMapping(value="/crawling/token")
    public ResponseEntity addToken(@Validated(ValidationGroups.createCrawlingToken.class)
                                       @RequestBody CrawlingToken token) throws Exception{
        crawlingService.addCrawlingToken(token);
        return new ResponseEntity(BaseResponse.of("토큰 추가 완료", HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value="/crawling/token")
    public ResponseEntity getToken() throws Exception {
        return new ResponseEntity (BaseResponse.of(crawlingService.getCrawlingToken(), HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping(value="/crawling/token")
    public ResponseEntity updateToken(@Validated(ValidationGroups.updateCrawlingToken.class)
                                @RequestBody CrawlingToken token) throws Exception {
        crawlingService.updateCrawlingToken(token);
        return new ResponseEntity(BaseResponse.of("토큰 수정 완료", HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping(value="/crawling/token/{token-id}")
    public ResponseEntity deleteToken(@PathVariable(name = "token-id") Long tokenId) throws Exception {
        crawlingService.deleteCrawlingToken(tokenId);
        return new ResponseEntity(BaseResponse.of("토큰 삭제 완료", HttpStatus.OK), HttpStatus.OK);
    }
}