package sagiri.spider;

import artoria.net.HttpMethod;
import artoria.net.HttpRequest;
import artoria.net.HttpResponse;
import artoria.net.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BiQuGeSpider {

    public static void main(String[] args) throws IOException {
//        bookInfoAndCatalog();
        bookContent("1152290");
    }

    public static void bookContent(String chapterId) throws IOException {
        HttpRequest request = new HttpRequest();
        request.setUrl("http://www.loubiqu.com/40_40152/" + chapterId + ".html");
        request.setMethod(HttpMethod.GET);
        request.addHeader("Host", "www.loubiqu.com");
        HttpResponse response = HttpUtils.getHttpClient().execute(request);
        String bodyAsString = response.getBodyAsString();
//        System.out.println(bodyAsString);

        Document document = Jsoup.parse(bodyAsString);
        // 标题
        Elements bookNames = document.getElementsByClass("bookname");
        Element h1 = bookNames.get(0).getElementsByTag("h1").get(0);
        System.out.println("标题：" + h1.text());
        // 正文
        Element content = document.getElementById("content");
        System.out.println(">>>> 正文 <<<<");
        System.out.println(content.html());
    }

    public static void bookInfoAndCatalog() throws IOException {
        HttpRequest request = new HttpRequest();
        request.setUrl("http://www.loubiqu.com/40_40152/");
        request.setMethod(HttpMethod.GET);
        request.addHeader("Host", "www.loubiqu.com");
        HttpResponse response = HttpUtils.getHttpClient().execute(request);
        String bodyAsString = response.getBodyAsString();
//        System.out.println(bodyAsString);
        Document document = Jsoup.parse(bodyAsString);
        // >> 主要信息
        Element mainInfo = document.getElementById("maininfo");
        Element info = mainInfo.getElementById("info");
        // 书名
        Elements bookNameH1s = info.getElementsByTag("h1");
        System.out.println("书名："+bookNameH1s.text());
        // 其他信息
        Elements infoTps = info.getElementsByTag("p");
        for (Element infoTp : infoTps) {
            String text = infoTp.text();
            if (text.startsWith("动 作:")) { continue; }
//            String[] split = text.split(":");
//            System.out.println("其他信息："+split[0] + ">>" + split[1]);
            System.out.println("其他信息 >> " + text);
        }
        // 简介
        Element intro = mainInfo.getElementById("intro");
        Elements introTps = intro.getElementsByTag("p");
        System.out.println("简介："+introTps.get(0).html().replaceAll("<br>", "\n"));
        // >> 书封
        Element fmImg = document.getElementById("fmimg");
        Elements imgs = fmImg.getElementsByTag("img");
        String src = imgs.get(0).attr("src");
        System.out.println("书封：" + src);
        // 章节
        Element list = document.getElementById("list");
        Elements dds = list.getElementsByTag("dd");
        for (Element dd : dds) {
            Elements as = dd.getElementsByTag("a");
            Element element = as.get(0);
            System.out.println(element.text() + " >> " + element.attr("href"));
        }
    }

}
