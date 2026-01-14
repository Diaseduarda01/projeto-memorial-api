package memorial.luiz.api.dto.midia;

import memorial.luiz.api.entity.TipoMidia;

public record MidiaResponseDto(
        Integer id,
        String nome,
        String url,
        TipoMidia tipo
) {
}
