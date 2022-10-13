package imok.back.controller;

import imok.back.common.Const;
import imok.back.common.ServerResponse;
import imok.back.pojo.User;
import imok.back.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("user")
public class userController {

    @Autowired
    private IUserService userService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ServerResponse login(String userId,String password){
        ServerResponse response = userService.login(Integer.valueOf(userId), password);
        if (response.isSuccess()){
              session.setAttribute(Const.CURRENT_USER,response.getData());
              String token = session.getId();
            Map<String,String> map = new HashMap<>(1);
            map.put("token",token);
            response =  ServerResponse.createSuccessDate(map);

        }



        return response;
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createSuccess();
    }

    @RequestMapping(value = "userInfo",method = RequestMethod.GET)
    public ServerResponse userInfo(){
       User user = (User) session.getAttribute(Const.CURRENT_USER);
       if(user == null){
           return ServerResponse.createError("用户未登录，无法获取信息！");
       }
       return  ServerResponse.createSuccessDate(user);

    }



}
