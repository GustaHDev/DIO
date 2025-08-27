package br.com.dio.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserModel {
    private String username;
    private int code;
    private LocalDate birthday;
}
