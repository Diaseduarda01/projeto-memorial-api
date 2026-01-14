package memorial.luiz.api.dto.solicitacao;

import memorial.luiz.api.dto.solicitacao.usuario.UsuarioRequestDtoSolicitacao;

public record SolicitacaoRequestDto(
        String titulo,
        String descricao,
        UsuarioRequestDtoSolicitacao usuario
) {
}
