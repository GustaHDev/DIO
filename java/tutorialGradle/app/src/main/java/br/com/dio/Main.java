package br.com.dio;

import org.mapstruct.factory.Mappers;
import br.com.dio.mapper.UserMapper;
import java.time.LocalDate;
import br.com.dio.model.UserModel;
import br.com.dio.dto.UserDTO;

public class Main {

    private final static UserMapper mapper = Mappers.getMapper(UserMapper.class);
    public static void main(String[] args) {
        var model = new UserModel();
        model.setUsername("Mario");
        model.setCode(1);
        model.setBirthday(LocalDate.now().minusYears(30));

        var dto = new UserDTO();
        dto.setName("Ana");
        dto.setId(2);
        dto.setBirthday(LocalDate.now().minusYears(40));

        System.out.println(mapper.toModel(dto));
    }
}
