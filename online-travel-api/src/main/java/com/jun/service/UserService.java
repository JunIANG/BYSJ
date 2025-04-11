package com.jun.service;

import com.jun.pojo.api.Result;
import com.jun.pojo.dto.update.UserLoginDTO;
import com.jun.pojo.dto.update.UserRegisterDTO;
import com.jun.pojo.dto.update.UserUpdateDTO;
import com.jun.pojo.dto.query.extend.UserQueryDto;
import com.jun.pojo.entity.User;
import com.jun.pojo.vo.ChartVO;
import com.jun.pojo.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * 用户服务类
 */
public interface UserService {
    Result<String> register(UserRegisterDTO userRegisterDTO);

    Result<Object> login(UserLoginDTO userLoginDTO);

    Result<UserVO> auth();

    Result<List<User>> query(UserQueryDto userQueryDto);

    Result<String> update(UserUpdateDTO userUpdateDTO);

    Result<String> batchDelete(List<Integer> ids);

    Result<String> updatePwd(Map<String, String> map);

    Result<UserVO> getById(Integer id);

    Result<String> insert(UserRegisterDTO userRegisterDTO);

    Result<String> backUpdate(User user);

    Result<List<ChartVO>> daysQuery(Integer day);

}
