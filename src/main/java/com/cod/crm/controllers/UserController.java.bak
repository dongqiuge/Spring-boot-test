package com.cod.crm.controllers;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cod.crm.mapper.UserMapper;
import com.cod.crm.model.User;



@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	
	
	@RequestMapping(value = "/api/user/create", method = RequestMethod.POST)
	public HashMap<String,String> create(
			@RequestBody User user
			)
    {
		

		
		HashMap<String,String> response = new HashMap<String,String>();
     
		
		// 确保用户名和密码不为空
	    if (user.getName()  == null || user.getPassword() == null) {
	        response.put("error", "用户名和密码不能为空！");
	        return response;
	    }

		
//    Users user=new Users();
//	user.setUsername("tiff");
//	user.setPassword("tiff");
		int result = userMapper.insertSelective(user);
	
	if (result > 0) {
        response.put("id", user.getId().toString());
    } else {
        response.put("error", "用户创建失败，请重试。");
    }

    return response;
	
	
	}
	
	
//    @RequestMapping(value = "/api/custom/create", method = RequestMethod.POST)
//    public  Response<HashMap<String,String>>  create(
//         @RequestBody Custom custom
//         ) 
//    {
//
//          //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//          //String date= df.format(new Date());
//
//          custom.setCreatedAt(new Date());
//          custom.setUpdatedAt(new Date());
//
//         customMapper.insert(custom);
//
//         Response<HashMap<String,String>> response = new Response<HashMap<String,String>>();
//
//         response.data=new HashMap<String,String>();
//         response.data.put("id",custom.getId().toString());
//
//         return response;
//    }


	
	
	
	
	
	
	
	@RequestMapping(value = "/api/user/delete", method = RequestMethod.POST)
    public  HashMap<String,String>  delete(
         @RequestParam(value = "id", defaultValue = "",required = true) Integer id
         ) 
    {
		userMapper.deleteByPrimaryKey(id);

         HashMap<String,String> response = new HashMap<String,String>();

//         response.data=new HashMap<String,String>();

         return response;
    }
	
	
	
	@RequestMapping(value = "/api/user/update", method = RequestMethod.POST)
    public  HashMap<String,String>  update(
         @RequestBody User users
         ) 
    {
		  users=userMapper.selectByPrimaryKey(users.getId());
		  //users.setName("Tiff0322");
		  
          userMapper.updateByPrimaryKey(users);

         HashMap<String,String> response = new HashMap<String,String>();

//         response.data=new HashMap<String,String>();
//         response.data.put("id",custom.getId().toString());

         return response;
    }
	
	
	
    @RequestMapping(value = "/api/user/get", method = RequestMethod.GET)
    public  User   get(
         @RequestParam(value = "id", defaultValue = "",required = true) Integer id
         ) 
    {

         User users = userMapper.selectByPrimaryKey(id);


//         response.data = custom;

         return users;
    }
    
    
    
//    @RequestMapping(value = "/api/user/list", method = RequestMethod.GET)
//    public  Response<PageInfo<Custom>>   list(
//         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageCount,
//         @RequestParam(value = "pageIndex", defaultValue = "1") Integer page,
//         @RequestParam(value = "name", defaultValue = "") String query
//  
//         ) 
//         {
//
//          if (!query.equals("")) query="%"+query+"%";
//          if (page < 1) page=1;
//          if (pageCount < 1) pageCount =1; 
//
//          CustomExample customExample = new CustomExample();
//
//          PageHelper.startPage(page,pageCount);    
//
//
//          User user=new User();
//
//
//          Criteria criteria = customExample.createCriteria();
//          if (!StringUtils.isEmptyOrWhitespace(query)) {
//                  criteria.andNameLike("%" + query + "%");    
//          }
//          
//          List<Custom> userList = customMapper.selectByExample(customExample);
//
//          PageInfo<Custom> pageInfo = new PageInfo<Custom>(userList);
//
//          Response<PageInfo<Custom>> response=new Response<PageInfo<Custom>>();
//
//
//          response.data=pageInfo;
//
//          return response;
//    }


}

