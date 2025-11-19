package memorial.luiz.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.luiz.api.entity.Role;
import memorial.luiz.api.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoleService {

    private RoleRepository repository;

    public Role salvar(Role role) {
        Role roleSalvo = repository.save(role);
        log.info("Role: {} salvo com sucesso!", roleSalvo.getNome());
        return roleSalvo;
    }

    public Role buscarPorNome(String nome) {
        return repository.findByNomeIgnoreCase(nome);
    }
}
