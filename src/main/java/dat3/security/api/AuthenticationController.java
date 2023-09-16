package dat3.security.api;

import dat3.car.dto.LoginRequest;
import dat3.car.dto.LoginResponse;
import dat3.car.entity.Member;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import dat3.security.service.UserDetailsServiceImp;

import javax.management.relation.RoleInfo;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class AuthenticationController<JwtClaimsSet, JwsHeader> {

    @Value("${app.token-issuer}")
    private String tokenIssuer;

    @Value("${app.token-expiration}")
    private long tokenExpiration;

    private AuthenticationManager authenticationManager;

    OAuth2ResourceServerProperties.Jwt encoder;

    public AuthenticationController(AuthenticationManager authenticationManager, OAuth2ResourceServerProperties.Jwt encoder) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }
}
/*
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        try {
            UsernamePasswordAuthenticationToken uat = new UsernamePasswordAuthenticationToken(request.getClass().getName(), request.getClass().getName());
            Authentication authentication = authenticationManager.authenticate(uat);

            UserWithRoles user = (UserWithRoles) authentication.getPrincipal();
            Instant now = Instant.now();
            long expiry = tokenExpiration;
            String scope = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(joining(" "));

            JwtClaimsSet claims = JwtClaimsSet.builder().issuer(tokenIssuer)  //Only this for simplicity
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(tokenExpiration))
                    .subject(user.getUsername())
                    .claim("roles", scope)
                    .build();
            JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
            String token = encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();


            List<String> roles = user.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
            return ResponseEntity.ok()
                    .body(new LoginResponse(user.getUsername(), token, roles));

        } catch (BadCredentialsException e) {
          throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UserDetailsServiceImp.WRONG_USERNAME_OR_PASSWORD);
        }

    }
 }
*/
