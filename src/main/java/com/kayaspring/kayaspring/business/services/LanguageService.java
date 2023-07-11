package com.kayaspring.kayaspring.business.services;

import com.kayaspring.kayaspring.api.middlewares.logging.ILogger;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.data.repositories.ILanguageRepository;
import com.kayaspring.kayaspring.data.repositories.IUserRepository;
import com.kayaspring.kayaspring.entities.models.Language;
import com.kayaspring.kayaspring.entities.models.User.AppUser;
import org.springframework.stereotype.Service;


@Service
public class LanguageService implements IService<Language> {

    private final ILanguageRepository languageRepository;
    private final ILogger logger;
    private final IUserRepository userRepository;
    private final AuthenticationService authenticationService;

    public LanguageService(ILanguageRepository languageRepository, ILogger logger, IUserRepository userRepository, AuthenticationService authenticationService) {
        this.languageRepository = languageRepository;
        this.logger = logger;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @Override
    public GenericResultClass getAll() {
        try {
            var data = languageRepository.findAll();
            return GenericResultClass.Success(data, data.stream().count());
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }


    @Override
    public GenericResultClass upsert(Language model) {
        try {
            languageRepository.save(model);
            return GenericResultClass.Success(true, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }


    @Override
    public GenericResultClass delete(long id) {
        try {
            languageRepository.deleteById(id);
            return GenericResultClass.Success(true, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }


    public GenericResultClass chooseMainAndTargetLanguages(long mainLanguageId, long targetLanguageId) {
        try {
            ///TODO: in the future, in order to reduce the wrong language selections, we need to uncomment this code...
            // var isMainLanguageExists = languageRepository.findById(mainLanguageId).orElseThrow();
            // var isTargetLanguageExists = languageRepository.findById(targetLanguageId).orElseThrow();
            String currentUserName = authenticationService.getUserName();
            AppUser user = userRepository.findByUsername(currentUserName);
            user.mainLanguage = mainLanguageId;
            user.targetLanguage = targetLanguageId;
            userRepository.save(user);
            return GenericResultClass.Success(true, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }

    }


}
