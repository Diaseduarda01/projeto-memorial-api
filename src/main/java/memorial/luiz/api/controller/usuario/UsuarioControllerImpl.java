package memorial.luiz.api.controller.usuario;

import lombok.RequiredArgsConstructor;
import memorial.luiz.api.dto.auth.AuthRequestDto;
import memorial.luiz.api.dto.role.RoleMapper;
import memorial.luiz.api.dto.usuario.UsuarioMapper;
import memorial.luiz.api.dto.usuario.UsuarioRequestDto;
import memorial.luiz.api.dto.usuario.UsuarioResponseDto;
import memorial.luiz.api.entity.Usuario;
import memorial.luiz.api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioControllerDoc {

    private final UsuarioService service;
    private final UsuarioMapper mapper;
    private final RoleMapper roleMapper;

    @Override
    public ResponseEntity<UsuarioResponseDto> cadastrar(UsuarioRequestDto dto) {
        Usuario usuarioSalvar = mapper.toEntity(dto);
        Usuario usuarioSalvo = service.cadastrar(usuarioSalvar);
        return ResponseEntity.status(201).body(mapper.toResponse(usuarioSalvo));
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> login(AuthRequestDto dto) {
        Usuario usuario = service.buscarPorEmailAndSenha(dto.email(), dto.senha());
        return ResponseEntity.status(200).body(mapper.toResponse(usuario));
    }

    @Override
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        List<Usuario> usuarios = service.listar();
        return usuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(usuarios.stream()
                    .map(mapper::toResponse)
                    .toList());
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> buscarPorId(Integer id) {
       Usuario usuario = service.buscarPorId(id);
       return ResponseEntity.status(200).body(mapper.toResponse(usuario));
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> atualizar(Integer id, UsuarioRequestDto dto) {
        Usuario usuarioAtualizar = mapper.toEntity(dto);
        Usuario usuarioAtualizado = service.atualizar(id, usuarioAtualizar);
        return ResponseEntity.status(200).body(mapper.toResponse(usuarioAtualizado));
    }

    @Override
    public ResponseEntity<String> deletar(Integer id) {
       service.deletar(id);
       return ResponseEntity.status(200).body("Usuário deletado com sucesso!");
    }
}
