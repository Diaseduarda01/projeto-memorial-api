package memorial.luiz.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.luiz.api.entity.Memoria;
import memorial.luiz.api.entity.Usuario;
import memorial.luiz.api.exception.naoencontrado.MemoriaNaoEncontradoException;
import memorial.luiz.api.repository.MemoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MemoriaService {

    private final MemoriaRepository repository;
    private final UsuarioService usuarioService;

    public Memoria cadastrar(Memoria memoria) {
        Usuario usuario = usuarioService.buscarPorId(memoria.getUsuario().getId());

        memoria.setUsuario(usuario);

        Memoria salva = repository.save(memoria);
        log.info("Memória {} cadastrada com sucesso!", salva.getId());
        return salva;
    }

    public List<Memoria> listarTodas() {
        List<Memoria> solicitacoes = repository.findAll();
        log.info("Total de solicitações encontradas: {}", solicitacoes.size());
        return solicitacoes;
    }

    public Memoria buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(MemoriaNaoEncontradoException::new);
    }

    public Memoria atualizar(Integer id, Memoria destino) {
        Memoria origem = buscarPorId(id);

        this.atualizarDadosBasicos(origem, destino);
        this.atualizarUsuario(origem, destino);

        Memoria salvo = repository.save(origem);
        log.info("Memória {} atualizada com sucesso!", salvo.getId());
        return salvo;
    }

    private void atualizarDadosBasicos(Memoria origem, Memoria destino) {
        origem.setTitulo(destino.getTitulo());
        origem.setDescricao(destino.getDescricao());
    }

    private void atualizarUsuario(Memoria origem, Memoria destino) {
        if (destino.getUsuario() != null) {
            Usuario usuario = usuarioService.buscarPorId(destino.getUsuario().getId());
            origem.setUsuario(usuario);
        }
    }

    public void deletar(Integer id) {
        Memoria memoria = buscarPorId(id);
        repository.delete(memoria);
        log.info("Memória {} deletada com sucesso!", memoria.getId());
    }
}
