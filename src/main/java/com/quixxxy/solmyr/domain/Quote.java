package com.quixxxy.solmyr.domain;

import com.quixxxy.solmyr.util.QuoteUtils;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QUOTE")
public class Quote implements Serializable, Cloneable {

    private static final long serialVersionUID = -6439645808727720233L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "text")
    private String text;

    @DateTimeFormat
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "rating")
    private Long rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "quote_hash")
    private String quoteHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "id:" + id + " text:" + text + " date: " + creationDate;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuoteHash() {
        return quoteHash;
    }

    public void setQuoteHash(String quoteHash) {
        this.quoteHash = quoteHash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Quote) {
            Quote quote = (Quote) obj;
            return StringUtils.equals(quote.text, text);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    public String getHtmlEscapedText() {
        return QuoteUtils.escapeHtml(text);
    }

}
