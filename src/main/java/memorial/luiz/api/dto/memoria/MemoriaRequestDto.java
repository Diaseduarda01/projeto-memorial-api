package memorial.luiz.api.dto.memoria;

import memorial.luiz.api.dto.usuario.UsuarioRequestDto;

public record MemoriaRequestDto(
        String titulo,
        String descricao,
        UsuarioRequestDto usuario
) {
}
