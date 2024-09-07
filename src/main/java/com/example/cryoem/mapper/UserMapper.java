package com.example.cryoem.mapper;

import com.example.cryoem.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王新刚
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-09-07 18:10:01
* @Entity com.example.cryoem.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




