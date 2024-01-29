package com.fable.weatherall.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fable.weatherall.Admin_User_Entities.Admin;
import com.fable.weatherall.Admin_User_Entities.User;
import com.fable.weatherall.DTOs.AdminDTO;
import com.fable.weatherall.DTOs.LoginDTO;
import com.fable.weatherall.Responses.LoginResponse;
import com.fable.weatherall.Services.AdminService;

import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
    private AdminService adminService;

//    @DeleteMapping("/deleteUser/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
//        adminService.deleteUser(userId);
//        return ResponseEntity.ok("User deleted successfully");
//    }
//	  @DeleteMapping("/deleteUser/{userId}")
//	    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
//	        try {
//	            adminService.deleteUser(userId);
//	            return ResponseEntity.ok("User deleted successfully");
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
//	        }
//	    }
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "userId") Integer userId) {
	    try {
	        if (userId == null || userId <= 0) {
	            return ResponseEntity.badRequest().body("Invalid user ID");
	        }

	        adminService.deleteUser(userId);
	        return ResponseEntity.ok("User deleted successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
	    }
	}

    @PostMapping("/authenticate")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin,HttpSession session) {
//        String email = adminDTO.getEmail();
//        String password = adminDTO.getPassword();
//
//        Admin admin = adminService.findAdminByUsername(email);
//
//        if (adminService.authenticateAdmin(admin, password)) {
//            return ResponseEntity.ok("{\"status\": true, \"message\": \"Admin authentication successful\"}");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("{\"status\": false, \"message\": \"Invalid credentials or Admin not found\"}");
//        }
    	
    	session.setAttribute("adminEmail", admin.getEmail());
     	
//    	session.setAttribute("adminPass", admin.getPassword());
    	
    	LoginResponse loginResponse = adminService.loginAdmin(admin);
		return ResponseEntity.ok(loginResponse);
    }

	  
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    
    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminDTO adminDTO) {
        adminService.registerAdmin(adminDTO);
       
        return ResponseEntity.ok("Admin registered successfully");
    }
//    
//    @GetMapping("/sendData")
//    public String userProfile(Model model)
//    {
//    	 
//    	 
//    	 model.addAttribute("admin", admin);
//    	
//    	return "admin_dashboard";
//    }
    
  

}
