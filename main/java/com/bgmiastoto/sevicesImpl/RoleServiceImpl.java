package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.users.Role;
import com.bgmiastoto.repositories.RoleRepository;
import com.bgmiastoto.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }
}
