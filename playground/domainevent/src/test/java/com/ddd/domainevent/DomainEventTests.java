package com.ddd.domainevent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class DomainEventTests {

    @Autowired
    AggregateRepository repository;

    @Autowired
    Aggregate2Repository repository2;

    @Autowired
    Aggregate3Repository repository3;

    @MockBean
    AggregateEventHandler eventHandler; // TransactionalEventListener

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    DomainService domainService;

    @DisplayName("@Transactional이 붙은 service 메소드에서는 이벤트 publish 됨")
    @Test
    void serviceEventTest() {
        // given
        Aggregate aggregate = Aggregate.create();
        repository.save(aggregate);

        // when
        domainService.serviceDomainOperation(aggregate.getId());

        // then
        verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
    }

    @DisplayName("@Transactional 없이는 이벤트가 발생하지 않음")
    @Test
    void aggregateEventTest() {
        // given
        Aggregate aggregate = Aggregate.create();
        repository.save(aggregate);

        // when
        aggregate.domainOperation(eventPublisher);

        // then
        verifyNoInteractions(eventHandler);
    }

    @DisplayName("repository에 의해 save되면 event가 publish 됨")
    @Test
    void domainEvents() {
        // given
        Aggregate2 aggregate = Aggregate2.create();

        // when
        aggregate.domainOperation();
        repository2.save(aggregate);

        // then
        verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
    }

    @DisplayName("두번 저장해도 @AfterDomainEventPublication로 인해 한번만 publish 됨")
    @Test
    void afterDomainEvents() {

        // given
        Aggregate2 aggregate = Aggregate2.create();

        // when
        aggregate.domainOperation();
        repository2.save(aggregate);
        repository2.save(aggregate);

        // then
        verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
    }

    @DisplayName("AbstractAggregateRoot를 상속받은 entity가 repository를 통해 저장되면 event가 publish 됨")
    @Test
    void abstractAggregateRootDomainEvents() {
        // given
        Aggregate3 aggregate = Aggregate3.create("test");

        // when
        aggregate.domainOperation();
        repository3.save(aggregate);

        // then
        verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
    }

    @DisplayName("AbstractAggregateRoot를 상속받은 entity를 repository를 통해 두번 저장해도 한번만 publish 됨")
    @Test
    void afterAbstractAggregateRootDomainEvents() {

        // given
        Aggregate3 aggregate = Aggregate3.create("test");

        // when
        aggregate.domainOperation();
        repository3.save(aggregate);
        repository3.save(aggregate);

        // then
        verify(eventHandler, times(1)).handleEvent(any(DomainEvent.class));
    }

    @DisplayName("@Transactional 메소드 안에서 entity가 변화하면 save가 되기 때문에 이벤트가 호출될 것 같지만 안됨, repository를 통해 save할 때만 발생한다.")
    @Test
    void aggregateRootDomainEventsWithoutRepository() {
        // given
        Aggregate3 aggregate = Aggregate3.create("test");
        repository3.save(aggregate);

        // when
        domainService.entitySavedWithoutRepositoryWhenEntityChanged(aggregate.getId());

        // then
        verifyNoInteractions(eventHandler);
    }
}
