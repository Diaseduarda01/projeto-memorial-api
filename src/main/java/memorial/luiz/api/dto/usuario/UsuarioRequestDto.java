package memorial.luiz.api.dto.usuario;

import memorial.luiz.api.dto.role.RoleRequestDto;

public record UsuarioRequestDto(
        String nome,
        String email,
        String telefone,
        String senha,
        RoleRequestDto role
) {
}
