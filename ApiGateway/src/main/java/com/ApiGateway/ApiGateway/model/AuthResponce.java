package com.ApiGateway.ApiGateway.model;

import lombok.*;

import java.util.Collection;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponce {
    private String userId;
    private String accesToken;
    private String refreshToken;
    private long expireAt;
    private Collection<String> authorities;
}
