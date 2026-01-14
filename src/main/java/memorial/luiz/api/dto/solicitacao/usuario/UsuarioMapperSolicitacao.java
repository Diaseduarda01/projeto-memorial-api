package memorial.luiz.api.dto.solicitacao.usuario;

import lombok.AllArgsConstructor;
import memorial.luiz.api.dto.role.RoleMapper;
import memorial.luiz.api.dto.usuario.UsuarioResponseDto;
import memorial.luiz.api.entity.Usuario;
import memorial.luiz.api.service.UsuarioService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioMapperSolicitacao {

    private final RoleMapper roleMapper;
    private final UsuarioService service;

    public Usuario toEntity(UsuarioRequestDtoSolicitacao dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setRole(roleMapper.toEntity(dto.role()));
        usuario.setRelacao(dto.relacao());

        return service.cadastrar(usuario);
    }

    public UsuarioResponseDto toResponse(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRelacao(),
                roleMapper.toResponse(usuario.getRole())
        );
    }
}
