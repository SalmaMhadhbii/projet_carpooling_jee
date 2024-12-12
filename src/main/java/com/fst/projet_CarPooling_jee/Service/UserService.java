package com.fst.projet_CarPooling_jee.Service;

import com.fst.projet_CarPooling_jee.Entity.User;


import java.util.List;


public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
}
