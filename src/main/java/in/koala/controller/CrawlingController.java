package in.koala.controller;

import in.koala.service.CrawlingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/crawling")
public class CrawlingController {

    @Resource(name = "crawlingServiceImpl")
    private CrawlingService crawlingService;

    @GetMapping(value = "/dorm")
    public void dormCrawling() throws Exception{
        crawlingService.dormCrawling();
    }

    @GetMapping(value="/youtube")
    public void youtubeCrawling() throws Exception{
        crawlingService.youtubeCrawling();
    }

    @GetMapping(value="/portal")
    public void portalCrawling() throws Exception{
        crawlingService.portalCrawling();
    }

    @GetMapping(value = "/test")
    public String test() {
        return crawlingService.test();
    }
}