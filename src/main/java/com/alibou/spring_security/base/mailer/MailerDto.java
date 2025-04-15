package com.alibou.spring_security.base.mailer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailerDto {
    private String to;
    private String subject;
    private String content;
}
