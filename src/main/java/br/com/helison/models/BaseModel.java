package br.com.helison.models;
import java.io.Serializable;

public interface BaseModel<PK extends Serializable> extends Serializable{
    PK getPk();
}