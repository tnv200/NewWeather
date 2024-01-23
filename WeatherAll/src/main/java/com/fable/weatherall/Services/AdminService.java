package com.fable.weatherall.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fable.weatherall.Admin_User_Entities.Admin;
import com.fable.weatherall.Admin_User_Entities.User;
import com.fable.weatherall.DTOs.AdminDTO;
import com.fable.weatherall.DTOs.LoginDTO;
import com.fable.weatherall.Responses.LoginResponse;


@Service
public interface AdminService {
	Admin findAdminByUsername(String email);
	void deleteUser(int userId);
	List<User> getAllUsers();
	void registerAdmin(AdminDTO adminDTO);
	 boolean authenticateAdmin(Admin admin, String password);
	 LoginResponse loginAdmin(LoginDTO loginDTO);
}
