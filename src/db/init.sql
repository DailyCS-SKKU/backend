-- ----------------------------
--  skills
-- ----------------------------
INSERT INTO skills (name) VALUES ('Spring');
INSERT INTO skills (name) VALUES ('데이터베이스');
INSERT INTO skills (name) VALUES ('네트워크');
INSERT INTO skills (name) VALUES ('React');
INSERT INTO skills (name) VALUES ('인공지능');
INSERT INTO skills (name) VALUES ('알고리즘');
INSERT INTO skills (name) VALUES ('OS');
INSERT INTO skills (name) VALUES ('JavaScript');
INSERT INTO skills (name) VALUES ('Python');
INSERT INTO skills (name) VALUES ('Java');

-- ----------------------------
--  jobs
-- ----------------------------



-- ----------------------------
--  questions - Spring
-- ----------------------------
INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES ('WAS(Web Application Server)와 WS(Web Server)의 차이를 설명해주세요.', 'WAS(Web Application Server)은 비즈니스 로직을 넣을 수 있고, Tomcat, PHP, ASP, .NET 등이 있습니다. 반면에 WS(Web Server)는 비즈니스 로직을 넣을 수 없고, Nginx, Apache 등이 있습니다.', 1, 1, NOW(), NOW());
INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES ('의존성 주입(DI, Dependency Injection에 대해 설명해주세요.', '의존성 주입은 필요한 객체를 직접 생성하는 것이 아닌 외부로부터 객체를 받아서 사용하는 것입니다. 이를 통해 객체 간의 결합도를 줄이고 코드의 재사용성을 높일 수 있습니다.', 2, 1, NOW(), NOW());
INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES ('관점지향 프로그래밍(AOP, Aspect Oriented Programming)은 무엇이고, 언제 사용할 수 있을까요?', 'AOP는 핵심비즈니스 로직에 있는 공통 관심사항을 분리하여 모듈화하는 프로그래밍 기법입니다. 여러 모듈에서 공통으로 사용하는 기능을 분리하여 관리할 수 있으며, 트랜잭션 처리, 로깅, 보안 등에서 사용할 수 있습니다.', 3, 1, NOW(), NOW());
INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES ('Spring의 싱글톤 패턴에 대해 설명해주세요.', '스프링에서 bean 생성 시 별다른 설정이 없으면 default로 싱글톤이 적용됩니다. 스프링은 컨테이너를 통해 직접 싱글톤 객체를 생성하고 관리하는데, 요청이 들어올 때마다 매번 객체를 생성하지 않고, 이미 만들어진 객체를 공유하기 때문에 효율적인 사용이 가능합니다.', 4, 1, NOW(), NOW());

INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES
                                                                                    ('Spring에서 Bean의 생명주기(Lifecycle)에 대해 설명해주세요.',
                                                                                     '스프링 빈은 생성 → 의존성 주입 → 초기화 → 사용 → 소멸 순으로 동작합니다. @PostConstruct, @PreDestroy 어노테이션을 통해 초기화나 종료 시점을 제어할 수 있습니다.',
                                                                                     5, 1, NOW(), NOW()),

                                                                                    ('DispatcherServlet의 역할은 무엇인가요?',
                                                                                     'DispatcherServlet은 모든 클라이언트 요청의 진입점으로, 요청을 적절한 컨트롤러로 전달하고 응답을 뷰로 반환하는 프론트 컨트롤러 역할을 합니다.',
                                                                                     6, 1, NOW(), NOW()),

                                                                                    ('Spring MVC 패턴의 흐름을 간단히 설명해주세요.',
                                                                                     '사용자가 요청을 보내면 DispatcherServlet이 이를 받아 Controller → Service → Repository로 전달되고, 결과를 ViewResolver를 통해 화면에 렌더링합니다.',
                                                                                     7, 1, NOW(), NOW()),

                                                                                    ('Spring Boot를 사용하는 이유는 무엇인가요?',
                                                                                     'Spring Boot는 설정을 자동화해 빠르게 프로젝트를 시작할 수 있게 해줍니다. 내장 서버와 자동 설정 기능 덕분에 별도의 복잡한 환경 설정이 필요 없습니다.',
                                                                                     8, 1, NOW(), NOW()),

                                                                                    ('AOP를 실제로 활용할 수 있는 사례를 들어주세요.',
                                                                                     '대표적으로 트랜잭션 관리, 로깅, 보안 검증 같은 공통 기능에서 사용됩니다. 핵심 로직과 분리함으로써 코드 중복을 줄이고 유지보수를 쉽게 합니다.',
                                                                                     9, 1, NOW(), NOW()),

                                                                                    ('Spring에서 @Transactional 어노테이션은 어떤 역할을 하나요?',
                                                                                     '@Transactional은 메서드나 클래스 단위에서 트랜잭션을 관리하도록 도와줍니다. 예외 발생 시 자동으로 롤백되어 데이터 일관성을 보장합니다.',
                                                                                     10, 1, NOW(), NOW()),

                                                                                    ('Spring Security는 어떤 역할을 하나요?',
                                                                                     'Spring Security는 인증(Authentication)과 인가(Authorization)을 처리하는 보안 프레임워크입니다. 로그인 검증이나 권한 제어를 손쉽게 구현할 수 있습니다.',
                                                                                     11, 1, NOW(), NOW()),

                                                                                    ('Spring의 IoC 컨테이너는 어떤 역할을 하나요?',
                                                                                     'IoC 컨테이너는 객체의 생성, 의존성 주입, 생명주기 관리 등을 담당합니다. 개발자는 객체 관리 대신 비즈니스 로직에 집중할 수 있습니다.',
                                                                                     12, 1, NOW(), NOW()),

                                                                                    ('@Component, @Service, @Repository, @Controller의 차이를 설명해주세요.',
                                                                                     '모두 스프링 Bean을 등록하는 어노테이션입니다. Controller는 요청 처리, Service는 비즈니스 로직, Repository는 데이터 접근 계층에 사용됩니다.',
                                                                                     13, 1, NOW(), NOW()),

                                                                                    ('Spring에서 CORS를 설정하는 방법을 설명해주세요.',
                                                                                     '@CrossOrigin 어노테이션을 사용하거나 WebMvcConfigurer의 addCorsMappings()을 오버라이드하여 특정 도메인에서의 요청을 허용할 수 있습니다.',
                                                                                     14, 1, NOW(), NOW());
-- ----------------------------
--  questions - Java
-- ----------------------------
INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES
                                                                                    ('Java의 주요 특징은 무엇인가요?',
                                                                                     'Java는 객체 지향 언어로, 플랫폼 독립성과 메모리 자동 관리가 큰 특징입니다. JVM 위에서 동작하기 때문에 운영체제에 상관없이 실행 가능하며, 가비지 컬렉터가 불필요한 메모리를 자동으로 회수해줍니다. 또한, 풍부한 표준 라이브러리와 강력한 예외 처리 기능을 제공합니다.',
                                                                                     1, 10, NOW(), NOW()),

                                                                                    ('객체지향 프로그래밍(OOP)의 4대 특징을 설명해주세요.',
                                                                                     '객체지향의 핵심은 추상화, 캡슐화, 상속, 다형성입니다. 추상화는 핵심만 표현해 복잡성을 줄이고, 캡슐화는 데이터 은닉으로 안정성을 높입니다. 상속은 코드 재사용성을 높이고, 다형성은 하나의 인터페이스로 다양한 구현을 가능하게 합니다.',
                                                                                     2, 10, NOW(), NOW()),

                                                                                    ('JVM, JRE, JDK의 차이를 설명해주세요.',
                                                                                     'JVM은 Java 프로그램을 실행하는 가상머신이며, JRE는 JVM과 라이브러리를 포함해 실행 환경을 제공합니다. JDK는 JRE에 개발 도구(javac, javadoc 등)가 추가된 패키지로, 개발자가 Java 코드를 작성하고 컴파일하는 데 사용됩니다.',
                                                                                     3, 10, NOW(), NOW()),

                                                                                    ('Java에서 메모리 영역은 어떻게 구성되어 있나요?',
                                                                                     'Java 메모리는 크게 Method Area, Heap, Stack, PC Register, Native Method Stack으로 구성됩니다. Heap은 객체가 저장되는 공간이고, Stack은 메서드 호출 시 지역 변수와 참조를 저장합니다. Method Area에는 클래스 정보, static 변수 등이 저장됩니다.',
                                                                                     4, 10, NOW(), NOW()),

                                                                                    ('가비지 컬렉션(Garbage Collection)의 원리를 설명해주세요.',
                                                                                     '가비지 컬렉터는 더 이상 참조되지 않는 객체를 자동으로 탐지하고 제거합니다. 대표적인 알고리즘은 Mark and Sweep 방식으로, 유효 객체를 표시(Mark)하고, 표시되지 않은 객체를 제거(Sweep)합니다. 이를 통해 메모리 누수를 방지하고 효율을 높입니다.',
                                                                                     5, 10, NOW(), NOW()),

                                                                                    ('String, StringBuilder, StringBuffer의 차이는 무엇인가요?',
                                                                                     'String은 불변(immutable) 객체로, 변경 시마다 새로운 객체가 생성됩니다. 반면 StringBuilder와 StringBuffer는 가변(mutable) 객체이며, 문자열 변경 시 성능상 유리합니다. StringBuffer는 동기화를 지원해 스레드 안전하고, StringBuilder는 동기화가 없어 더 빠릅니다.',
                                                                                     6, 10, NOW(), NOW()),

                                                                                    ('오버로딩(Overloading)과 오버라이딩(Overriding)의 차이를 설명해주세요.',
                                                                                     '오버로딩은 같은 이름의 메서드를 매개변수의 개수나 타입을 다르게 정의하는 것이고, 컴파일 시점에 결정됩니다. 오버라이딩은 상속받은 메서드를 재정의하는 것으로, 런타임 시점에 호출 메서드가 결정됩니다.',
                                                                                     7, 10, NOW(), NOW()),

                                                                                    ('Java에서 예외(Exception) 처리 방식에 대해 설명해주세요.',
                                                                                     'Java에서는 예외를 try-catch-finally 블록을 통해 처리합니다. Checked Exception은 컴파일 시점에 반드시 처리해야 하며, Unchecked Exception(RuntimeException)은 런타임에 발생합니다. 필요 시 throw 키워드로 직접 예외를 던질 수도 있습니다.',
                                                                                     8, 10, NOW(), NOW()),

                                                                                    ('Java의 컬렉션 프레임워크(Collection Framework)에 대해 설명해주세요.',
                                                                                     '컬렉션 프레임워크는 데이터를 효율적으로 저장, 탐색, 수정할 수 있는 표준화된 구조입니다. 주요 인터페이스로는 List, Set, Map이 있고, 각각 ArrayList, HashSet, HashMap 등의 구현체가 존재합니다. 제네릭을 통해 타입 안정성도 제공합니다.',
                                                                                     9, 10, NOW(), NOW()),

                                                                                    ('멀티스레딩(Multi-threading)이란 무엇이며, Java에서는 어떻게 구현하나요?',
                                                                                     '멀티스레딩은 하나의 프로세스 내에서 여러 스레드가 동시에 실행되는 구조입니다. Java에서는 Thread 클래스를 상속하거나 Runnable 인터페이스를 구현하여 스레드를 생성할 수 있습니다. 동기화(synchronized) 키워드를 이용해 공유 자원을 안전하게 접근할 수 있습니다.',
                                                                                     10, 10, NOW(), NOW());