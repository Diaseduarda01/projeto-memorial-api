package memorial.luiz.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import memorial.luiz.api.entity.Role;
import memorial.luiz.api.entity.Usuario;
import memorial.luiz.api.exception.naoencontrado.UsuarioNaoEncontradoException;
import memorial.luiz.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioService {

    private UsuarioRepository repository;
    private RoleService roleService;

    public Usuario cadastrar(Usuario usuario) {
        Role role = roleService.buscarPorNome(usuario.getRole().getNome());
        usuario.setRole(role);
        Usuario usuarioSalvo = repository.save(usuario);
        log.info("Usuário: {} salvo com sucesso!", usuarioSalvo);
        return usuarioSalvo;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = repository.findAll();
        log.info("Total de usuários encontrados no banco: {} ", usuarios.size());
        return usuarios;
    }

    public Usuario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public Usuario atualizar(Integer id, Usuario destino) {
        Usuario origem = buscarPorId(id);

        this.atualizarDadosBasicos(origem, destino);
        this.atualizarRoleUsuario(origem, destino);

        Usuario salvo = repository.save(origem);
        log.info("Usuário {} atualizado com sucesso!", salvo.getNome());
        return salvo;
    }

    public void atualizarDadosBasicos(Usuario origem, Usuario destino) {
        origem.setNome(destino.getNome());
        origem.setEmail(destino.getEmail());
        origem.setTelefone(destino.getTelefone());
        origem.setSenha(destino.getSenha());
    }

    public void atualizarRoleUsuario(Usuario origem, Usuario destino) {
        if (destino.getRole() != null) {
            Role role = roleService.buscarPorNome(destino.getRole().getNome());
            origem.setRole(role);
        }
    }

    public void deletar(Integer id) {
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
        log.info("Usuário {} deletado com sucesso!", usuario.getNome());
    }
}
