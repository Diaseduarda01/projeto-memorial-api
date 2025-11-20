package memorial.luiz.api.dto.solicitacao;

import lombok.AllArgsConstructor;
import memorial.luiz.api.dto.status.StatusMapper;
import memorial.luiz.api.dto.usuario.UsuarioMapper;
import memorial.luiz.api.entity.Solicitacao;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SolicitacaoMapper {

    private final StatusMapper statusMapper;
    private final UsuarioMapper usuarioMapper;

    public Solicitacao toEntity(SolicitacaoRequestDto dto) {
        if (dto == null) return null;

        Solicitacao solicitacao = new Solicitacao(
                dto.titulo(),
                dto.descricao()
        );

        solicitacao.setUsuario(usuarioMapper.toEntity(dto.usuario()));

        return solicitacao;
    }

    public SolicitacaoResponseDto toResponse(Solicitacao solicitacao) {
        if (solicitacao == null) return null;

        return new SolicitacaoResponseDto(
                solicitacao.getId(),
                solicitacao.getTitulo(),
                solicitacao.getDescricao(),
                usuarioMapper.toResponse(solicitacao.getUsuario()),
                statusMapper.toResponse(solicitacao.getStatus())
        );
    }
}
