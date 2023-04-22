package com.que.ads.ds.service.api;

import com.que.ads.ds.common.data.ErrorResponse;
import com.que.ads.ds.security.models.request.LoginModel;
import com.que.ads.ds.security.models.request.RegisterModel;
import com.que.ads.ds.security.models.request.TokenRefreshModel;
import com.que.ads.ds.security.models.response.AuthenticationResponseModel;
import com.que.ads.ds.security.models.response.RegisterResponseModel;
import com.que.ads.ds.security.service.AuthenticationService;
import com.que.ads.ds.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Authentication", description = "Authentication management endpoints")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(description = "login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseModel.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public AuthenticationResponseModel login(@Valid @RequestBody LoginModel loginModel) {
        return authenticationService.authenticate(loginModel);
    }

    @PostMapping("/refresh")
    @Operation(description = "refresh token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseModel.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public AuthenticationResponseModel refresh(@RequestBody TokenRefreshModel tokenRefreshModel) {
        return authenticationService.refreshToken(tokenRefreshModel);
    }

    @PostMapping("/register")
    @Operation(description = "register a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseModel.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public RegisterResponseModel register(@RequestBody RegisterModel registerModel) {
        return userService.register(registerModel);
    }

    @GetMapping("/check-email-address/{emailAddress}")
    @Operation(description = "check the user email address is unique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "emailAddress", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "Email address you are checking against.")
    public void checkEmail(@PathVariable String emailAddress) {
        userService.checkEmailAddress(emailAddress);
    }

    @GetMapping("/check-username/{username}")
    @Operation(description = "check the username is unique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "username", required = true, in = ParameterIn.PATH, schema = @Schema(type = "string"), description = "Username you are checking against.")
    public void checkUsername(@PathVariable String username) {
        userService.checkUsername(username);
    }
}
