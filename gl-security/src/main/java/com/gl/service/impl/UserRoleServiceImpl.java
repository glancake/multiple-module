package com.gl.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gl.domain.Role;
import com.gl.domain.UserRole;
import com.gl.mapper.RoleMapper;
import com.gl.mapper.UserRoleMapper;
import com.gl.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        List<UserRole> list = list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId));
        if (ArrayUtil.isNotEmpty(list)) {
            return roleMapper.selectBatchIds(list.stream().map(UserRole::getRoleId).toList());
        }
        return List.of();
    }

    @Override
    public boolean setUserRole(Long id, String roleName) {
        Role role = roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getName, roleName));
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(id);
        return save(userRole);
    }
}
