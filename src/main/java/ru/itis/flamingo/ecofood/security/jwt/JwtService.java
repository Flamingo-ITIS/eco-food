package ru.itis.flamingo.ecofood.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.exception.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationInMs}")
    private int jwtExpirationInMs;

    private final ConcurrentLinkedQueue<String> expireTokens = new ConcurrentLinkedQueue<>();

    public JwtToken generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        claims.put("auth", authentication.getAuthorities());
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpirationInMs);

        return new JwtToken(Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact());
    }

    public Authentication getAuthentication(JwtToken token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token.getToken())
                .getBody();
        List<String> roles = (List<String>)claims.get("auth");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(getUsername(token), null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        return auth;
    }

    public String getUsername(JwtToken token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token.getToken()).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return  null;
    }

    public boolean validateToken(String token) {
        try {
            if (expireTokens.contains(token)) {
                throw new UnauthorizedException("Expired or invalid JWT token");
            }
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new UnauthorizedException("Expired or invalid JWT token");
        }
    }

    public void addExpireToken(String token) {
        expireTokens.add(token);
    }

}
