package memorial.luiz.api.dto.midia;

import lombok.AllArgsConstructor;
import memorial.luiz.api.entity.Memoria;
import memorial.luiz.api.entity.Midia;
import memorial.luiz.api.entity.Solicitacao;
import memorial.luiz.api.service.MemoriaService;
import memorial.luiz.api.service.SolicitacaoService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
public class MidiaMapper {

    public MidiaResponseDto toResponse(Midia midia) {
        if (midia == null) return null;

        return new MidiaResponseDto(
                midia.getId(),
                midia.getNome(),
                "http://localhost:8080/upload/" + midia.getCaminho(),
                midia.getTipo()
        );
    }
}
