package memorial.luiz.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String relacao;
    private String senha;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Usuario(String nome, String email, String relacao, String senha) {
        this.nome = nome;
        this.email = email;
        this.relacao = relacao;
        this.senha = senha;
    }
}
