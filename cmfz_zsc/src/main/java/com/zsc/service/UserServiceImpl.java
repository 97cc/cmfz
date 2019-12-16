package com.zsc.service;

import com.zsc.dao.UserDao;
import com.zsc.dto.UserCount;
import com.zsc.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public List<User> queryPage(Integer page, Integer rows) {
        Integer size = (page - 1) * rows;
        RowBounds rowBounds = new RowBounds(size, rows);
        List<User> list = userDao.selectByRowBounds(new User(), rowBounds);
        return list;
    }

    @Override
    public int queryRecords() {
        return userDao.selectCount(new User());
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        Date date = new Date();
        user.setCreateDate(date);
        int i = userDao.insertSelective(user);
        if(i != 1){
            throw new RuntimeException("用户添加失败");
        }
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delUser(User user) {
        int i = userDao.deleteByPrimaryKey(user);
        if(i != 1){
            throw new RuntimeException("用户删除失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String editUser(User user) {
        String id = null;

        Date date = new Date();
        user.setLastUpdateDate(date);

        if(user.getHeadImg().length()>0){
            int i = userDao.updateByPrimaryKeySelective(user);
            if(i != 1){
                throw new RuntimeException("修改失败");
            }
            return user.getId();
        }else {
            user.setHeadImg(null);
            int i = userDao.updateByPrimaryKeySelective(user);
            if(i != 1){
                throw new RuntimeException("修改失败");
            }
            return id;

        }
    }

    @Override
    public List<User> queryRegisterCount(int a) {
        List<User> list = userDao.queryRegisterCount(a);

        return list;
    }

    //根据性别查省份用户数量
    @Override
    public List<UserCount> queryUserProvinceCount(String sex) {
        return userDao.queryUserProvinceCount(sex);
    }


}
