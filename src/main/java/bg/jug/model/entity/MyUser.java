package bg.jug.model.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String role;

    @Column(columnDefinition = "boolean default true")
    private  boolean isAccountNonExpired = true;

    @Column(columnDefinition = "boolean default true")
    private boolean isAccountNonLocked = true;

    @Column(columnDefinition = "boolean default true")
    private boolean isCredentialsNonExpired = true;

    @Column(columnDefinition = "boolean default true")
    private  boolean isEnabled = true;



}
