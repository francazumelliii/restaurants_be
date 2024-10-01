package jac.project.restaurants.restaurants_be.admin;


import jac.project.restaurants.restaurants_be.APIResponse.ApiResponse;
import jac.project.restaurants.restaurants_be.admin.DTOs.PatchAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    public final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }


    @GetMapping("/me/list")
    public ResponseEntity<ApiResponse<String>> getAdminList(){
        ApiResponse response = new ApiResponse(true, this.adminService.getAdminList());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Optional<AdminDTO>>> getAdmin(){
        ApiResponse response = new ApiResponse(true, this.adminService.getAdmin());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<ApiResponse<Optional<AdminDTO>>> patchAdmin(@RequestBody PatchAdminDTO dto){
        ApiResponse response = new ApiResponse(true, this.adminService.patchAdmin(dto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
