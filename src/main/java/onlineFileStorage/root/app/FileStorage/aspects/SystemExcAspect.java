package onlineFileStorage.root.app.FileStorage.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * The aspect class - "SystemExcAspect" made in order to monitor the functionality
 * of the system methods like "LoadUserByUserName,generateJwt,etc.In word "System"
 * i mean the inner methods who supposed to be used by program
 */

@Component
@Aspect
@Slf4j
@Order(1)
public class SystemExcAspect {

    @Before("onlineFileStorage.root.app.FileStorage.aspects.sysp.SystemPoints.LoadUserByUserName()")
    public void LoadUserByUserName(JoinPoint point){
        String signature = point.getSignature().toShortString();
        log.warn("Executing method: " + signature);

    }
    @Before("onlineFileStorage.root.app.FileStorage.aspects.sysp.SystemPoints.GenerateToken()")
    public void GenerateToken(JoinPoint point){
        String signature = point.getSignature().toShortString();
        log.warn("Executing method: " + signature);

    }

}
