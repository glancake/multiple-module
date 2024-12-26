package com.gl.service;

import com.gl.domain.Role;

import java.util.List;

public interface UserRoleService {

    List<Role> getRolesByUserId(Long userId);

}
