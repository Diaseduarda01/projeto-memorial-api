package memorial.luiz.api.controller.solicitacao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import memorial.luiz.api.dto.auth.AuthRequestDto;
import memorial.luiz.api.dto.solicitacao.SolicitacaoRequestDto;
import memorial.luiz.api.dto.solicitacao.SolicitacaoResponseDto;
import memorial.luiz.api.dto.usuario.UsuarioRequestDto;
import memorial.luiz.api.dto.usuario.UsuarioResponseDto;
import memorial.luiz.api.entity.Solicitacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Solicitações", description = "Operações relacionadas ao gerenciamento de solicitações de mémoria")
public interface SolicitacaoControllerDoc {

    @PostMapping
    @Operation(summary = "Cadastrar solicitação", description = """
            Cadastrar uma nova solicitação
            ---
            Cadastrar uma nova solicitação no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitação cadastrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoResponseDto.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Erro no corpo da requisição")
    })
    ResponseEntity<SolicitacaoResponseDto> cadastrar(@RequestBody SolicitacaoRequestDto dto);

    @GetMapping("findAllBy")
    @Operation(
            summary = "Buscar solicitações por titulo",
            description = """
                Lista todas as solicitações cadastradas, com opção de filtrar por titulo via parâmetro.
                ---
                Se o parâmetro 'titulo' for informado, filtra pelo titulo do solicitante (busca parcial).
                Exemplo:
                /solicitacao?titulo=Maria
                """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitações encontradas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoResponseDto.class)
                    )),
            @ApiResponse(responseCode = "204", description = "Nenhuma solicitação encontrada")
    })
    ResponseEntity<List<SolicitacaoResponseDto>> listarPoritulo(
            @RequestParam(required = false) String titulo
    );

    @GetMapping
    @Operation(
            summary = "Listar solicitações",
            description = """
            Lista todas as solicitações cadastradas, com opção de filtrar por status via parâmetro.
            ---
            Exemplo: /solicitacoes?status=PENDENTE
            """)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Quando existem solicitações com o status informado (ou todas, se o parâmetro não for informado)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Quando não existe nenhuma solicitação com o status informado"
            )
    })
    ResponseEntity<List<SolicitacaoResponseDto>> listar(@RequestParam(required = false) String status);

    @PutMapping("/aceitar/{id}")
    @Operation(summary = "Aceitar solicitação", description = """
            Aceitar Solicitação de cadastro de nova memória
            ---
            Aceitar Solicitação de cadastro de novo solicitação no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando a solicitação foi aceita com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoResponseDto.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content())
    })
    ResponseEntity<Void> aceitarSolicitacao(@PathVariable Integer id);

    @DeleteMapping("/recusar/{id}")
    @Operation(summary = "Recusar solicitação", description = """
            Recusar solicitação de nova memória
            ---
            Recusar solicitação de nova memória no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando a solicitação é recusada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado com o ID fornecido",
                    content = @Content())
    })
    ResponseEntity<Void> recusarSolicitacao(@PathVariable Integer id);

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados da solicitação", description = """
            Atualizar dados da solicitação
            ---
            Atualizar dados da solicitação no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando a solicitação é atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoResponseDto.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "Quando não foi encontrado nenhum solicitação com este id",
                    content = @Content())
    })
    ResponseEntity<SolicitacaoResponseDto> atualizar(@PathVariable Integer id,
                                                 @Valid @RequestBody SolicitacaoRequestDto dto);
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar solicitação", description = """
            Deletar solicitação
            ---
            Deletar solicitação no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando a solicitação é deletado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "Quando não foi encontrado nenhum solicitação com este id",
                    content = @Content())
    })
    ResponseEntity<String> deletar(@PathVariable Integer id);
}
