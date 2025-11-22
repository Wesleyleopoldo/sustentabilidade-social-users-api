package com.syntech.sustentabilidadesocial.sustentabilidade_social.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RequestUser {
    
    @Autowired
    private HttpServletRequest request;

    public String getUserId() {
        return (String) request.getAttribute("userId");
    }
}
