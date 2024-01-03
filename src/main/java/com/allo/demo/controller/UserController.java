package com.allo.demo.controller;

import com.google.gson.Gson;
import com.allo.demo.dao.UserDao;
import com.allo.demo.entity.UserDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "Users API", tags = "Users API")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping("add")
    public ResponseEntity addUser(
            @RequestParam(value = "userName", required = true)
                    String userName,
            @RequestParam(value = "password", required = true)
                    String password,
            @RequestParam(value = "phone", required = false)
                    String phone
    ) {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userDao.insert(userDomain);
        return ResponseEntity.ok("User Added");
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteUser(@RequestParam(value = "userId", required = true) Integer userId) {
        userDao.deleteUserById(userId);
        return ResponseEntity.ok("User Deleted");
    }

    @PutMapping("update")
    public ResponseEntity updateUser(
            @RequestParam(value = "userId", required = true)
                    Integer userId,
            @RequestParam(value = "userName", required = false)
                    String userName,
            @RequestParam(value = "password", required = false)
                    String password,
            @RequestParam(value = "phone", required = false)
                    String phone
    ) {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(userId);
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userDao.updateUser(userDomain);
        return ResponseEntity.ok("User Updated");
    }

    @GetMapping("get")
    @ApiOperation(value = "Get User List", notes = "Get User List")
    public ResponseEntity getUsers() {
        List<UserDomain> userDomains = userDao.selectUsers();
        Gson gson = new Gson();
        String s = gson.toJson(userDomains);
        System.out.println(s);
        return ResponseEntity.ok(userDomains);
    }
}
