package bg.jug.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
    private Integer id;
    private String username;
    private String email;
    private String role;

}
