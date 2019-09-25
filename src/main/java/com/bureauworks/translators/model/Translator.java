package com.bureauworks.translators.model;

import javax.persistence.*;

/**
 * @author Iverson Pereira
 */
@Entity
@Table(name = "translators")
public class Translator extends AuditModel{
    @Id
    @GeneratedValue(generator = "translator_generator")
    @SequenceGenerator(
            name = "translator_generator",
            sequenceName = "translator_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "source_language")
    private String sourceLanguage;

    @Column(name = "target_language")
    private String targetLanguage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
}
