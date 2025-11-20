package memorial.luiz.api.controller.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import memorial.luiz.api.dto.auth.AuthRequestDto;
import memorial.luiz.api.dto.usuario.UsuarioRequestDto;
import memorial.luiz.api.dto.usuario.UsuarioResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Operações relacionadas com o gerenciamento de usuários")
interface UsuarioControllerDoc {

    @PostMapping
    @Operation(summary = "Salvar usuário", description = """
            Salvar usuário
            ---
            Salva usuário no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quando o usuário é cadastrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content())
    })
    ResponseEntity<UsuarioResponseDto> cadastrar(@Valid @RequestBody UsuarioRequestDto dto);

    @PostMapping("/login")
    @Operation(summary = "Autentificação de usuário", description = """
            Autentificação de usuário
            ---
            Autentificação usuário no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando o usuário é Autentificado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "Quando não foi encontrado nenhum usuário com este email e senha",
                    content = @Content())
    })
    ResponseEntity<UsuarioResponseDto> login(@RequestBody AuthRequestDto dto);

    @GetMapping
    @Operation(summary = "Listar usuários", description = """
            Listar usuário
            ---
            Listar usuário salvos no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quando existe usuário no banco de dados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class)
                    )),
            @ApiResponse(responseCode = "204", description = "Quando não existe usuário no banco de dados",
                    content = @Content())
    })
    ResponseEntity<List<UsuarioResponseDto>> listar();

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por id", description = """
            buscar usuário por id
            ---
            buscar usuário por id no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando o usuário é encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDto.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "Quando não foi encontrado nenhum usuário com este id",
                    content = @Content())
    })
    ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Integer id);

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de usuário", description = """
            Atualizar dados do usuário
            ---
            Atualizar dados do usuário no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando o usuário é atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthRequestDto.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "Quando não foi encontrado nenhum usuário com este id",
                    content = @Content())
    })
    ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable Integer id,
                                                        @Valid @RequestBody UsuarioRequestDto dto);
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = """
            Deletar usuário
            ---
            Deletar usuário no banco de dados
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quando o usuário é deletado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Quando o corpo de requisição está incorreto",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "Quando não foi encontrado nenhum usuário com este id",
                    content = @Content())
    })
    ResponseEntity<String> deletar(@PathVariable Integer id);

}
