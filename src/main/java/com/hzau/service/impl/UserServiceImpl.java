package com.hzau.service.impl;

import com.hzau.dao.UserDao;
import com.hzau.dao.impl.UserDaoImpl;
import com.hzau.domain.User;
import com.hzau.service.UserService;
import com.hzau.util.MailUtils;
import com.hzau.util.UuidUtil;

/**
 * @author su
 * @description
 * @date 2020/2/24
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            return false;
        }
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        userDao.save(user);
        //3.激活邮件发送邮件正文
        String content = "<a href='http://localhost:8080/Travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    /**
     * 激活方法
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        User user = userDao.findByCode(code);
        if (user != null) {
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    /**登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
