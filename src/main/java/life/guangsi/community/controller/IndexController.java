package life.guangsi.community.controller;

import life.guangsi.community.cache.HotTagCache;
import life.guangsi.community.dto.PageDTO;
import life.guangsi.community.dto.QuestionDTO;
import life.guangsi.community.mapper.QuestionMapper;
import life.guangsi.community.mapper.UserMapper;
import life.guangsi.community.model.Question;
import life.guangsi.community.model.User;
import life.guangsi.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag) {

        PageDTO pagination = questionService.list(search, tag, page, size);
        List<String> hotTags = hotTagCache.getHots();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("hotTags", hotTags);
        model.addAttribute("tag", tag);

        return "index";
    }
}
