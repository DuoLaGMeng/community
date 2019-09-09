package life.guangsi.community.controller;

import life.guangsi.community.dto.AccessTokenDTO;
import life.guangsi.community.dto.GithubUser;
import life.guangsi.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.Client.id}")
    private String clientid;

    @Value("${github.Client.secret}")
    private String clientsecret;

    @Value("${github.Redirect.uri}")
    private String redirecturi;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirecturi);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());

        if(user!=null){
            //登录成功，写cookie和session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登录失败重新登录
            return "redirect:/";

        }

    }
}
