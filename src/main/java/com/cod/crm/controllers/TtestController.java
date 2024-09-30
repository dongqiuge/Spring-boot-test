package com.cod.crm.controllers;

import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cod.crm.mapper.TestMapper;
import com.cod.crm.mapper.UserMapper;
import com.cod.crm.model.User;
import com.cod.crm.model.UserExample;
import com.cod.crm.model.UserExample.Criteria;
import com.cod.crm.response.Response;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;

import java.util.HashMap;
import java.util.List;



@RestController

public class TtestController{
	
	
		@Autowired
	    private UserMapper userMapper;

		@Autowired
	    private TestMapper testMapper;
	
	 @RequestMapping(value = "/api/user/create", method = RequestMethod.POST)
	    public  Response<HashMap<String,String>>  create(
	    		@RequestBody User user
	         ) 
	    
	    
	    {
		          //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		          //String date= df.format(new Date());

		         // user.setName(new Date());
		 		user.setToken("hello");		         // user.setPassword(new Date());

		         userMapper.insert(user);

		         Response<HashMap<String,String>> response = new Response<HashMap<String,String>>();

		         response.data=new HashMap<String,String>();
		         response.data.put("id",user.getId().toString());

		         return response;
	    }
	
	
	 @RequestMapping(value = "/api/user/delete", method = RequestMethod.POST)
	    public  Response<HashMap<String,String>>  delete(
	    	@RequestParam(value = "id", defaultValue = "0") Integer user_id
	         ) 
	    
	    
		    {
			          //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			          //String date= df.format(new Date());
	
			         // user.setName(new Date());
				 userMapper.deleteByPrimaryKey(user_id);
		
		         Response<HashMap<String,String>> response = new Response<HashMap<String,String>>();
		
		         response.data=new HashMap<String,String>();
		
		         return response;
		    }
	 
	 
	 @RequestMapping(value = "/api/user/get", method = RequestMethod.POST)
	    public  Response<User>  get(
	    	@RequestParam(value = "id", defaultValue = "0") Integer user_id
	         ) 
	    
	    
		    {
			          //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			          //String date= df.format(new Date());
	
			         // user.setName(new Date());
				 User user = userMapper.selectByPrimaryKey(user_id);
		
		         Response<User> response = new Response<User>();
		
		         response.data = user;
		
		         return response;
		    }
	 
	 
	 
	 
	 @RequestMapping(value = "/api/user/update", method = RequestMethod.POST)
	    public  Response<HashMap<String,String>>  update(
	    	@RequestBody User user 
	         ) 
	    
	    
		    {
			          //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			          //String date= df.format(new Date());
	
			         // user.setName(new Date());
			/*	 User user = userMapper.selectByPrimaryKey(user_id);
					
		         Response<User> response = new Response<User>();
		
		         response.data = user;
		
		         return response;
		 */
		         
		         
		         
				
		         userMapper.updateByPrimaryKey(user);
		
		         Response<HashMap<String,String>> response = new Response<HashMap<String,String>>();
		
		         response.data=new HashMap<String,String>();
		         response.data.put("id",user.getId().toString());
		
		         return response;
		    }
	 
	 
	
	 


	 	//__________________list__________________________


			
			@RequestMapping(value = "/api/user/list", method = RequestMethod.GET)
			public  Response<PageInfo<User>>   list(
			     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageCount,
			     @RequestParam(value = "pageIndex", defaultValue = "1") Integer page,
			     @RequestParam(value = "name", defaultValue = "") String query
			
			     ) 
			     {
			
			      if (!query.equals("")) query="%"+query+"%";
			      if (page < 1) page=1;
			      if (pageCount < 1) pageCount =1; 
			
			      UserExample userExample = new UserExample();
			
			      PageHelper.startPage(page,pageCount);    
			
			
			      User user=new User();

			      Criteria criteria = userExample.createCriteria();
		          if (!StringUtils.isEmptyOrWhitespaceOnly(query)) {
		                  criteria.andNameLike("%" + query + "%");    
		          }
		          
		          List<User> userList = userMapper.selectByExample(userExample);

		          PageInfo<User> pageInfo = new PageInfo<User>(userList);

		          Response<PageInfo<User>> response=new Response<PageInfo<User>>();


		          response.data=pageInfo;

		          return response;
		    }
			
			

//_________________________________________
			
			
			 @RequestMapping(value = "/api/user/test", method = RequestMethod.GET)
			    public  Response<String>  test(
			         ) 
			    
			    
			    {		
				 		Integer  x;
				        x = testMapper.test(); 
				 	       

				         Response<String> response = new Response<String>();

				         response.data=  x.toString();
				         
				         return response;
				         
				         
				         
			    }
			 
			 
			 
			 
			 
			 
			 
			 
			 
			
			
}		
			
