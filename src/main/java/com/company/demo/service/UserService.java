package com.company.demo.service;

import com.company.demo.entity.User;
import com.company.demo.model.dto.UserDto;
import com.company.demo.model.request.ChangePasswordReq;
import com.company.demo.model.request.CreateUserReq;
import com.company.demo.model.request.UpdateProfileReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getListUser();

    User createUser(CreateUserReq req);

    void changePassword(User user, ChangePasswordReq req);

    User updateProfile(User user, UpdateProfileReq req);
    void deleteUser(Long id);
}
