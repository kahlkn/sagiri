package sagiri.core.facade.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sagiri.core.service.ArticleService;

import java.util.HashMap;
import java.util.Map;

/**
 * PageController.
 * @author Kahle
 * @date 2020-11-30T16:17:49.778+0800
 */
@Slf4j
@Controller
public class PageController {

    @Autowired
    private ArticleService articleService;

    private Map<String, Object> app() {
        Map<String, Object> system = new HashMap<>();
        system.put("cdnUrl", "/admin");
        system.put("version", "v1.0");
        return system;
    }

    /**
     * 仪表盘
     */
    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("app", app());
        return "/admin/index";
    }

    /**
     * 个人设置页面
     */
    @RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        model.addAttribute("app", app());
        return "/admin/profile";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("app", app());
        return "/admin/login";
    }

    @RequestMapping(value = "/admin/articles", method = RequestMethod.GET)
    public String articles(Model model) {
        model.addAttribute("app", app());
        return "/admin/articles";
    }

    @RequestMapping(value = "/admin/attaches", method = RequestMethod.GET)
    public String attaches(Model model) {
        model.addAttribute("app", app());
        return "/admin/attaches";
    }

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String categories(Model model) {
        model.addAttribute("app", app());
        return "/admin/categories";
    }

    @RequestMapping(value = "/admin/comments", method = RequestMethod.GET)
    public String comments(Model model) {
        model.addAttribute("app", app());
        return "/admin/comments";
    }

    @RequestMapping(value = "/admin/pages", method = RequestMethod.GET)
    public String pages(Model model) {
        model.addAttribute("app", app());
        return "/admin/pages";
    }

    @RequestMapping(value = "/admin/setting", method = RequestMethod.GET)
    public String setting(Model model) {
        model.addAttribute("app", app());
        return "/admin/setting";
    }

    @RequestMapping(value = "/admin/themes", method = RequestMethod.GET)
    public String themes(Model model) {
        model.addAttribute("app", app());
        return "/admin/themes";
    }

    @RequestMapping(value = "/admin/tpl_list", method = RequestMethod.GET)
    public String tpl_list(Model model) {
        model.addAttribute("app", app());
        return "/admin/tpl_list";
    }




    @RequestMapping(value = "/admin/page/new", method = RequestMethod.GET)
    public String pageNew(Model model) {
        model.addAttribute("app", app());
        return "/admin/page/new";
    }

    @RequestMapping(value = "/admin/page/edit", method = RequestMethod.GET)
    public String pageEdit(Model model) {
        model.addAttribute("app", app());
        return "/admin/page/edit";
    }

    @RequestMapping(value = "/admin/article/new", method = RequestMethod.GET)
    public String articleNew(Model model) {
        model.addAttribute("app", app());
        return "/admin/article/new";
    }

    @RequestMapping(value = "/admin/article/edit", method = RequestMethod.GET)
    public String articleEdit(Model model) {
        model.addAttribute("app", app());
        return "/admin/article/edit";
    }

}
