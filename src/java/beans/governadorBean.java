package beans;

import entidades.Governador;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import negocio.GovernadorService;

@ManagedBean
@SessionScoped
public class governadorBean {

    GovernadorService service = new GovernadorService();
    Governador governador = new Governador();
    private Integer cpf;
    private String nome;
    private Integer votos;

    public void cadastrarGovernador() {
        Governador governadorNew = new Governador();
        governadorNew.setCpf(cpf);
        governadorNew.setNome(nome);
        service.cadastrarGovernador(governadorNew);
    }

    public List<Governador> getListGovernadores() {
        List<Governador> listaGovernadores = service.buscarTodosGovernadores();
        return listaGovernadores;
    }

    public List<Governador> getListVotacaoGovernadores() {
        List<Governador> listaGovernadores = service.resultadoOrdem();
        return listaGovernadores;
    }
    

    public String votar() {
        governador = this.getSelectedGovernador();
        governador.setVotos();
        service.atualizarGovernador(governador);

        return "/seguranca/votacaoPresidente?faces-redirect=true";
    }

    public Integer getTotalVotos() {
        Integer totalVotos = service.getVotos(this.getSelectedGovernador());
        return totalVotos;
    }

    public Governador getSelectedGovernador() {
        if (nome != null) {
            String[] objects = nome.split(",");
            String[] cpfNew = objects[1].split("=");
            Governador governadorNew = service.buscarGovernadorPorcpf(Integer.parseInt(cpfNew[1]));
            return governadorNew;
        }
        return null;
    }

    public void setService(GovernadorService service) {
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
