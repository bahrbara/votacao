package beans;

import entidades.Prefeito;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import negocio.PrefeitoService;

@ManagedBean
@SessionScoped
public class prefeitoBean {

    PrefeitoService service = new PrefeitoService();
    Prefeito prefeito = new Prefeito();
    private Integer cpf;
    private String nome;
    private Integer votos;

    public void cadastrarPrefeito() {
        Prefeito prefeitoNew = new Prefeito();
        prefeitoNew.setCpf(cpf);
        prefeitoNew.setNome(nome);
        service.cadastrarPrefeito(prefeitoNew);
    }

    public List<Prefeito> getListPrefeitos() {
        List<Prefeito> listaPrefeitos = service.buscarTodosPrefeitos();
        return listaPrefeitos;
    }

    public List<Prefeito> getListVotacaoPrefeitos() {
        List<Prefeito> listaPrefeitos = service.resultadoOrdem();
        return listaPrefeitos;
    }

    public String votar() {
        prefeito = this.getSelectedPrefeito();
        prefeito.setVotos();
        service.atualizarPrefeito(prefeito);

        return "/seguranca/votacaoGovernador?faces-redirect=true";
    }

    public Integer getTotalVotos() {
        Integer totalVotos = service.getVotos(this.getSelectedPrefeito());
        return totalVotos;
    }

    public Prefeito getSelectedPrefeito() {
        if (nome != null) {
            String[] objects = nome.split(",");
            String[] cpfNew = objects[1].split("=");
            Prefeito prefeitoNew = service.buscarPrefeitoPorcpf(Integer.parseInt(cpfNew[1]));
            return prefeitoNew;
        }
        return null;
    }

    public void setService(PrefeitoService service) {
        this.service = service;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Integer getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
