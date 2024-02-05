package com.ApiGateway.ApiGateway.controller;

import com.ApiGateway.ApiGateway.model.AuthResponce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger= LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponce> login(
            @RegisteredOAuth2AuthorizedClient ("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user,
            Model model
            ){

        logger.info("user email id: {}",user.getEmail());

        AuthResponce authResponce =new AuthResponce();
        authResponce.setUserId(user.getEmail());
        authResponce.setAccesToken(client.getAccessToken().getTokenValue());

        authResponce.setRefreshToken(client.getRefreshToken().getTokenValue());

        authResponce.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

        List<String> authorities =user.getAuthorities().stream().map(g->{
            return g.getAuthority( );
        }).collect(Collectors.toList());

        authResponce.setAuthorities(authorities);

        return new ResponseEntity<>(authResponce, HttpStatus.OK);
    }
}
