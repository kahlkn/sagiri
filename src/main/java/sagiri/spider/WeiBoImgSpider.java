package sagiri.spider;

import artoria.exception.ExceptionUtils;
import artoria.net.HttpMethod;
import artoria.net.HttpRequest;
import artoria.net.HttpResponse;
import artoria.net.HttpUtils;
import artoria.util.CollectionUtils;
import artoria.util.ObjectUtils;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static artoria.common.Constants.NEWLINE;
import static artoria.common.Constants.ONE;

public class WeiBoImgSpider {

    public static void main(String[] args) {
        for (int i = 1; i <= 2; i++) {
            List<WeiBoContent> list = doSome1("2304131792328230", i);
            for (WeiBoContent weiBoContent : list) {
                System.out.println(weiBoContent);
            }
            System.out.println(">>>>>>>> 条数：" + list.size());
        }
    }

    public static List<WeiBoContent> doSome1(String userId, Integer pageNum) {
        if (pageNum == null || pageNum < ONE) { pageNum = ONE; }
        try {
            HttpRequest request = new HttpRequest();
            request.setUrl("https://m.weibo.cn/api/container/getIndex?containerid=" + userId + "&t=1&page=" + pageNum);
            request.setMethod(HttpMethod.GET);
            request.addHeader("Host", "m.weibo.cn");
            request.addHeader("Referer", "https://m.weibo.cn/p/2304131792328230");
            request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
            request.addHeader("X-Requested-with", "XMLHttpRequest");
//            request.addCookie();
            HttpResponse response = HttpUtils.getHttpClient().execute(request);
            String bodyAsString = response.getBodyAsString();
            Map map = JSON.parseObject(bodyAsString, Map.class);
            List cards = (List) ((Map) map.get("data")).get("cards");
            List<WeiBoContent> result = new ArrayList<WeiBoContent>();
            for (Object card : cards) {
                Map mblog = (Map) ((Map) card).get("mblog");
                String text = (String) mblog.get("raw_text");
                String createdAt = (String) mblog.get("created_at");
                List pics = (List) mblog.get("pics");
                List<String> imageList = new ArrayList<String>();
                if (!ObjectUtils.isEmpty(pics)) {
                    for (Object pic : pics) {
                        String url = (String) ((Map) ((Map) pic).get("large")).get("url");
                        imageList.add(url);
                    }
                }
                WeiBoContent weiBoContent = new WeiBoContent();
                weiBoContent.setImageList(imageList);
                weiBoContent.setText(text);
                weiBoContent.setCreatedAt(createdAt);
                result.add(weiBoContent);
            }
            return result;
        }
        catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    public static class WeiBoContent {
        private List<String> imageList;
        private String createdAt;
        private String text;

        public List<String> getImageList() {
            return imageList;
        }

        public void setImageList(List<String> imageList) {
            this.imageList = imageList;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            String str = "Text: " + text + NEWLINE;
            str += "Pics: ";
            if (CollectionUtils.isNotEmpty(imageList)) {
                for (String image : imageList) {
                    str += image + NEWLINE;
                }
            }
            else {
                str += NEWLINE;
            }
            str += "Time: " + createdAt + NEWLINE;
            return str;
        }

    }

}
