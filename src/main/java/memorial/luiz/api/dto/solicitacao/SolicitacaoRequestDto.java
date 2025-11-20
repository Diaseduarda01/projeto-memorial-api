package memorial.luiz.api.dto.solicitacao;

import memorial.luiz.api.dto.status.StatusRequestDto;
import memorial.luiz.api.dto.usuario.UsuarioRequestDto;

public record SolicitacaoRequestDto(
        String titulo,
        String descricao,
        UsuarioRequestDto usuario,
        StatusRequestDto status
) {
}
