package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.users.ApplicationUser;
import com.bgmiastoto.entities.users.User;
import com.bgmiastoto.errors.ErrorMessage;
import com.bgmiastoto.models.bindingModels.UserRegistrationModel;
import com.bgmiastoto.repositories.ApplicationUserRepository;
import com.bgmiastoto.repositories.UserRepository;
import com.bgmiastoto.services.ApplicationUserService;
import com.bgmiastoto.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final UserRepository appUserRepository;

    private final RoleService roleService;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserServiceImpl(ApplicationUserRepository appUserRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationModel userRegistrationModel) {
        ApplicationUser user = this.modelMapper.map(userRegistrationModel, ApplicationUser.class);
        String encryptedPassword = this.passwordEncoder.encode(userRegistrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        this.appUserRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.appUserRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(ErrorMessage.INVALID_CREDENTIALS);
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = this.appUserRepository.findOneByUsername(username);
        return user;
    }

}
