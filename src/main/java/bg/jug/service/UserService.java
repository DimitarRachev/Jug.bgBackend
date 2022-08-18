package bg.jug.service;

import bg.jug.model.dto.UserRequest;
import bg.jug.model.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserResponse> getAll();

    int create(UserRequest userRequest);

    UserResponse getByID(Integer id);

    UserResponse update(Integer id, UserRequest requestDto);

    UserResponse delete(Integer id) ;
}
