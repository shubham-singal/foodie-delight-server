package com.example.foodiedelight.repositories;

import com.example.foodiedelight.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository
        extends CrudRepository<User, Integer> {

  @Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
  public User findUserByCredentials(
          @Param("username") String username,
          @Param("password") String password
  );

  @Query("SELECT user from User user WHERE user.id=:userId")
  public User findUserById(@Param("userId") int uid);

  @Query(value="SELECT users.id, users.username FROM users ORDER BY users.id DESC LIMIT 10", nativeQuery=true)
  public List<Object[]> findRecentlyJoinedUsers();

  @Query(value="SELECT user FROM User user WHERE user.id=:userId")
  public User getProfileForUser(@Param("userId") int userId);
}
