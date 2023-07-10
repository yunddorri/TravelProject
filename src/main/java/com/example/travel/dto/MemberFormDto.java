package com.example.travel.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message="이름을 입력해주세요.")
    private String name;
    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message="이메일 형식으로 입력해주세요.")
    private String email;
    @NotBlank(message="비밀번호를 입력해주세요.")
    @Length(min=8, max=16, message="비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;
}