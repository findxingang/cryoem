package com.example.cryoem.web;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.cryoem.exception.Result;
import com.example.cryoem.service.UserService;
import com.example.cryoem.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangxingang
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    /**
     * 用户注册
     * @param userVO
     * @return
     */
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody UserVO userVO) {
        Boolean rs = userService.register(userVO);
        return Result.ok(rs);
    }

    /**
     * 用户登录
     * @param userVO
     * @return
     */
    @PostMapping("/login")
    public SaResult login(@RequestBody UserVO userVO) {
        return userService.login(userVO);
    }

    /**
     * 用户退出登录
     * @return
     */
    @PostMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    /**
     * 查询登录状态
     * @return
     */
    @GetMapping("isLogin")
    public SaResult isLogin() {
        boolean login = StpUtil.isLogin();
        return SaResult.ok(login ? "登录了" : "没登录");
    }
}
