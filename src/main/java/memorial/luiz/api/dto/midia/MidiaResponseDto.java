package memorial.luiz.api.dto.midia;

import memorial.luiz.api.entity.Midia;
import memorial.luiz.api.entity.TipoMidia;

public record MidiaResponseDto(
        Integer id,
        String nome,
        String url,
        TipoMidia tipo
) {
    public static MidiaResponseDto from(Midia midia) {
        return new MidiaResponseDto(
                midia.getId(),
                midia.getNome(),
                "http://localhost:8080/upload/" + midia.getCaminho(),
                midia.getTipo()
        );
    }
}
