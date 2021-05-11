package com.qwj.girl.crawler.oracle;

import com.qwj.girl.dao.oracle.BadHookerDao;
import com.qwj.girl.entity.oracle.BadHooker;
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
public class HookerUncensoredActresses implements PageProcessor {

    @Resource
    public BadHookerDao badHookerDao;

    @Value("${spring.proxy.username}")
    public String username;

    @Value("${spring.proxy.password}")
    public String password;

    @Value("#{'${spring.proxy.ipandports}'.split(',')}")
    public List<String> ipAndPorts = new ArrayList<>();

    public String baseUrl = "https://www.javbus.com";

    public String startLink = "https://www.javbus.com/uncensored/actresses/1";

    public Map<String, BadHooker> map = new HashMap<>();

    public Site site = Site.me().setCycleRetryTimes(3).setTimeOut(60000).setSleepTime(20).setCharset("UTF-8");

    @Override
    public void process(Page page) {
        String url = page.getUrl().toString();
        if (url.contains("uncensored/actresses")) {
            Document document = Jsoup.parse(page.getRawText().trim());
            Elements divs = document.select("div#waterfall > div:has(a)");
            for (Element div : divs) {
                BadHooker badHooker = new BadHooker();
                Element a = div.selectFirst("a");
                String href = a.attr("href").trim();
                String code = href.substring(href.lastIndexOf("/") + 1).trim();
                int count = badHookerDao.countByCode(code);
                if (count > 0) {
                    continue;
                }
                badHooker.setCode(code);
                badHooker.setDetailLink(href);
                map.put(href, badHooker);
                page.addTargetRequest(href);
            }
            if (document.selectFirst("a:contains(下一頁)") != null) {
                String nextLink = document.selectFirst("a:contains(下一頁)").attr("href").trim();
                nextLink = baseUrl + nextLink;
                page.addTargetRequest(nextLink);
            }
        } else {
            if (page.getStatusCode() == 404) {
                return;
            }
            BadHooker badHooker = map.get(url);
            Document document = Jsoup.parse(page.getRawText().trim());
            Element div = document.selectFirst("div.avatar-box > div.photo-info");
            String name = div.selectFirst("span.pb10").text().trim();
            badHooker.setName(name);
            if (div.selectFirst("p:contains(生日:)") != null) {
                String birthday = div.selectFirst("p:contains(生日:)").text().replace("生日:", "").trim();
                badHooker.setBirthday(birthday);
            }
            if (div.selectFirst("p:contains(年齡:)") != null) {
                int age = Integer.valueOf(div.selectFirst("p:contains(年齡:)").text().replace("年齡:", "").trim());
                badHooker.setAge(age);
            }
            if (div.selectFirst("p:contains(身高:)") != null) {
                String height = div.selectFirst("p:contains(身高:)").text().replace("身高:", "").trim();
                badHooker.setHeight(height);
            }
            if (div.selectFirst("p:contains(罩杯:)") != null) {
                String cup = div.selectFirst("p:contains(罩杯:)").text().replace("罩杯:", "").trim();
                badHooker.setCup(cup);
            }
            if (div.selectFirst("p:contains(胸圍:)") != null) {
                String bust = div.selectFirst("p:contains(胸圍:)").text().replace("胸圍:", "").trim();
                badHooker.setBust(bust);
            }
            if (div.selectFirst("p:contains(腰圍:)") != null) {
                String waistline = div.selectFirst("p:contains(腰圍:)").text().replace("腰圍:", "").trim();
                badHooker.setWaistline(waistline);
            }
            if (div.selectFirst("p:contains(臀圍:)") != null) {
                String hips = div.selectFirst("p:contains(臀圍:)").text().replace("臀圍:", "").trim();
                badHooker.setHips(hips);
            }
            if (div.selectFirst("p:contains(愛好:)") != null) {
                String hobby = div.selectFirst("p:contains(愛好:)").text().replace("愛好:", "").trim();
                badHooker.setHobby(hobby);
            }
            badHookerDao.save(badHooker);
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
