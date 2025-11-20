package memorial.luiz.api.dto.solicitacao;

import memorial.luiz.api.dto.status.StatusResponseDto;
import memorial.luiz.api.dto.usuario.UsuarioResponseDto;

public record SolicitacaoResponseDto(
        Integer id,
        String titulo,
        String descricao,
        UsuarioResponseDto usuario,
        StatusResponseDto status
) {
}
