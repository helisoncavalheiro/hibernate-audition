<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello World!</title>
  </head>
  <body>
    <s:form action="salvar">
        <s:textfield name="aluno.nome" label="Nome" />
        <s:textfield name="aluno.matricula" label="Matrícula" />
        <s:textfield name="aluno.curso" label="Curso" />
        <s:submit />
    </s:form>

    <s:if test="msgErro != ''">
    <p style="color: red;">
        <s:property value="msgErro"/>
    </p>
</s:if>
  </body>
</html>