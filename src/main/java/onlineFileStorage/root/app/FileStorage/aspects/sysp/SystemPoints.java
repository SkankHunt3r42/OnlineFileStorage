package onlineFileStorage.root.app.FileStorage.aspects.sysp;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Package named as "Sysp" - this might be a little confusing, but its just abbreviation to
 * "System Points",i use this class to make a Point cut`s which should simplify work with
 *  paths of method`s.So instead of #(onlineFileStorage.root.app.FileStorage.*.*(..)#
 * i use - (onlineFileStorage.root.app.FileStorage.sysp.SystemPoints.POINT_NAME()).
 */

@Aspect
public class SystemPoints {

    //CustomServicePoint
    @Pointcut("execution(* onlineFileStorage.root.app.FileStorage.securityConfigs.CustomService.loadUserByUsername(..))")
    public void LoadUserByUserName() {}

    //JwtPoint`s
    @Pointcut("execution(* onlineFileStorage.root.app.FileStorage.securityConfigs.JwtGenerator.generateToken(..))")
    public void GenerateToken(){}



}
