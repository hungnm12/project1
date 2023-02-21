package com.example.demo.restImpl;

import com.example.demo.constents.CafeConstants;
import com.example.demo.rest.UserRest;
import com.example.demo.service.UserService;
import com.example.demo.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
       try{
           return userService.signUp(requestMap);
       }catch (Exception ex){
           ex.printStackTrace();
       }
//       return new ResponseEntity<String>("{\"massage\":\"Something went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    return CafeUtils.getResponesEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
