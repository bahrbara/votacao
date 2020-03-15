package negocio;

import entidades.Governador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GovernadorService {

    EntityManager em = Persistence.createEntityManagerFactory("VotacaoLPIIPU").createEntityManager();

    public List<Governador> buscarTodosGovernadores() {
        //Limpa o cache para buscar todos os objetos do banco de dados novamente
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();

        //Busca todos os governador do Banco de dados
        Query consulta = em.createQuery("Select p from Governador p ORDER BY p.cpf");

        //Joga o resultado da consulta em uma lista de governadors
        List<Governador> governador = consulta.getResultList();

        return governador;
    }

    public void cadastrarGovernador(Governador governador) {
        System.out.println("Persistindo governador: " + governador.getCpf() + " | " + governador.getNome());

        em.getTransaction().begin();
        em.persist(governador);
        em.getTransaction().commit();

        System.out.println("Governador persistido!");
    }

    public Governador getGovernador(Integer cpf) {
        Governador governador = (Governador) em.find(Governador.class, cpf);
        if (governador != null) {
            return governador;
        } else {
            return null;
        }
    }

    public void atualizarGovernador(Governador governador) {
        System.out.println("Atualizando governador de nome " + governador.getNome() + "...");

        em.getTransaction().begin();
        em.merge(governador);
        em.getTransaction().commit();

        System.out.println("Governador atualizado");
    }

    public Governador buscarGovernadorPorcpf(Integer cpf) {
        Governador governador = (Governador) em.find(Governador.class, cpf);
        return governador;
    }

    public Integer getVotos(Governador governador) {
        String nomeGovernador = governador.getNome();
        Integer cpfGovernador = governador.getCpf();

        System.out.println("Atualizando votos do governador de nome " + nomeGovernador + "...");

        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Governador p where p.cpf=:param").setParameter("param", cpfGovernador);
        Integer votos = consulta.getFirstResult();
        System.out.println(votos);

        return votos;
    }
    
     public List<Governador> resultadoOrdem() {
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Governador p ORDER BY p.votos DESC");
        List<Governador> governador = consulta.getResultList();
        return governador;
    }

    public void removerGovernador(Governador governador) {
        System.out.println("Removendo governador de matricula " + governador.getCpf() + "...");

        em.getTransaction().begin();
        em.remove(governador);
        em.getTransaction().commit();

        System.out.println("Governador removido!");

    }
}
