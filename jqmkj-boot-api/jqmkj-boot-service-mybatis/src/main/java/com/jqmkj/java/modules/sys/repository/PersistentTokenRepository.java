package com.jqmkj.java.modules.sys.repository;

import com.jqmkj.java.common.persistence.repository.BaseRepository;
import com.jqmkj.java.modules.sys.domain.PersistentToken;

import java.util.Optional;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 */
public interface PersistentTokenRepository extends BaseRepository<PersistentToken, String> {


    Optional<PersistentToken> findOneById(String id);

}
