package onlineFileStorage.root.app.FileStorage.securityConfigs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static onlineFileStorage.root.app.FileStorage.securityConfigs.constants.SecurityConstants.*;

@Component
public class JwtCustomFilter extends OncePerRequestFilter {
    @Autowired
    private CustomService service;



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = header.substring(7);
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_PHRASE)
                    .parseClaimsJws(token)
                    .getBody();
            UserDetails details = service.loadUserByUsername(claims.getSubject());
            if(details != null){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(details.getUsername(),null,details.getAuthorities() );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (JwtException e) {
            throw new RuntimeException("Jwt is invalid, or expired");
        }
        filterChain.doFilter(request,response);

    }
}
