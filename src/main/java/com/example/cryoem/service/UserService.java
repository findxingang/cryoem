package com.example.cryoem.service;

import cn.dev33.satoken.util.SaResult;
import com.example.cryoem.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cryoem.vo.UserVO;

/**
* @author 王新刚
* @description 针对表【user】的数据库操作Service
* @createDate 2024-09-07 18:10:01
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userVO
     * @return
     */
    Boolean register(UserVO userVO);

    /**
     * 用户登录
     * @param userVO
     * @return
     */
    SaResult login(UserVO userVO);

    User getByUsername(String username);
}
