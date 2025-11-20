package memorial.luiz.api.dto.role;

import memorial.luiz.api.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequestDto dto) {
        if (dto == null) return null;

        return new Role(
                dto.nome()
        );
    }

    public RoleResponseDto toResponse(Role role) {
        if (role == null) return null;

        return new RoleResponseDto(
                role.getId(),
                role.getNome()
        );
    }
}
