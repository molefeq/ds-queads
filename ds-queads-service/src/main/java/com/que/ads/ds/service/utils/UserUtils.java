package com.que.ads.ds.service.utils;

import com.que.ads.ds.security.models.security.AuthenticationDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static int getUserId(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication.getDetails() instanceof AuthenticationDetails)){
            throw new RuntimeException("");
        }

        return ((AuthenticationDetails) authentication.getDetails()).getUserId();
    }
}
