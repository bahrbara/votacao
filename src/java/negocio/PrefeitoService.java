package negocio;

import entidades.Governador;
import entidades.Prefeito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PrefeitoService {

    EntityManager em = Persistence.createEntityManagerFactory("VotacaoLPIIPU").createEntityManager();

    public List<Prefeito> buscarTodosPrefeitos() {
        //Limpa o cache para buscar todos os objetos do banco de dados novamente
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();

        //Busca todos os prefeito do Banco de dados
        Query consulta = em.createQuery("Select p from Prefeito p ORDER BY p.cpf");

        //Joga o resultado da consulta em uma lista de prefeitos
        List<Prefeito> prefeito = consulta.getResultList();

        return prefeito;
    }

    public void cadastrarPrefeito(Prefeito prefeito) {
        System.out.println("Persistindo prefeito: " + prefeito.getCpf() + " | " + prefeito.getNome());

        em.getTransaction().begin();
        em.persist(prefeito);
        em.getTransaction().commit();

        System.out.println("Prefeito persistido!");
    }

    public Prefeito getPrefeito(Integer cpf) {
        Prefeito prefeito = (Prefeito) em.find(Prefeito.class, cpf);
        if (prefeito != null) {
            return prefeito;
        } else {
            return null;
        }
    }

    public void atualizarPrefeito(Prefeito prefeito) {
        System.out.println("Atualizando prefeito de nome " + prefeito.getNome() + "...");

        em.getTransaction().begin();
        em.merge(prefeito);
        em.getTransaction().commit();

        System.out.println("Prefeito atualizado");
    }

    public Prefeito buscarPrefeitoPorcpf(Integer cpf) {
        Prefeito prefeito = (Prefeito) em.find(Prefeito.class, cpf);
        return prefeito;
    }

    public Integer getVotos(Prefeito prefeito) {
        String nomePrefeito = prefeito.getNome();
        Integer cpfPrefeito = prefeito.getCpf();

        System.out.println("Atualizando votos do prefeito de nome " + nomePrefeito + "...");

        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Prefeito p where p.cpf=:param").setParameter("param", cpfPrefeito);
        Integer votos = consulta.getFirstResult();
        System.out.println(votos);

        return votos;
    }

    public List<Prefeito> resultadoOrdem() {
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();
        Query consulta = em.createQuery("Select p from Prefeito p ORDER BY p.votos DESC");
        List<Prefeito> prefeito = consulta.getResultList();
        return prefeito;
    }
    
    public void removerPrefeito(Prefeito prefeito) {
        System.out.println("Removendo prefeito de matricula " + prefeito.getCpf() + "...");

        em.getTransaction().begin();
        em.remove(prefeito);
        em.getTransaction().commit();

        System.out.println("Prefeito removido!");

    }
}
