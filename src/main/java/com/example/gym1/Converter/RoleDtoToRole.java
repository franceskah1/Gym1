package com.example.gym1.Converter;

import com.example.gym1.Dto.RoleDto;
import com.example.gym1.Model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoToRole implements Converter<RoleDto, Role> {

    @Override
    public Role convert(RoleDto source) {
        if (source != null){
            Role role = new Role();
            if (source.getId() != null) role.setId(source.getId());
            role.setName(source.getRoleName());
            return role;
        }
        return null;
    }
}

