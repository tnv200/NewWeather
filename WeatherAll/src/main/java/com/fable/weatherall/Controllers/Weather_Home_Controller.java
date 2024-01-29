package com.fable.weatherall.Controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fable.weatherall.Admin_User_Entities.Admin;
import com.fable.weatherall.Repos.AdminRepo;
import com.fable.weatherall.Services.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Weather_Home_Controller {

	@Autowired
    private AdminService adminService;

	
	@GetMapping("/home")
    public String displayHome() {
        return "Homepage";
    }
	
	@GetMapping("/login")
	public String displayLogin() {
        return "comlogin";
    }
	
	@GetMapping("/signup")
	public String displaySignup() {
        return "signup";
    }
	
	@GetMapping("/user_dashboard")
	public String displayUserDashboard() {
        return "user";
    }
	

	@GetMapping("/user_profile")
	public String displayUserProfile() {
        return "user_profile";
    }
	
	@GetMapping("/admin_dashboard")
	public String displayAdminDashboard() {
        return "admin_dashboard";
    }
	
	@GetMapping("/about")
	public String displayAboutPage() {
        return "about";
    }
	
//	  @GetMapping("/pages-profile")
//	    public String adminprofile(HttpSession session,Model model) {
//	    	System.out.println("Hi");
//	    	return "/pages-profile";
//	    }
//	    
	  
	  @GetMapping("/pages-profile")
	    public String adminprofile() {
	    	System.out.println("Hi");
	    	return "pages-profile";
	    }
	  
	  @Autowired
	  AdminRepo repo;
	@GetMapping("/view_adminprofile")
    public String view_adminprofile(HttpSession session,Model model)
	    {
		
			String email = (String) session.getAttribute("adminEmail");
	    	Admin admin = repo.findByEmail(email);
//	    	System.out.println(email);
//	    	System.out.println("admin + " + admin.getEmail());
	    	List<Admin> user = new ArrayList<>();
	    	user.add(admin);
	    	model.addAttribute("user", user);
	    	System.out.println("Raja");
	    	return "/pages-profile";
	    }
	
}