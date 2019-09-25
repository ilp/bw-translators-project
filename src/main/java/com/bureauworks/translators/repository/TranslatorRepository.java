package com.bureauworks.translators.repository;

import com.bureauworks.translators.model.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Iverson Pereira
 */
@Repository
public interface TranslatorRepository extends JpaRepository<Translator, Long> {
}
