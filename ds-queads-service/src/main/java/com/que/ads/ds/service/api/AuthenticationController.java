package com.que.ads.ds.service.api;

import com.que.ads.ds.common.data.ErrorResponse;
import com.que.ads.ds.security.models.request.*;
import com.que.ads.ds.security.models.response.AuthenticationResponseModel;
import com.que.ads.ds.security.models.response.ForgotPasswordResponseModel;
import com.que.ads.ds.security.models.response.RegisterResponseModel;
import com.que.ads.ds.security.models.response.ResetPasswordResponseModel;
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
    public AuthenticationResponseModel refresh(@RequestBody @Valid TokenRefreshModel tokenRefreshModel) {
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
    public RegisterResponseModel register(@RequestBody @Valid RegisterModel registerModel) {
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

    @PostMapping("/forgot-password")
    @Operation(description = "forgot password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForgotPasswordModel.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ForgotPasswordResponseModel forgotPassword(@RequestBody @Valid ForgotPasswordModel forgotPasswordModel) {
        return userService.forgotPassword(forgotPasswordModel);
    }

    @PostMapping("/reset-password")
    @Operation(description = "reset password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResetPasswordModel.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "key", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"), description = "Forgot password key which was set when ypu request to reset the password.")
    public ResetPasswordResponseModel forgotPassword(@RequestBody @Valid ResetPasswordModel resetPasswordModel,
                                                     @RequestParam(name = "key") String forgotPasswordKey) {
        return userService.resetPassword(resetPasswordModel, forgotPasswordKey);
    }
}
