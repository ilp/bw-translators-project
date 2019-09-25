package com.bureauworks.translators.controller;

import com.bureauworks.translators.model.Translator;
import com.bureauworks.translators.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Iverson Pereira
 */
@RestController
public class TranslatorController {

    @Autowired
    private TranslatorRepository translatorRepository;

    @GetMapping("/translators")
    public Page<Translator> getTranslators(Pageable pageable) {
        return translatorRepository.findAll(pageable);
    }
}
