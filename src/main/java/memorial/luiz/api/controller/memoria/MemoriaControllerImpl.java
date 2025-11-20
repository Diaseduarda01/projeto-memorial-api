package memorial.luiz.api.controller.memoria;

import lombok.RequiredArgsConstructor;
import memorial.luiz.api.dto.memoria.MemoriaMapper;
import memorial.luiz.api.dto.memoria.MemoriaResponseDto;
import memorial.luiz.api.entity.Memoria;
import memorial.luiz.api.service.MemoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/memorias")
@RequiredArgsConstructor
public class MemoriaControllerImpl implements MemoriaControllerDoc{

    private final MemoriaService service;
    private final MemoriaMapper mapper;

    @Override
    public ResponseEntity<List<MemoriaResponseDto>> listar() {
        List<Memoria> memorias = service.listar();

        return memorias.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(memorias.stream()
                    .map(mapper::toResponse)
                    .toList());
    }
}
