package com.ddd.domainevent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomainService {

    private final ApplicationEventPublisher eventPublisher;
    private final AggregateRepository repository;
    private final Aggregate3Repository repository3;

    public DomainService(ApplicationEventPublisher eventPublisher, AggregateRepository repository, Aggregate3Repository repository3) {
        this.eventPublisher = eventPublisher;
        this.repository = repository;
        this.repository3 = repository3;
    }

    @Transactional
    public void serviceDomainOperation(long id) {
        repository.findById(id)
                .ifPresent(entity -> {
                    entity.domainOperation();
                    repository.save(entity);
                    eventPublisher.publishEvent(new DomainEvent());
                });
    }

    @Transactional
    public void entitySavedWithoutRepositoryWhenEntityChanged(long id) {
        repository3.findById(id)
                .ifPresent(entity -> {
                    entity.changeName("test2");
                    entity.domainOperation();
                });
    }
}
