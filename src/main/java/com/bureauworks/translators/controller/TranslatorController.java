package com.bureauworks.translators.controller;

import com.bureauworks.translators.exception.TranslatorNotFoundException;
import com.bureauworks.translators.model.Translator;
import com.bureauworks.translators.repository.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Iverson Pereira
 */
@RestController
public class TranslatorController {

    @Autowired
    private TranslatorRepository translatorRepository;

    /**
     * Was added pagination because the database can contain more one million of data.
     *
     * @param pageable Object contains the params of pagination.
     * @return a set of Translator based on params.
     */
    @GetMapping("/translators")
    public Page<Translator> getTranslators(Pageable pageable) {
        return translatorRepository.findAll(pageable);
    }

    @PostMapping("/translators")
    public Translator createTranslator(@Valid @RequestBody Translator translator) {
        return translatorRepository.save(translator);
    }

    @PutMapping("/translators/{translatorId}")
    public Translator createTranslator(@PathVariable Long translatorId,
                                       @Valid @RequestBody Translator translatorRequest) {
        return translatorRepository.findById(translatorId)
                .map(translator -> {
                    translator.setName(translatorRequest.getName());
                    translator.setEmail(translatorRequest.getEmail());
                    translator.setSourceLanguage(translatorRequest.getSourceLanguage());
                    translator.setTargetLanguage(translatorRequest.getTargetLanguage());
                    return translatorRepository.save(translator);
                }).orElseThrow(() -> new TranslatorNotFoundException("Translator not found with id " + translatorId));

    }

    @DeleteMapping("/translators/{translatorId}")
    public ResponseEntity<?> deleteTranslator(@PathVariable Long translatorId) {
        return translatorRepository.findById(translatorId)
                .map(translator -> {
                    translatorRepository.delete(translator);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new TranslatorNotFoundException("Translator not found with id " + translatorId));
    }
}
