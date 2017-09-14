package com.bgmiastoto.sevicesImpl;

import com.bgmiastoto.entities.users.SocialUser;
import com.bgmiastoto.errors.ErrorMessage;
import com.bgmiastoto.repositories.SocialUserRepository;
import com.bgmiastoto.services.RoleService;
import com.bgmiastoto.services.SocialUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService {

    private final SocialUserRepository socialUserRepository;

    private final RoleService roleService;

    @Autowired
    public SocialUserServiceImpl(SocialUserRepository socialUserRepository, RoleService roleService) {
        this.socialUserRepository = socialUserRepository;
        this.roleService = roleService;
    }

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);
        if (socialUser == null) {
            socialUser = registerUser(email);
        }

        this.socialUserRepository.save(socialUser);
        loginUser(socialUser);
    }

    private SocialUser registerUser(String email) {
        SocialUser socialUser = new SocialUser();
        socialUser.setUsername(email);
        socialUser.setProvider("FACEBOOK");
        socialUser.setAccountNonExpired(true);
        socialUser.setAccountNonLocked(true);
        socialUser.setCredentialsNonExpired(true);
        socialUser.setEnabled(true);
        socialUser.addRole(this.roleService.getDefaultRole());

        return socialUser;
    }

    private void loginUser(SocialUser socialUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser.getUsername(), null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SocialUser user = this.socialUserRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(ErrorMessage.INVALID_CREDENTIALS);
        }

        return user;
    }
}
