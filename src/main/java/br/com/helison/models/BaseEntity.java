package br.com.helison.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements BaseModel<Long>{

    /**
     *
     */
    private static final long serialVersionUID = -2268083817123241307L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Override
    public Long getPk() {
        return pk;
    }
    
}