package com.eaz.feedbackrest.model;

import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javier
 */
@XmlRootElement
public class Feedback {
    
    protected BigInteger id;
    
    protected String name;
    
    protected Integer score;
    
    protected Date date;
    
    protected String comment;
    
    public Feedback() {
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}