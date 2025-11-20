package memorial.luiz.api.controller.midia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import memorial.luiz.api.dto.midia.MidiaResponseDto;
import memorial.luiz.api.entity.TipoMidia;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Mídias", description = "Operações relacionadas ao gerenciamento de mídias")
public interface MidiaControllerDoc {

    @PostMapping("/upload")
    @Operation(
            summary = "Upload de mídias",
            description = """
            Realiza o upload de um ou vários arquivos de mídia, vinculando-os a uma solicitação
            ou memória, de acordo com os parâmetros enviados.
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Mídias enviadas e salvas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MidiaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Quando os parâmetros obrigatórios não são informados ou os arquivos são inválidos"
            )
    })
    ResponseEntity<List<MidiaResponseDto>> upload(
            @RequestParam("arquivos") List<MultipartFile> arquivos,
            @RequestParam("tipo") TipoMidia tipo,
            @RequestParam(required = false) Integer solicitacaoId,
            @RequestParam(required = false) Integer memoriaId
    );

    @GetMapping("/solicitacao/{id}")
    @Operation(
            summary = "Listar mídias por solicitação",
            description = """
            Retorna todas as mídias vinculadas a uma solicitação específica.
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mídias encontradas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MidiaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhuma mídia encontrada para a solicitação informada"
            )
    })
    ResponseEntity<List<MidiaResponseDto>> buscarPorSolicitacao(@PathVariable Integer id);

    @GetMapping("/memoria/{id}")
    @Operation(
            summary = "Listar mídias por memória",
            description = """
            Retorna todas as mídias vinculadas a uma memória específica.
            """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mídias encontradas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MidiaResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhuma mídia encontrada para a memória informada"
            )
    })
    ResponseEntity<List<MidiaResponseDto>> buscarPorMemoria(@PathVariable Integer id);
}
