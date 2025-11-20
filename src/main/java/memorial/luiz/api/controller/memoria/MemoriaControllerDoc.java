package memorial.luiz.api.controller.memoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import memorial.luiz.api.dto.memoria.MemoriaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(name = "Memórias", description = "Operações relacionadas ao gerenciamento de mémorias")
public interface MemoriaControllerDoc {

    @GetMapping
    @Operation(
            summary = "Listar memórias",
            description = """
            Lista todas as memórias cadastradas.
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Quando existem memórias cadastradas no banco de dados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MemoriaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Quando não existe nenhuma memória no banco de dados"
            )
    })
    ResponseEntity<List<MemoriaResponseDto>> listar();
}
