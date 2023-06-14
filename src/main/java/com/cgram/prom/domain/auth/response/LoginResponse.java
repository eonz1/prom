package com.cgram.prom.domain.auth.response;

import com.cgram.prom.domain.profile.domain.Profile;
import com.cgram.prom.global.security.jwt.domain.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Token token;

    private String profileId;

    @Builder
    public LoginResponse(Token token, String profileId) {
        this.token = token;
        this.profileId = profileId;
    }
}
