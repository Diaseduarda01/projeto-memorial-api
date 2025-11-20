package memorial.luiz.api.dto.solicitacao;

import memorial.luiz.api.dto.usuario.UsuarioResponseDto;

public record SolicitacaoRequestDto(
        String titulo,
        String descricao,
        UsuarioResponseDto usuario
) {
}
