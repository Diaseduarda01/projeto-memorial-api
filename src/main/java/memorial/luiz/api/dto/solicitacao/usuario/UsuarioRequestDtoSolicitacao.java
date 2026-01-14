package memorial.luiz.api.dto.solicitacao.usuario;

import memorial.luiz.api.dto.role.RoleResponseDto;

public record UsuarioRequestDtoSolicitacao(
        String nome,
        String email,
        String relacao,
        RoleResponseDto role
) {
}
