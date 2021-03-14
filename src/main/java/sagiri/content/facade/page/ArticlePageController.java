package sagiri.content.facade.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sagiri.content.common.util.SysUtils;

/**
 * ArticleController.
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
@Slf4j
@Controller
public class ArticlePageController {

    /**
     * 文章的列表页面
     */
    @RequestMapping(value = "/admin/article/list", method = RequestMethod.GET)
    public String articleListPage(Model model) {
        model.addAttribute("app", SysUtils.app());
        return "admin/articles";
    }

    @RequestMapping(value = "/admin/article/add", method = RequestMethod.GET)
    public String articleNew(Model model) {
        model.addAttribute("app", SysUtils.app());
        return "admin/article/new";
    }

    @RequestMapping(value = "/admin/article/edit", method = RequestMethod.GET)
    public String articleEdit(Model model) {
        model.addAttribute("app", SysUtils.app());
        return "admin/article/edit";
    }

}
