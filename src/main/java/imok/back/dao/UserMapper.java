package imok.back.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import imok.back.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
public interface UserMapper extends BaseMapper<User> {
        int countByUserId(Integer id);
        User selectLoginInfo(@Param("id")Integer id,@Param("password")String password);
}
