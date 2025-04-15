package com.alibou.spring_security.modules.user.dto;

import com.alibou.spring_security.modules.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDto {
    private String fullname;
    private String address;
    private String phoneNumber;
}
