package com.kayaspring.kayaspring.business.services;

import com.kayaspring.kayaspring.api.middlewares.logging.ILogger;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.data.repositories.ICategoryRepository;
import com.kayaspring.kayaspring.data.repositories.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "categoryCash")
public class CategoryService {


    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final ILogger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public CategoryService(ICategoryRepository categoryRepository, IUserRepository userRepository,
                           AuthenticationService authenticationService, ILogger logger) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.logger = logger;
    }

    public GenericResultClass getAll() {
        try {
            var data = categoryRepository.findAll();
            return GenericResultClass.Success(data, data.stream().count());
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }


}
