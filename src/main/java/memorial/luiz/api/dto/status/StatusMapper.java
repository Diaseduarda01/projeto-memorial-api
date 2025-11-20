package memorial.luiz.api.dto.status;

import memorial.luiz.api.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {

    public Status toEntity(StatusRequestDto dto) {
        if (dto == null) return null;

        return new Status(
                dto.nome()
        );
    }

    public StatusResponseDto toResponse(Status status) {
        if (status == null) return null;

        return new StatusResponseDto(
                status.getId(),
                status.getNome()
        );
    }
}
