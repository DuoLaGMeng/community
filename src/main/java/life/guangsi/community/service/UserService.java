package life.guangsi.community.service;

import life.guangsi.community.mapper.UserMapper;
import life.guangsi.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public void updateOrInsert(User user) {
        User dbUser = userMapper.findById(user.getId());
        if(dbUser == null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
        if(dbUser != null){
            user.setGmtModified(user.getGmtCreate());
            user.setName(user.getName());
            user.setAvatarUrl(user.getAvatarUrl());
            user.setToken(user.getToken());
            userMapper.update(user);
        }
    }
}
