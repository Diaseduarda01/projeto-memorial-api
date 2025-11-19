package memorial.luiz.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.luiz.api.entity.Status;
import memorial.luiz.api.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class StatusService {

    private final StatusRepository repository;

    public Status cadastrar(Status status) {
        Status statusSalvo = repository.save(status);
        log.info("Status: {} salvo com sucesso!", statusSalvo.getNome());
        return statusSalvo;
    }

    public Status buscarPorNome(String nome) {
        return repository.findByNomeIgnoreCase(nome);
    }
}
