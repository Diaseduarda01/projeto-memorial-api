package memorial.luiz.api.dto.auth;

public record AuthRequestDto(
        String email,
        String senha
) {
}
