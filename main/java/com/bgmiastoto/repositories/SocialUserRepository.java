package com.bgmiastoto.repositories;

import com.bgmiastoto.entities.users.SocialUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialUserRepository extends UserRepository<SocialUser> {
}
