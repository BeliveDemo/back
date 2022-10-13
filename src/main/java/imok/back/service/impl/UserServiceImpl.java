package imok.back.service.impl;

import imok.back.common.ServerResponse;
import imok.back.dao.UserMapper;
import imok.back.pojo.User;
import imok.back.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(Integer userId, String password) {

        int count = userMapper.countByUserId(userId);
        if (count == 0){
            return ServerResponse.createError("账号不存在！");
        }

        User user = userMapper.selectLoginInfo(userId,password);
        if (user == null){
            return ServerResponse.createError("账号不存在或者密码错误！");
        }

        user.setPassword(StringUtils.EMPTY);

        return ServerResponse.createSuccessMessage("登录成功！",user);
    }




}
