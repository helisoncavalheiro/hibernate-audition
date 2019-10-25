package br.com.helison.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.com.helison.models.Aluno;
import br.com.helison.services.AlunoService;

@ViewScoped
@Named("alunoController")
@URLBeanName("alunoController")
@URLMappings(mappings = { @URLMapping(id = "alunos", pattern = "/", viewId = "/pages/index.xhtml"),
        @URLMapping(id = "novo-aluno", pattern = "/novo", viewId = "/pages/formAluno.xhtml"),
        @URLMapping(id = "editar-aluno", pattern = "/editar", viewId = "/pages/formAluno.xhtml"),
        @URLMapping(id = "detalhe-aluno", pattern = "/aluno", viewId = "/pages/detalheAluno.xhtml") })

public class AlunoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Aluno aluno;
    private List<Aluno> alunoAudit;

    @Inject
    private AlunoService alunoService;

    private List<Aluno> alunos;

    public AlunoController() {
        aluno = new Aluno();
    }

    public void salvar() {
        try {
            alunoService.save(this.aluno);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Aluno " + this.aluno.getMatricula() + " cadastrado com sucesso"));
        } catch (PersistenceException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Aluno " + this.aluno.getMatricula() + " não foi cadastrado"));
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/");
        } catch (IOException e) {

        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    @URLAction(mappingId = "alunos", onPostback = false)
    public List<Aluno> todos() {
        if (this.alunos == null || this.alunos.isEmpty()) {
            this.alunos = alunoService.getAll();
        }
        return this.alunos;
    }

    @URLActions(actions = { @URLAction(mappingId = "editar-aluno", onPostback = false),
            @URLAction(mappingId = "detalhe-aluno", onPostback = false) })
    public void setAlunoByPk() {
        String alunoId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("alunoId");
        this.aluno = alunoService.getByPk(Long.parseLong(alunoId));
        this.alunoAudit = alunoService.getAllRevisions(Long.parseLong(alunoId));
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    public List<Aluno> getAlunoAudit(){
        return this.alunoAudit;
    }

}