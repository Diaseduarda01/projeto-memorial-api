package memorial.luiz.api.controller.solicitacao;

import lombok.RequiredArgsConstructor;
import memorial.luiz.api.dto.solicitacao.SolicitacaoMapper;
import memorial.luiz.api.dto.solicitacao.SolicitacaoRequestDto;
import memorial.luiz.api.dto.solicitacao.SolicitacaoResponseDto;
import memorial.luiz.api.dto.usuario.UsuarioRequestDto;
import memorial.luiz.api.dto.usuario.UsuarioResponseDto;
import memorial.luiz.api.entity.Solicitacao;
import memorial.luiz.api.service.SolicitacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/solicitacoes")
@RequiredArgsConstructor
public class SolicitacaoControllerImpl implements SolicitacaoControllerDoc {

    private final SolicitacaoMapper mapper;
    private final SolicitacaoService service;

    @Override
    public ResponseEntity<SolicitacaoResponseDto> cadastrar(SolicitacaoRequestDto dto) {
        Solicitacao solicitacao = mapper.toEntity(dto);
        Solicitacao cadastrado = service.cadastrar(solicitacao);
        return ResponseEntity.status(201).body(mapper.toResponse(cadastrado));
    }

    @Override
    public ResponseEntity<List<SolicitacaoResponseDto>> listarPoritulo(@RequestParam(required = false) String titulo) {
        List<Solicitacao> pendentes = service.listarPorTitulo(titulo);
        if (pendentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<SolicitacaoResponseDto> dtos = pendentes.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<SolicitacaoResponseDto>> listar(String status) {
        List<Solicitacao> pendentes = service.listar(status);
        if (pendentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<SolicitacaoResponseDto> dtos = pendentes.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Void> aceitarSolicitacao( @PathVariable Integer id) {
        service.aceitarSolicitacao(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> recusarSolicitacao(Integer id) {
        service.recusarSolicitacao(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SolicitacaoResponseDto> atualizar(Integer id, SolicitacaoRequestDto dto) {
       Solicitacao solicitacao = mapper.toEntity(dto);
       Solicitacao solicitacaoAtualizada = service.atualizar(id, solicitacao);
       return ResponseEntity.status(200).body(mapper.toResponse(solicitacaoAtualizada));
    }

    @Override
    public ResponseEntity<String> deletar(Integer id) {
        service.deletar(id);
        return ResponseEntity.status(200).body("Solicitação deletada com sucesso!");
    }
}
