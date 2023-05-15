package com.example.gym1.Converter;

import com.example.gym1.Dto.UserDto;
import com.example.gym1.Model.User;
import com.example.gym1.Utils.GenderUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoToUser implements Converter<UserDto, User> {
    private final RoleDtoToRole roleDtoToRole;

    public UserDtoToUser(RoleDtoToRole roleDtoToRole) {
        this.roleDtoToRole = roleDtoToRole;
    }

    @Override
    public User convert(UserDto source) {
        if (source!=null){
            User user=new User();
            if (source.getRoles() != null)
                user.setRoles(source.getRoles().stream().map(roleDTO -> roleDtoToRole.convert(roleDTO)).collect(Collectors.toSet()));
            user.setEmail(source.getEmail());
            user.setUserName(source.getUserName());
            user.setGender(GenderUtils.getGender(source.getGender()));
            user.setPassword(source.getPassword());
            user.setPhoneNumber(source.getPhoneNumber());
            user.setAddress(source.getAddress());
            return user;

        }
        return null;
    }
}
