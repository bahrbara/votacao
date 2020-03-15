package beans;

import entidades.Presidente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import negocio.PresidenteService;

@ManagedBean
@SessionScoped
public class presidenteBean {

    PresidenteService service = new PresidenteService();
    Presidente presidente = new Presidente();

    private Integer cpf;
    private String nome;
    private Integer votos;

    public void cadastrarPresidente() {
        Presidente presidenteNew = new Presidente();
        presidenteNew.setCpf(cpf);
        presidenteNew.setNome(nome);
        service.cadastrarPresidente(presidenteNew);
    }

    public List<Presidente> getListPresidentes() {
        List<Presidente> listaPresidentes = service.buscarTodosPresidentes();
        return listaPresidentes;
    }

    public List<Presidente> getListVotacaoPresidentes() {
        List<Presidente> listaPresidentes = service.resultadoOrdem();
        return listaPresidentes;
    }

    public String votar() {
        presidente = this.getSelectedPresidente();
        presidente.setVotos();
        service.atualizarPresidente(presidente);

        return "/seguranca/resultados?faces-redirect=true";
    }

    public Integer getTotalVotos() {
        Integer totalVotos = service.getVotos(this.getSelectedPresidente());
        return totalVotos;
    }

    public Presidente getSelectedPresidente() {
        if (nome != null) {
            String[] objects = nome.split(",");
            String[] cpfNew = objects[1].split("=");
            Presidente presidenteNew = service.buscarPresidentePorcpf(Integer.parseInt(cpfNew[1]));
            return presidenteNew;
        }
        return null;
    }

    public void setService(PresidenteService service) {
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
