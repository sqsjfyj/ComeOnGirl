package com.qwj.girl.crawler.mysql;

import com.qwj.girl.dao.mysql.BadGirlDao;
import com.qwj.girl.entity.mysql.BadGirl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.annotation.Resource;
import java.util.*;

@Component
public class UncensoredActresses implements PageProcessor {

    @Resource
    public BadGirlDao badGirlDao;

    @Value("${spring.proxy.username}")
    public String username;

    @Value("${spring.proxy.password}")
    public String password;

    @Value("#{'${spring.proxy.ipandports}'.split(',')}")
    public List<String> ipAndPorts = new ArrayList<>();

    public String baseUrl = "https://www.javbus.com";

    public String startLink = "https://www.javbus.com/uncensored/actresses/1";

    public Map<String, BadGirl> map = new HashMap<>();

    public Site site = Site.me().setCycleRetryTimes(3).setTimeOut(60000).setSleepTime(20).setCharset("UTF-8");

    @Override
    public void process(Page page) {
        String url = page.getUrl().toString();
        if (url.contains("uncensored/actresses")) {
            Document document = Jsoup.parse(page.getRawText().trim());
            Elements divs = document.select("div#waterfall > div:has(a)");
            for (Element div : divs) {
                BadGirl badGirl = new BadGirl();
                Element a = div.selectFirst("a");
                String href = a.attr("href").trim();
                String code = href.substring(href.lastIndexOf("/") + 1).trim();
                int count = badGirlDao.countByCode(code);
                if (count > 0) {
                    continue;
                }
                badGirl.setCode(code);
                badGirl.setDetailLink(href);
                map.put(href, badGirl);
                page.addTargetRequest(href);
            }
            if (document.selectFirst("a:contains(?????????)") != null) {
                String nextLink = document.selectFirst("a:contains(?????????)").attr("href").trim();
                nextLink = baseUrl + nextLink;
                page.addTargetRequest(nextLink);
            }
        } else {
            if (page.getStatusCode() == 404) {
                return;
            }
            BadGirl badGirl = map.get(url);
            Document document = Jsoup.parse(page.getRawText().trim());
            Element div = document.selectFirst("div.avatar-box > div.photo-info");
            String name = div.selectFirst("span.pb10").text().trim();
            badGirl.setName(name);
            if (div.selectFirst("p:contains(??????:)") != null) {
                String birthday = div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim();
                badGirl.setBirthday(birthday);
            }
            if (div.selectFirst("p:contains(??????:)") != null) {
                int age = Integer.valueOf(div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim());
                badGirl.setAge(age);
            }
            if (div.selectFirst("p:contains(??????:)") != null) {
                String height = div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim();
                badGirl.setHeight(height);
            }
            if (div.selectFirst("p:contains(??????:)") != null) {
                String cup = div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim();
                badGirl.setCup(cup);
            }
            if (div.selectFirst("p:contains(??????:)") != null) {
                String bust = div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim();
                badGirl.setBust(bust);
            }
            if (div.selectFirst("p:contains(??????:)") != null) {
                String waistline = div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim();
                badGirl.setWaistline(waistline);
            }
            if (div.selectFirst("p:contains(??????:)") != null) {
                String hips = div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim();
                badGirl.setHips(hips);
            }
            if (div.selectFirst("p:contains(??????:)") != null) {
                String hobby = div.selectFirst("p:contains(??????:)").text().replace("??????:", "").trim();
                badGirl.setHobby(hobby);
            }
            badGirlDao.save(badGirl);
        }
    }

    @Override
    public Site getSite() {
        Set<Integer> set = new HashSet<>();
        set.add(200);
        set.add(404);
        return this.site.setAcceptStatCode(set).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
    }

    public void startGo() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        for (String ipAndPort : ipAndPorts) {
            String ip = ipAndPort.split(":")[0].trim();
            int port = Integer.valueOf(ipAndPort.split(":")[1].trim());
            Proxy proxy = new Proxy(ip, port, username, password);
            proxy.setScheme("https");
            httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(proxy));
        }
        Request request = new Request(startLink);
        Spider spider = Spider.create(this).addRequest(request).thread(5).setDownloader(httpClientDownloader);
        spider.start();
    }

}
