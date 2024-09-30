package com.cod.crm.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cod.crm.mapper.UserMapper;
import com.cod.crm.model.User;
import com.cod.crm.model.UserExample;
import com.cod.crm.model.UserExample.Criteria;
import com.cod.crm.response.Response;

@RestController

public class LoginController{

	
	
	   @Autowired
	    private UserMapper userMapper;

    @RequestMapping (value="/api/login/login", method = RequestMethod.POST)
    

    public Response<User> login(
	    	@RequestParam(value = "username", defaultValue = "0") String user_name,
	    	@RequestParam(value = "password", defaultValue = "0") String user_password

    		)
    		
    		
    		
    	
    	 {
             Response<User> response= new Response<User>();


             System.out.println("username " + user_name);
             System.out.println("password " + user_password);

             UserExample userExample = new UserExample();
             Criteria criteria =  userExample.createCriteria();
             
             criteria.andNameEqualTo(user_name);
             criteria.andPasswordEqualTo(user_password);
    
             List<User> users  = userMapper.selectByExample(userExample);
             User user=null;
             if(users.size() > 0 )  user=users.get(0);

             if(user == null )
             {
                  response.code="USER_NOT_EXISTS";
                  response.message="用户不存在或者账号密码错误";
                  response.data=null;

                  return response;
             }
             
             
             String token = this.generateRandom(32);

             user.setToken(token);

             userMapper.updateByPrimaryKey(user);


             response.data = user;

            return response;
      
    	 }
    		
			    private String generateRandom(int length) {
			
			        String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXZYabcdefghijklmnopqrstuvwxyz1234567890"; // 36 letter.
			
			        Random rand = new Random();
			        StringBuilder res = new StringBuilder();
			        for (int i = 0; i < length; i++) {
			             int randIndex = rand.nextInt(aToZ.length());
			             res.append(aToZ.charAt(randIndex));
			        }
			        return res.toString();
			   }
    
    
    
			}
             
             
             
             
             
        
    
