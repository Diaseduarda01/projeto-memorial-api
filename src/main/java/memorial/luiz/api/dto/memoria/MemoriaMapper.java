package memorial.luiz.api.dto.memoria;

import lombok.AllArgsConstructor;
import memorial.luiz.api.dto.usuario.UsuarioMapper;
import memorial.luiz.api.entity.Memoria;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MemoriaMapper {

    private final UsuarioMapper usuarioMapper;

    public Memoria toEntity(MemoriaRequestDto dto) {
        if (dto == null) return null;

        Memoria memoria = new Memoria(
                dto.titulo(),
                dto.descricao()
        );

        memoria.setUsuario(usuarioMapper.toEntity(dto.usuario()));

        return memoria;
    }

    public MemoriaResponseDto toResponse(Memoria memoria) {
        if (memoria == null) return null;

        return new MemoriaResponseDto(
                memoria.getId(),
                memoria.getTitulo(),
                memoria.getDescricao(),
                usuarioMapper.toResponse(memoria.getUsuario())
        );
    }
}
