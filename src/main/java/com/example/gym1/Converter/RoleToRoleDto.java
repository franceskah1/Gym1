package com.example.gym1.Converter;
import com.example.gym1.Dto.RoleDto;
import com.example.gym1.Model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class RoleToRoleDto implements Converter<Role, RoleDto> {
    @Override
    public RoleDto convert(Role source) {
        if (source!=null){
            RoleDto roleDto=new RoleDto();
            roleDto.setRoleName(source.getName());
            roleDto.setId(source.getId());
            return roleDto;

        }
        return null;
    }
}
