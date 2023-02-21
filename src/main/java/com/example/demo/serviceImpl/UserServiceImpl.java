package com.example.demo.serviceImpl;

import com.example.demo.POJO.User;
import com.example.demo.constents.CafeConstants;
import com.example.demo.dao.UserDao;
import com.example.demo.service.UserService;
import com.example.demo.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j

public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {if (validateSignUpMap(requestMap)){
            User user = userDao.findByEmailId(requestMap.get("email"));
            if(Objects.isNull(user)){
                userDao.save(getUserFromMap(requestMap));
                return CafeUtils.getResponesEntity("Succesfully Register.", HttpStatus.OK);

            }else{
                return CafeUtils.getResponesEntity("Email already exits.", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return CafeUtils.getResponesEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }

        }catch (Exception ex){
            ex.printStackTrace();
        }
return CafeUtils.getResponesEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    private boolean validateSignUpMap(Map<String,String> requestMap){
        if (requestMap.containsKey("name") && requestMap.containsKey("phoneNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setPhoneNumber(requestMap.get("phoneNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
