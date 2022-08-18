package bg.jug.service.impl;

import bg.jug.model.AppUser;
import bg.jug.model.dto.UserRequest;
import bg.jug.model.dto.UserResponse;
import bg.jug.model.entity.MyUser;
import bg.jug.repository.UserRepository;
import bg.jug.service.UserService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoginAttemptService loggingAttemptService;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MyUser> userEntity = userRepository.findByUsername(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        } else {
            validateLoginAttempt(userEntity.get());
            return new AppUser(userEntity.get());
        }


    }

    @Override
    public List<UserResponse> getAll() {
        List<MyUser> users = userRepository.findAll();
        return users.stream().map(u -> mapper.map(u, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public int create(UserRequest userRequest) {
      MyUser user = new MyUser();
      user.setUsername(userRequest.getUsername());
      user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
      user.setEmail(userRequest.getEmail());
      user.setRole(userRequest.getRole());
      return userRepository.save(user).getId();

    }

    @Override
    public UserResponse getByID(Integer id) {
        return mapper.map(userRepository.getReferenceById(id), UserResponse.class);
    }

    @Override
    public UserResponse update(Integer id, UserRequest userRequest) {
        MyUser user = userRepository.getReferenceById(id);
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        return mapper.map( userRepository.save(user), UserResponse.class);
    }

    @Override
    public UserResponse delete(Integer id) {
       MyUser user = userRepository.getReferenceById(id);
        UserResponse responseDto = mapper.map(user, UserResponse.class);
        userRepository.deleteById(id);
        return responseDto;
    }


    private void validateLoginAttempt(MyUser myUser) {
        if (myUser.isAccountNonLocked()) {
            if (loggingAttemptService.hasExceededMaxAttempts(myUser.getUsername())) {
                myUser.setAccountNonLocked(false);
            } else {
                myUser.setAccountNonLocked(true);
            }
        } else {
            loggingAttemptService.evictUserFromLoginAttemptCache(myUser.getUsername());
        }
    }
}
