package br.com.helison.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import br.com.helison.models.Aluno;

public class AlunoService extends FactoryEntityManager implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2565562069720056688L;

    public List<Aluno> getAll() {
        if (entityManager == null) {
            return new ArrayList<>();
        }
        return entityManager.createQuery("from Aluno").getResultList();
    }

    public Aluno save(Aluno objeto) throws PersistenceException {
        try {
            entityManager.getTransaction().begin();
            if (objeto.getPk() == null) {
                this.insert(objeto);
            } else {
                this.update(objeto);
            }   
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException();
        } finally {
            closeConnection();
        }

        return objeto;
    }

    private void insert(Aluno objeto) {
        entityManager.persist(objeto);
    }

    private void update(Aluno objeto) {
    }

    public Aluno getByPk(Long pk) {
        return entityManager.find(Aluno.class, pk);
    }

    public List<Aluno> getAllRevisions(Long pk) {
        List<Aluno> alunosAudit = new ArrayList<>();
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Number> revs = reader.getRevisions(Aluno.class, pk);
        if (!revs.isEmpty()) {

            for (Number rev : revs) {
                alunosAudit.add(reader.find(Aluno.class, pk, rev));
            }
        }
        
        return alunosAudit;

    }

}