package com.ecommerce.project.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
    private List<String> roles;

}