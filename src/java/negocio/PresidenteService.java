package negocio;

import entidades.Presidente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PresidenteService {

    EntityManager em = Persistence.createEntityManagerFactory("VotacaoLPIIPU").createEntityManager();

    public List<Presidente> buscarTodosPresidentes() {
        //Limpa o cache para buscar todos os objetos do banco de dados novamente
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();

        //Busca todos os presidente do Banco de dados
        Query consulta = em.createQuery("Select p from Presidente p ORDER BY p.cpf");

        //Joga o resultado da consulta em uma lista de presidentes
        List<Presidente> presidente = consulta.getResultList();

        return presidente;
    }

    public void cadastrarPresidente(Presidente presidente) {
        System.out.println("Persistindo presidente: " + presidente.getCpf() + " | " + presidente.getNome());

        em.getTransaction().begin();
        em.persist(presidente);
        em.getTransaction().commit();

        System.out.println("Presidente persistido!");
    }

    public Presidente getPresidente(Integer cpf) {
        Presidente presidente = (Presidente) em.find(Presidente.class, cpf);
        if (presidente != null) {
            return presidente;
        } else {
            return null;
        }
    }

    public void atualizarPresidente(Presidente presidente) {
        System.out.println("Atualizando presidente de nome " + presidente.getNome() + "...");

        em.getTransaction().begin();
        em.merge(presidente);
        em.getTransaction().commit();

        System.out.println("Presidente atualizado");
    }

    public Presidente buscarPresidentePorcpf(Integer cpf) {
        Presidente presidente = (Presidente) em.find(Presidente.class, cpf);
        return presidente;
    }

    public Integer getVotos(Presidente presidente) {
        String nomePresidente = presidente.getNome();
        Integer cpfPresidente = presidente.getCpf();

        System.out.println("Atualizando votos do presidente de nome " + nomePresidente + "...");

        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Presidente p where p.cpf=:param").setParameter("param", cpfPresidente);
        Integer votos = consulta.getFirstResult();
        System.out.println(votos);

        return votos;
    }
    
    public List<Presidente> resultadoOrdem() {
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Presidente p ORDER BY p.votos DESC");
        List<Presidente> presidente = consulta.getResultList();
        return presidente;
    }

    public void removerPresidente(Presidente presidente) {
        System.out.println("Removendo presidente de matricula " + presidente.getCpf() + "...");

        em.getTransaction().begin();
        em.remove(presidente);
        em.getTransaction().commit();

        System.out.println("Presidente removido!");

    }
}
