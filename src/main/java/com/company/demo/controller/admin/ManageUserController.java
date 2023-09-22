package com.company.demo.controller.admin;

import com.company.demo.entity.Category;
import com.company.demo.entity.User;
import com.company.demo.model.dto.CategoryInfo;
import com.company.demo.model.dto.UserDto;
import com.company.demo.model.mapper.UserMapper;
import com.company.demo.model.request.CreateCategoryReq;
import com.company.demo.model.request.CreateUserReq;
import com.company.demo.model.request.UpdateProfileReq;
import com.company.demo.repository.UserRepository;
import com.company.demo.service.CategoryService;
import com.company.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ManageUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public String getUserManagePage(Model model) {
        List<UserDto> users = userService.getListUser();
        model.addAttribute("users", users);

        return "admin/user/list";
    }

    @PostMapping("/api/admin/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {
        // Create user
        User result = userService.createUser(req);

        return ResponseEntity.ok(UserMapper.toUserDto(result));
    }

    @PutMapping("/api/admin/users/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateProfileReq req, @PathVariable Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if(user == null) {
            return ResponseEntity.ok("Không tìm thấy user");
        }
        userService.updateProfile(user, req);

        return ResponseEntity.ok("Cập nhật thành công");
    }

    @DeleteMapping("/api/admin/users/{id}")
    public ResponseEntity<?> deleteUser( @PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.ok("Xóa thành công");
    }
}
