package sagiri.content.facade.api;

import artoria.beans.BeanUtils;
import artoria.common.Input;
import artoria.common.PageResult;
import artoria.common.Result;
import artoria.util.PagingUtils;
import sagiri.content.persistence.entity.ArticleRelation;
import sagiri.content.service.dto.ArticleRelationDTO;
import sagiri.content.facade.vo.ArticleRelationVO;
import sagiri.content.service.ArticleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * ArticleRelationController.
 * @author Kahle
 * @date 2021-03-05T10:23:44.708+0800
 */
@Slf4j
@Controller
public class ArticleRelationController {

    @Autowired
    private ArticleRelationService articleRelationService;

    /*@ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Result<Object> hello(@RequestBody ArticleRelationVO articleRelationVO) {

        return new Result<Object>("Hello, World! ");
    }*/

}
