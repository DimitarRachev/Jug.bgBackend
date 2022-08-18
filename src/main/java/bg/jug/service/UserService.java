package bg.jug.service;

import bg.jug.model.dto.UserRequestDto;
import bg.jug.model.dto.UserResponseDto;
import bg.jug.model.entity.MyUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserService extends UserDetailsService {
     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserResponseDto> getAll();

    int create(UserRequestDto userRequestDto);

    UserResponseDto getByID(Integer id);

    UserResponseDto update(Integer id, UserRequestDto requestDto);

    UserResponseDto delete(Integer id) ;
}
