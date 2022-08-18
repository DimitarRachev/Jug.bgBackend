package bg.jug.model;

import bg.jug.model.entity.MyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class AppUser implements UserDetails {
private final MyUser myUser;

    public AppUser(MyUser myUser) {
        this.myUser = myUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + myUser.getRole()));
    }

    @Override
    public String getPassword() {
        return myUser.getPassword();
    }

    @Override
    public String getUsername() {
        return myUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return myUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return myUser.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return myUser.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return myUser.isEnabled();
    }
}
