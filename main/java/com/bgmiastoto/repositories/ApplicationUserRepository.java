package com.bgmiastoto.repositories;

import com.bgmiastoto.entities.users.ApplicationUser;
import com.bgmiastoto.entities.users.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends UserRepository<ApplicationUser> {

}
