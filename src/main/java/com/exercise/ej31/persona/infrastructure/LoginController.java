package com.exercise.ej31.persona.infrastructure;

import com.exercise.ej31.persona.application.IPersona;
import com.exercise.ej31.shared.NotFoundException;
import com.exercise.ej31.shared.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    IPersona personaService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam("user") String username, @RequestParam("password") String pwd)
            throws NotFoundException,UnprocesableException
    {
        PersonaListaOutputDTO lista = personaService.getByUser(username,"");
        if (lista.total_items == 0) throw new NotFoundException("Usuario "+username+" no encontrado");
        if (lista.total_items > 1) throw new UnprocesableException("Usuario ambiguo");
        PersonaOutputDTO pOut = lista.items.get(0);
        String password = pOut.getPassword();
        if (!pwd.equals(password)) throw new UnprocesableException("Password erróneo");
        String rol = (pOut.getAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
        return new ResponseEntity<>(getJWTToken(username, rol), HttpStatus.OK);
    }

    private String getJWTToken(String username, String rol) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
