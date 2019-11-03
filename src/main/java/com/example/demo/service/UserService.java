package com.example.demo.service;

import com.example.demo.data.AuthUser;
import com.example.demo.repository.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService implements IUserService {

    private IAuthUserRepository authUserRepos;

    @Autowired
    public UserService(IAuthUserRepository authUserRepos){
        this.authUserRepos = authUserRepos;
    }

    @Override
    public String registerUser(String userName, String userPass ) {
        try {
        int n = authUserRepos.insert(userName, userPass);
            System.out.println("記録行数:" + n);

            var message = n>0? n+"件を追加":"追加失敗 ";
                   return message;
    }catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "error ユーザがすでに存在しています。";
    }


    @Override
    public boolean removeUser(String userName){
        return authUserRepos.delete(userName) != 0;
 //       System.out.println("削除行数:" + n);
    }

    @Override
    public boolean existsUser(String userName, String userPass){
        boolean result = authUserRepos.exists(userName, userPass);
        System.out.println(userName + ","+userPass+"のユーザ照合結果:"+result);
        return result;
    }

    @Override
    public List<AuthUser> findAuthUsers(){
        List<AuthUser> users = authUserRepos.find();
        System.out.println("データ件数:" + users.size());
        return users;
    }
}
