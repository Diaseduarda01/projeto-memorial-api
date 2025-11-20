package memorial.luiz.api.dto.usuario;

import lombok.AllArgsConstructor;
import memorial.luiz.api.dto.role.RoleMapper;
import memorial.luiz.api.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioMapper {

    private final RoleMapper roleMapper;

    public Usuario toEntity(UsuarioRequestDto dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario(
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.senha()
        );

        usuario.setRole(roleMapper.toEntity(dto.role()));

        return usuario;
    }

    public UsuarioResponseDto toResponse(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                roleMapper.toResponse(usuario.getRole())
        );
    }
}
