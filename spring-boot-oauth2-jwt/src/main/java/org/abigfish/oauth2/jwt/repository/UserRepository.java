package org.abigfish.oauth2.jwt.repository;

import org.abigfish.oauth2.jwt.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	//@Query("SELECT u FROM User u WHERE (u.deletedOn > CURRENT_TIMESTAMP OR u.deletedOn IS NULL) AND u.username = :username")
	//User findActiveByUsername(@Param("username") String username);

	//@Query("SELECT u FROM User u WHERE (u.deletedOn > CURRENT_TIMESTAMP OR u.deletedOn IS NULL) AND u.email = :email")
	//User findActiveByEmail(@Param("email") String email);

	User findByUsername(String username);

	User findByEmail(String email);
}
