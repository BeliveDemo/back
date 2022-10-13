package imok.back.service;

import imok.back.common.ServerResponse;
import imok.back.pojo.User;

public interface IUserService {
    ServerResponse<User> login(Integer userId, String password);
}
