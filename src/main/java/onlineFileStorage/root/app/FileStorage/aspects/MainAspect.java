package onlineFileStorage.root.app.FileStorage.aspects;

import lombok.extern.slf4j.Slf4j;
import onlineFileStorage.root.app.FileStorage.models.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
Aspect-class made in order to monitor the functionality of http request,
 which server should handle and give the response
 */

@Aspect
@Component
@Slf4j
@Order(0)
public class MainAspect {

    @Before("execution (* onlineFileStorage.root.app.FileStorage.controllers.MainController.*(..))")
    public void MainControllersRequests(JoinPoint point){
        String signature = point.getSignature().toShortString();
        log.warn( "Executing method - " + signature);
        Object args[] = point.getArgs();
        for(Object arg : args){
            UserEntity entity = (UserEntity) arg;
            log.warn("\nUser name: "
                    + entity.getUsername()
                    +"\nUser password(Encoded format): " + entity.getPassword()
                    +"\nUser email: " + entity.getEmail());
        }

    }
}
