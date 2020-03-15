package beans;

import entidades.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import negocio.UsuarioService;

@ManagedBean
@SessionScoped
public class usuarioBean {

    UsuarioService service = new UsuarioService();

    Usuario usuario = new Usuario();
    private String cpf;
    private String nome;
    private String senha;
    private String novoCpf;
    private boolean logado;

    public String cadastrarUsuario() throws Exception {
        usuario.setVotou("0");
        usuario.setCpf(novoCpf);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        service.cadastrarUsuario(usuario);
        return "index";
    }

    public void hasVoted() {
        usuario.setVotou("1");
        service.atualizarUsuario(usuario);
    }

    private void limparForm() {
        novoCpf = "";
        cpf = "";
        nome = "";
        senha = "";
    }

    public String login() {
        this.logado = false;
        usuario = service.validarLogin(cpf);
        
        if (usuario == null) {
            return this.voltar();
        }

        boolean validaSenha = usuario.getSenha().equals(senha);
        boolean naoVotou = usuario.getVotou().equals("0");
        
        if (validaSenha) {
            this.logado = true;
            if (naoVotou) {
                return "/seguranca/votacaoPrefeito?faces-redirect=true";
            } else {
                return "/seguranca/resultados?faces-redirect=true";
            }
        }

        return this.voltar();
    }
    
    public String getVotou() {
        return usuario.getVotou();
    }

    public String voltar() {
        this.limparForm();
        return "/index";
    }

    public String telaCadastro() {
        this.limparForm();
        return "/cadastro";
    }

    public String getCpf() {
        return cpf;
    }

    public String getNovoCpf() {
        return novoCpf;
    }

    public void setNovoCpf(String novoCpf) {
        this.novoCpf = novoCpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

}
