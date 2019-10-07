package br.com.helison.services;

import java.io.Serializable;

import javax.persistence.PersistenceException;

import br.com.helison.models.BaseModel;

public class GenericService <T extends BaseModel<PK>, PK extends Serializable> extends FactoryEntityManager implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected GenericService() {
        getEntityManager();
    }

    public T save(T objeto) throws PersistenceException{
        try{
            entityManager.getTransaction().begin();
            if(objeto.getPk() == null){
                this.insert(objeto);
            }else{
                this.update(objeto);
            }
            entityManager.getTransaction().commit();
        }catch(PersistenceException e){
            entityManager.getTransaction().rollback();
            throw new PersistenceException();
        }finally{
            closeConnection();
        }

        return objeto;
    }

    private void insert(T objeto) {
        entityManager.persist(objeto);
    }

    private void update(T objeto) {
    }

}