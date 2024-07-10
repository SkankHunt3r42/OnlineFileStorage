package onlineFileStorage.root.app.FileStorage.securityConfigs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static onlineFileStorage.root.app.FileStorage.securityConfigs.constants.SecurityConstants.*;

@Component
public class JwtGenerator {

    public String generateToken(Authentication auth){
        String username = auth.getName();
        Date dateOfCreating = new Date();
        Date expirationTime = new Date(dateOfCreating.getTime() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(dateOfCreating)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS512,SECRET_PHRASE)
                .compact();
        return token;
    }

}
