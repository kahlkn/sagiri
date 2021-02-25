package sagiri.spider;

import artoria.common.Result;
import artoria.util.CollectionUtils;
import artoria.util.ThreadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sagiri.system.service.FileService;

import java.util.List;

@Slf4j
@Controller
public class SpiderController {

    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/spider/WeiBoImgSpider", method = RequestMethod.POST)
    public Result<Object> uploadFiles(@RequestParam(required = false) Integer pageNum) {
        if (pageNum == null) { pageNum = 5; }
        for (int i = 1; i < pageNum; i++) {
            List<WeiBoImgSpider.WeiBoContent> list = WeiBoImgSpider.doSome1("2304131792328230", i);
            for (WeiBoImgSpider.WeiBoContent weiBoContent : list) {
                System.out.println(weiBoContent);
                List<String> imageList = weiBoContent.getImageList();
                if (CollectionUtils.isEmpty(imageList)) { continue; }
                for (String image : imageList) {
                    fileService.saveFile("tmp", image, null);
                    System.out.println("saveFile: " + image);
                    ThreadUtils.sleepQuietly(500);
                }
            }
        }
        return new Result<Object>();
    }

}
