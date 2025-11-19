package memorial.luiz.api.exception.naoencontrado;

import memorial.luiz.api.exception.naoencontrado.base.NaoEncontradoException;

public class UsuarioNaoEncontradoException extends NaoEncontradoException {

    private static final String MENSAGEM = "Usuário não encontrado";

    public UsuarioNaoEncontradoException() {
        super(MENSAGEM);
    }
}


