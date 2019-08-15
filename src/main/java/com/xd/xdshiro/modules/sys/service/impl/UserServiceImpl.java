package com.xd.xdshiro.modules.sys.service.impl;

import com.xd.xdshiro.modules.sys.entity.User;
import com.xd.xdshiro.modules.sys.mapper.UserMapper;
import com.xd.xdshiro.modules.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author xd
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
