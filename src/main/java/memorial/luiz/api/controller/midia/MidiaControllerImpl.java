package memorial.luiz.api.controller.midia;

import lombok.AllArgsConstructor;
import memorial.luiz.api.dto.midia.MidiaMapper;
import memorial.luiz.api.dto.midia.MidiaResponseDto;
import memorial.luiz.api.entity.Memoria;
import memorial.luiz.api.entity.Midia;
import memorial.luiz.api.entity.Solicitacao;
import memorial.luiz.api.entity.TipoMidia;
import memorial.luiz.api.service.MemoriaService;
import memorial.luiz.api.service.MidiaService;
import memorial.luiz.api.service.SolicitacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/midias")
@AllArgsConstructor
public class MidiaControllerImpl implements MidiaControllerDoc{

    private final MidiaService midiaService;
    private final MidiaMapper mapper;
    private final SolicitacaoService solicitacaoService;
    private final MemoriaService memoriaService;

    @Override
    public ResponseEntity<List<MidiaResponseDto>> upload(
            @RequestParam("arquivos") List<MultipartFile> arquivos,
            @RequestParam("tipo") TipoMidia tipo,
            @RequestParam(required = false) Integer solicitacaoId,
            @RequestParam(required = false) Integer memoriaId
    ) {

        Solicitacao solicitacao = solicitacaoId != null
                ? solicitacaoService.buscarPorId(solicitacaoId)
                : null;

        Memoria memoria = memoriaId != null
                ? memoriaService.buscarPorId(memoriaId)
                : null;

        List<MidiaResponseDto> midias = arquivos.stream()
                .map(a -> midiaService.salvarArquivo(a, tipo, solicitacao, memoria))
                .map(MidiaResponseDto::from)
                .toList();

        return ResponseEntity.status(200).body(midias);
    }

    @Override
    public ResponseEntity<List<MidiaResponseDto>> buscarPorSolicitacao(@PathVariable Integer id) {
       List<Midia> midias = midiaService.buscarPorSolicitacao(id);

       return midias.isEmpty()
               ? ResponseEntity.status(204).build()
               : ResponseEntity.status(200).body(midias.stream()
               .map(mapper::toResponse)
               .toList());
    }

    @Override
    public ResponseEntity<List<MidiaResponseDto>> buscarPorMemoria(@PathVariable Integer id) {
        List<Midia> midias = midiaService.buscarPorMemoria(id);

        return midias.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(midias.stream()
                .map(mapper::toResponse)
                .toList());
    }
}
