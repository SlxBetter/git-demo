package com.sunlx.serviceedu.controller;

import com.sunlx.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 用户信息控制器
 */
@Api("用户登录")
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","adming-token");
    }
    @GetMapping("/userinfo")
    public R userinfo( String token){

        return R.ok().data("name","admin");
    }


}
