package bg.jug.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {
    private String username;
    private String password; //TODO Check here ?????
    private String email;
    private String role;
}
