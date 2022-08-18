package bg.jug.listener;


import bg.jug.model.AppUser;
import bg.jug.model.entity.MyUser;
import bg.jug.service.impl.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@Configuration
public class AuthenticationSuccessListener {
    private LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void  onAuthenticationSuccess(AuthenticationSuccessEvent event){
        Object principal = event.getAuthentication().getPrincipal();
        //TODO check if AppUser is the right class
        if(principal instanceof AppUser){
//            MyUser myUser = (MyUser) event.getAuthentication().getPrincipal();
            AppUser myUser = (AppUser) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCache(myUser.getUsername());
        }
    }
}
