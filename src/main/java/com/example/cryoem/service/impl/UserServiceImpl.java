package com.example.cryoem.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cryoem.domain.User;
import com.example.cryoem.service.UserService;
import com.example.cryoem.mapper.UserMapper;
import com.example.cryoem.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
* @author 王新刚
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-09-07 18:10:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Boolean register(UserVO userVO) {
        if (isIllegal(userVO)) {
            throw new RuntimeException("参数校验失败: 用户名、密码不能为空");
        }
        User user = User.buildUser(userVO);
        int insert = baseMapper.insert(user);
        return insert > 0;
    }

    /**
     * 校验参数，必须同时传入 username, password, email 三个参数
     * @param userVO
     * @return
     */
    private static boolean isIllegal(UserVO userVO) {
        return StrUtil.isBlank(userVO.getUsername())
                || StrUtil.isBlank(userVO.getPassword());
    }

    @Override
    public SaResult login(UserVO userVO) {
        if (isIllegal(userVO)) {
            throw new RuntimeException("参数校验失败: 用户名、密码、邮件不能为空");
        }
        User user = getByUsername(userVO.getUsername());
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }
        if (StrUtil.equals(userVO.getPassword(), user.getPassword())) {
            // 登录
            StpUtil.login(user.getId());
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUsername, username);
        return baseMapper.selectOne(lambdaQueryWrapper);
    }
}




