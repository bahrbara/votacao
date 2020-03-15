package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class resultadosBean {

    public String resultadoPrefeito() {
        return "/seguranca/resultadosPrefeito?faces-redirect=true";
    }

    public String resultadoGovernador() {
        return "/seguranca/resultadosGovernador?faces-redirect=true";
    }

    public String resultadoPresidente() {
        return "/seguranca/resultadosPresidente?faces-redirect=true";
    }

    public String voltar() {
        return "/seguranca/resultados?faces-redirect=true";
    }

}
