package com.graodireto.quemtemboca.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graodireto.quemtemboca.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
