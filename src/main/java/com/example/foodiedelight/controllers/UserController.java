package com.example.foodiedelight.controllers;

import com.example.foodiedelight.models.User;
import com.example.foodiedelight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
  @Autowired
  UserRepository repository;

  @PostMapping("/logout")
  public void logout(HttpSession session) {
    session.invalidate();
  }

  @PostMapping("/login")
  public User login(HttpSession session,
                    @RequestBody User user,
                    HttpServletResponse res) {
    User profile = repository.findUserByCredentials(user.getUsername(), user.getPassword());
    if (profile != null) {
      session.setAttribute("profile", profile);
      return profile;
    } else {
      res.setStatus(403);
      return null;
    }
  }

  @PostMapping("/register")
  public User register(HttpSession session,
                       @RequestBody User user) {
    User newUser = repository.save(user);
    newUser.setPassword("***");
    session.setAttribute("profile", newUser);
    return newUser;
  }

  @PostMapping("/profile")
  public User profile(HttpSession session, HttpServletResponse res) {
    User profile = (User) session.getAttribute("profile");
    if (profile != null)
      return profile;
    else {
      res.setStatus(403);
      return null;
    }
  }

  @GetMapping("/profile/{userId}")
  public User getProfileForUser(@PathVariable("userId") int uid) {
    User profile = repository.getProfileForUser(uid);
    profile.setPassword("***");
    return profile;
  }

  @GetMapping("/users")
  public List<Object[]> findRecentlyJoinedUsers() {
    return repository.findRecentlyJoinedUsers();
  }

  @PutMapping("/api/users/{userId}")
  public int updateProfile(
          @PathVariable("userId") int uid,
          @RequestBody User user,
          HttpSession session) {
    User oldUser = repository.findUserById(uid);
    oldUser.setFirstName(user.getFirstName());
    oldUser.setLastName(user.getLastName());
    oldUser.setEmail(user.getEmail());
    repository.save(oldUser);
    session.setAttribute("profile", oldUser);
    return 1;
  }
}

