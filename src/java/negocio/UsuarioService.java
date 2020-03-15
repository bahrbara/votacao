/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

public class UsuarioService {

    EntityManager em = Persistence.createEntityManagerFactory("VotacaoLPIIPU").createEntityManager();

    public List<Usuario> buscarTodosUsuarios() {
        //Limpa o cache para buscar todos os objetos do banco de dados novamente
        em.getEntityManagerFactory().getCache().evictAll();
        em.clear();

        //Busca todos os usuario do Banco de dados
        Query consulta = em.createQuery("Select u from Usuario u ORDER BY u.cpf");

        //Joga o resultado da consulta em uma lista de usuarios
        List<Usuario> usuario = consulta.getResultList();

        return usuario;
    }

    public void cadastrarUsuario(Usuario usuario) {
        System.out.println("Persistindo usuario: " + usuario.getCpf() + " | " + usuario.getNome() + " | " + usuario.getSenha());

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

            System.out.println("Usuario persistido!");
        } catch (ConstraintViolationException e) {
            System.out.println(e.getConstraintViolations());
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e);
        }
    }

    public Usuario validarLogin(String cpf) {
        Usuario usuario = (Usuario) em.find(Usuario.class, cpf);
        if (usuario != null) {
            return usuario;
        } else {
            return null;
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        System.out.println("Atualizando usuario de matricula " + usuario.getCpf() + "...");

        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();

        System.out.println("Usuario atualizado");
    }

    public Usuario buscarUsuarioPorcpf(String cpf) {
        Usuario usuario = (Usuario) em.find(Usuario.class, cpf);
        return usuario;
    }

    public void removerUsuario(Usuario usuario) {
        System.out.println("Removendo usuario de matricula " + usuario.getCpf() + "...");

        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();

        System.out.println("Usuario removido!");

    }
}
