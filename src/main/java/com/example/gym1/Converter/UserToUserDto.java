package com.example.gym1.Converter;

import com.example.gym1.Dto.UserDto;
import com.example.gym1.Model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToUserDto implements Converter<User, UserDto> {
    private final RoleToRoleDto roleToRoleDto;

    public UserToUserDto(RoleToRoleDto roleToRoleDto) {
        this.roleToRoleDto = roleToRoleDto;
    }

    @Override
    public UserDto convert(User source) {
        if(source!=null){
            UserDto userDto=new UserDto();
            userDto.setUserName(source.getUsername());
            userDto.setLastName(source.getLastName());
            userDto.setEmail(source.getEmail());
            userDto.setAddress(source.getAddress());
            userDto.setRoles(source.getRoles().stream().map(role -> roleToRoleDto.convert(role)).collect(Collectors.toSet()));
            userDto.setPhoneNumber(source.getPhoneNumber());
            return userDto;
        }
        return null;
    }
}
