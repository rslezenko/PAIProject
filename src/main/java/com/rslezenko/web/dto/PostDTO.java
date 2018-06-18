package com.rslezenko.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PostDTO {

  /*  @NotEmpty
    @NotNull
    private Long id;*/

    @NotEmpty
    private String title;

    @NotEmpty
    private String body;
    /*
        @NotEmpty
        private Date date;

            @NotEmpty
            private String author;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }
        */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
/*
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    */
}
