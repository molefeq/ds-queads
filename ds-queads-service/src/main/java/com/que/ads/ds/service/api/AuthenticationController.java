package com.que.ads.ds.service.api;

import com.que.ads.ds.security.models.request.LoginModel;
import com.que.ads.ds.security.models.request.RegisterModel;
import com.que.ads.ds.security.models.request.TokenRefreshModel;
import com.que.ads.ds.security.models.response.AuthenticationResponseModel;
import com.que.ads.ds.security.models.response.RegisterResponseModel;
import com.que.ads.ds.security.service.AuthenticationService;
import com.que.ads.ds.security.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public AuthenticationResponseModel login(@Valid @RequestBody LoginModel loginModel, HttpServletResponse servletResponse) {
        return authenticationService.authenticate(loginModel);
    }

    @PostMapping("/refresh")
    public AuthenticationResponseModel refresh(@RequestBody TokenRefreshModel tokenRefreshModel, HttpServletResponse servletResponse) {
        return authenticationService.refreshToken(tokenRefreshModel);
    }

    @PostMapping("/register")
    public RegisterResponseModel register(@RequestBody RegisterModel registerModel, HttpServletResponse servletResponse) {
        return userService.register(registerModel);
    }

    @GetMapping("/check-email-address/{emailAddress}")
    public void checkEmail(@PathVariable String emailAddress) {
        userService.checkEmailAddress(emailAddress);
    }

    @GetMapping("/check-username/{username}")
    public void checkUsername(@PathVariable String username) {
        userService.checkUsername(username);
    }
}
