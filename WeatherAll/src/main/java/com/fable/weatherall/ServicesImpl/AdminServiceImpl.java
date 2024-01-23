package com.fable.weatherall.ServicesImpl;

import java.util.List;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fable.weatherall.Admin_User_Entities.Admin;
import com.fable.weatherall.Admin_User_Entities.User;
import com.fable.weatherall.DTOs.AdminDTO;
import com.fable.weatherall.DTOs.LoginDTO;
import com.fable.weatherall.Repos.AdminRepo;
import com.fable.weatherall.Repos.UserRepo;
import com.fable.weatherall.Responses.LoginResponse;
import com.fable.weatherall.Services.AdminService;
import jakarta.transaction.Transactional;


@Service
public class AdminServiceImpl implements AdminService {
	
//	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void deleteUser(int userId) {
    	 System.out.println("Deleting user with ID: " + userId);
        userRepo.deleteById(userId);
    }
    

    @Override
    public boolean authenticateAdmin(Admin admin, String password) {
        return admin != null && passwordEncoder.matches(password, admin.getPassword());
    }
    
    @Override
    public Admin findAdminByUsername(String email) {
        return adminRepo.findByUsername(email).orElse(null);
    }
       
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
   
	
    
    @Override
    public void registerAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        admin.setConfirmpassword(passwordEncoder.encode(adminDTO.getConfirmpassword()));
        adminRepo.save(admin);
    }


	@Override
	public LoginResponse loginAdmin(LoginDTO loginDTO) {
		Admin admin1 = adminRepo.findByEmail(loginDTO.getEmail());
        if (admin1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = admin1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Admin> admin = adminRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (admin.isPresent()){
                    return new LoginResponse("Login Success", true); // Fixed syntax
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Password Not Match", false); // Fixed typo
            }
        } else {
            return new LoginResponse("Email not exists", false);
        }
	}

}


