package br.com.helison.controllers;

import javax.persistence.PersistenceException;

import com.opensymphony.xwork2.ActionSupport;

import br.com.helison.models.Aluno;
import br.com.helison.services.AlunoService;

public class AlunoController extends ActionSupport{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Aluno aluno;
    private AlunoService alunoService;
    private String msgErro;

    public String salvar(){
        alunoService = new AlunoService();
        try{
            alunoService.save(this.aluno);
            System.out.println(this.aluno);
            return SUCCESS;
        }catch(PersistenceException e){
            this.msgErro = e.getMessage();
            return ERROR;
        }finally{
            alunoService.closeConnection();
        }
        
    }

    public Aluno getAluno(){
        return aluno;
    }

    public String getMsgErro(){
        return msgErro;
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }

}