package memorial.luiz.api.dto.usuario;

import memorial.luiz.api.dto.role.RoleResponseDto;

public record UsuarioResponseDto(
        Integer id,
        String nome,
        String email,
        String telefone,
        RoleResponseDto role
) {
}
