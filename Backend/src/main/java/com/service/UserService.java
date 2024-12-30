package com.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.UserDAO;
import com.model.User;
 
@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	public void saveUser(User user)
	{
		userDAO.save(user);
	}
	
	 public List<User> getAllUsers() {
	        return userDAO.findAll();
	    }
	 
	public boolean findById(long id)
	{
		return userDAO.findById(id).isPresent();
	}
	public void deleteUser(long id)
	{
		userDAO.deleteById(id);
	}
    public User getUserById(long id)
    {
    	return userDAO.findById(id).get();
    }
}
 
 