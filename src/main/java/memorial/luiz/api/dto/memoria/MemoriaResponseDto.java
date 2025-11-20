package memorial.luiz.api.dto.memoria;

import memorial.luiz.api.dto.usuario.UsuarioResponseDto;

public record MemoriaResponseDto(
        Integer id,
        String titulo,
        String descricao,
        UsuarioResponseDto usuario
) {
}
