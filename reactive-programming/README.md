Reactive Programming이란..

외부의 이벤트가 발생하면 이벤트에 대응하는 방식으로 동작하는 코드를 작성하는 것..

리액티브의 특성(2가지만..)

Duality

Erik Meijer는 Reactive에 대한 idea를 얘기할 때 항상 Duality라는 말을 꺼낸다고 한다. iterable ←→ observable 더라..

Duality: 두가지가 궁금적인 기능은 똑같은데 해당 기능을 반대방향으로 표현한 것

- iterable ←→ observable (gof 옵저버 패턴)
- iterable은 소스에서 pulling 방식 -`next()`
- observable은 소스에서 밀어 넣어주는 방식
- DATA method(void) <-> void method(DATA)

Observer Pattern

Reactive Streams

JVM 언어를 만드는 회사(넷플릭스,피봇탈 등)들이 핵심 인터페이스 등 표준 스펙을 정했다.

Java 9에는 Reactive Streams을 표준 스펙으로 한 개발된 API가 JDK(Flow API) 안에 들어감..

GOF의 옵저버 패턴과 같다…이름들이 달라서 헷갈린다고 한다..

옵저버 패턴 장점
Observable (소스 이벤트.. 데이터..)에 등록
관심있어 하는 모든 옵저버한테 broadcast 할수 있다.
별도의 쓰레드에서 비동기로 동작한 후 이벤트가 발생하면 결과정보를 가져올수있다.

Observer는 여러개가 될수있다.,.


나프다 김훈민 - MS는 RX를 왜 만들었을까?
https://www.youtube.com/watch?v=3FKlYO4okts
=> GoF 옵저버 패턴의 두가지를 지적했다.
1. Complete: 내가 지금 데이터를 다 줬다는 Complete 개념이 없다.
2. Error: 복구가능한 예외 전파 방식 및 처리 방식에 대한 아이디어가 없다..(물론 각자 알아서 구현은 할수는 있지만..)
두가지 추가해서 확장한 옵저버 패턴을 만들었고 리액티브프로그래밍의 한축이 되었다.


https://www.reactive-streams.org/
리액티브 프로그래밍의 느슨한 표준을 가져가는 큰 두그룹.. ReactiveX(rxjava 등..),  
표준
리액티브 메니페스트도 만듦었다
스프링이 구현하고 있는 리액티브 웹이 리액티브스트림에 바탕을 두고 있다.
표준을 지키는 것끼리는 상호호환성을 가질수있다.
덕분에 엔진은 리액터이고 퍼블리셔는 rxjava로 만들어도 특별한 어댑터를 가지지 않아도 사용할 수 있다.
굉장히 추상화된 레벨이고 구현은 가지고 많이 가지고 있지 않다.
표준안에는 API, Text Spec, TCK(kit), 구현예제 등을 포함

https://projectreactor.io/docs/core/release/reference/

scheduler