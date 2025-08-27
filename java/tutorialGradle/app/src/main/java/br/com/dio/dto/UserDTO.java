package br.com.dio.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private int id;
    private LocalDate birthday;
}
