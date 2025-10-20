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

INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES
                                                                                    ('운영체제(OS)의 역할은 무엇인가요?',
                                                                                     '운영체제는 하드웨어와 사용자를 중개하는 소프트웨어로, 프로세스 관리, 메모리 관리, 파일 시스템, 입출력 장치 제어 등의 기능을 수행합니다. 즉, 시스템 자원을 효율적으로 관리하고 프로그램 실행 환경을 제공합니다.',
                                                                                     1,7,NOW(),NOW()),

                                                                                    ('프로세스(Process)와 스레드(Thread)의 차이는 무엇인가요?',
                                                                                     '프로세스는 실행 중인 프로그램의 인스턴스로, 독립된 메모리 공간을 가집니다. 반면 스레드는 프로세스 내에서 실행되는 작업 단위로, 같은 프로세스의 자원을 공유합니다. 스레드는 경량화되어 문맥 전환 비용이 낮습니다.',
                                                                                     2,7,NOW(),NOW()),

                                                                                    ('멀티프로세싱(Multiprocessing)과 멀티스레딩(Multithreading)의 차이는?',
                                                                                     '멀티프로세싱은 여러 개의 프로세스를 병렬로 실행하여 CPU 활용도를 높이는 방식이며, 프로세스 간 통신(IPC)에 오버헤드가 큽니다. 반면 멀티스레딩은 하나의 프로세스 내에서 여러 스레드를 동시에 실행하여 자원을 공유하고 문맥 전환 비용이 적습니다.',
                                                                                     3,7,NOW(),NOW()),

                                                                                    ('CPU 스케줄링(CPU Scheduling)이란 무엇인가요?',
                                                                                     'CPU 스케줄링은 여러 프로세스가 CPU를 사용하려 할 때, 어떤 프로세스에 CPU를 할당할지를 결정하는 운영체제의 기능입니다. 대표적인 스케줄링 알고리즘에는 FCFS, SJF, Priority, Round Robin 등이 있습니다.',
                                                                                     4,7,NOW(),NOW()),

                                                                                    ('동기(Synchronous)와 비동기(Asynchronous)의 차이를 설명해주세요.',
                                                                                     '동기는 작업이 순차적으로 실행되며, 하나의 작업이 끝나야 다음 작업이 시작됩니다. 비동기는 작업이 독립적으로 실행되어, 이전 작업의 완료를 기다리지 않고 다음 작업을 수행할 수 있습니다.',
                                                                                     5,7,NOW(),NOW()),

                                                                                    ('교착상태(Deadlock)의 발생 조건은 무엇인가요?',
                                                                                     '교착상태는 프로세스들이 자원을 서로 점유한 채로 무한히 기다리는 상태입니다. 발생 조건은 상호 배제, 점유와 대기, 비선점, 순환 대기입니다. 이 네 조건 중 하나라도 깨지면 교착상태를 방지할 수 있습니다.',
                                                                                     6,7,NOW(),NOW()),

                                                                                    ('세마포어(Semaphore)와 뮤텍스(Mutex)의 차이를 설명해주세요.',
                                                                                     '세마포어는 정수값을 사용해 여러 스레드가 공유 자원에 접근할 수 있도록 제어하는 동기화 도구입니다. 반면 뮤텍스는 오직 하나의 스레드만 자원에 접근할 수 있도록 잠금(lock)과 해제(unlock)을 제공합니다.',
                                                                                     7,7,NOW(),NOW()),

                                                                                    ('페이징(Paging) 기법이란 무엇인가요?',
                                                                                     '페이징은 물리 메모리를 고정된 크기의 블록(프레임)으로 나누고, 프로세스의 가상 메모리를 같은 크기의 블록(페이지)으로 나누어 매핑하는 메모리 관리 기법입니다. 외부 단편화를 줄이고 메모리 효율을 높입니다.',
                                                                                     8,7,NOW(),NOW()),

                                                                                    ('가상 메모리(Virtual Memory)의 개념을 설명해주세요.',
                                                                                     '가상 메모리는 실제 물리 메모리보다 큰 주소 공간을 제공하기 위해 디스크의 일부를 메모리처럼 사용하는 기법입니다. 프로세스는 자신만의 독립적인 주소 공간을 가지며, 필요할 때만 페이지 단위로 실제 메모리에 적재됩니다.',
                                                                                     9,7,NOW(),NOW()),

                                                                                    ('인터럽트(Interrupt)란 무엇인가요?',
                                                                                     '인터럽트는 CPU가 작업을 수행하는 중에 외부 또는 내부에서 발생한 이벤트를 즉시 처리하기 위해 현재 작업을 중단하고 해당 루틴으로 제어를 넘기는 메커니즘입니다. 예를 들어 키보드 입력, 입출력 완료, 시스템 오류 등이 있습니다.',
                                                                                     10,7,NOW(),NOW());

INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES
                                                                                    ('OSI 7계층이란 무엇인가요?',
                                                                                     'OSI 7계층은 네트워크 통신 과정을 7단계(물리, 데이터링크, 네트워크, 전송, 세션, 표현, 응용)로 나눈 모델입니다. 계층별로 역할을 구분해 설계 및 디버깅을 용이하게 하며, 각 계층은 하위 계층의 서비스를 이용하고 상위 계층에 서비스를 제공합니다.',
                                                                                     1,3,NOW(),NOW()),

                                                                                    ('TCP와 UDP의 차이는 무엇인가요?',
                                                                                     'TCP는 연결 지향적 프로토콜로, 신뢰성 있는 데이터 전송을 보장합니다. 반면 UDP는 비연결형 프로토콜로, 신뢰성보다 속도를 중시하며 실시간 스트리밍 등에 사용됩니다. TCP는 3-way handshake를 통해 연결을 설정하고, 순서 보장과 재전송 기능을 제공합니다.',
                                                                                     2,3,NOW(),NOW()),

                                                                                    ('3-way handshake 과정에 대해 설명해주세요.',
                                                                                     'TCP 연결을 설정할 때 클라이언트와 서버가 세 번의 패킷 교환으로 연결을 확립하는 절차입니다. 클라이언트가 SYN, 서버가 SYN-ACK, 다시 클라이언트가 ACK를 보내며 연결이 성립합니다. 이를 통해 양쪽 모두 데이터 송수신이 가능한 상태임을 확인합니다.',
                                                                                     3,3,NOW(),NOW()),

                                                                                    ('HTTP와 HTTPS의 차이를 설명해주세요.',
                                                                                     'HTTP는 암호화되지 않은 평문 통신을 사용하는 프로토콜이고, HTTPS는 SSL/TLS를 통해 암호화된 통신을 제공합니다. HTTPS는 데이터 무결성과 기밀성을 보장하며, 인증서를 통해 서버의 신뢰성을 검증합니다.',
                                                                                     4,3,NOW(),NOW()),

                                                                                    ('DNS(Domain Name System)의 역할은 무엇인가요?',
                                                                                     'DNS는 사람이 읽을 수 있는 도메인 이름(www.example.com)을 IP 주소로 변환해주는 시스템입니다. 계층적 구조를 가지며, 루트 → TLD → 권한 있는 네임서버 순으로 질의가 진행됩니다.',
                                                                                     5,3,NOW(),NOW()),

                                                                                    ('IP 주소의 종류와 IPv4, IPv6의 차이는 무엇인가요?',
                                                                                     'IP 주소는 네트워크 상의 장치를 구분하는 식별자입니다. IPv4는 32비트 주소 체계로 약 43억 개의 주소를 지원하고, IPv6는 128비트 체계로 사실상 무한대의 주소를 제공합니다. IPv6는 보안(IPsec)과 자동 설정 기능이 내장되어 있습니다.',
                                                                                     6,3,NOW(),NOW()),

                                                                                    ('라우팅(Routing)이란 무엇인가요?',
                                                                                     '라우팅은 네트워크 상에서 목적지까지 최적의 경로를 찾아 패킷을 전달하는 과정입니다. 라우터는 라우팅 테이블을 참고하여 다음 홉(next hop)을 결정합니다. 정적 라우팅과 동적 라우팅(OSPF, BGP 등)이 있습니다.',
                                                                                     7,3,NOW(),NOW()),

                                                                                    ('ARP(Address Resolution Protocol)의 역할은 무엇인가요?',
                                                                                     'ARP는 IP 주소를 물리적 MAC 주소로 변환하는 프로토콜입니다. 같은 네트워크 내에서 통신할 때, IP만으로는 실제 목적지를 찾을 수 없기 때문에 ARP 요청을 보내고 응답을 받아 MAC 주소를 확인합니다.',
                                                                                     8,3,NOW(),NOW()),

                                                                                    ('HTTP 상태 코드(Status Code)의 종류를 설명해주세요.',
                                                                                     'HTTP 상태 코드는 클라이언트 요청에 대한 서버의 응답 상태를 나타냅니다. 200번대는 성공(200 OK), 300번대는 리다이렉션(302 Found), 400번대는 클라이언트 오류(404 Not Found), 500번대는 서버 오류(500 Internal Server Error)를 의미합니다.',
                                                                                     9,3,NOW(),NOW()),

                                                                                    ('쿠키(Cookie)와 세션(Session)의 차이를 설명해주세요.',
                                                                                     '쿠키는 클라이언트(브라우저)에 저장되는 데이터로, 서버가 사용자의 상태를 식별하기 위해 사용합니다. 세션은 서버 측에서 관리되며, 쿠키에 세션 ID만 저장됩니다. 쿠키는 클라이언트 의존적이고, 세션은 서버 의존적인 상태 관리 방식입니다.',
                                                                                     10,3,NOW(),NOW());

INSERT INTO questions (question, answer, day, skill_id, created_at, updated_at) VALUES
                                                                                    ('데이터베이스(DB)의 기본 개념은 무엇인가요?',
                                                                                     '데이터베이스는 데이터를 체계적으로 저장하고 관리하기 위한 시스템입니다. 중복을 최소화하고 일관성을 유지하며, 여러 사용자가 동시에 데이터를 안전하게 사용할 수 있도록 합니다.',
                                                                                     1,2,NOW(),NOW()),

                                                                                    ('DBMS(Database Management System)의 역할은 무엇인가요?',
                                                                                     'DBMS는 데이터베이스를 생성, 관리, 제어하는 소프트웨어입니다. 데이터의 저장, 조회, 수정, 삭제 등의 작업을 수행하며, 트랜잭션 관리와 동시성 제어를 통해 데이터 무결성을 유지합니다.',
                                                                                     2,2,NOW(),NOW()),

                                                                                    ('스키마(Schema)란 무엇인가요?',
                                                                                     '스키마는 데이터베이스의 구조를 정의하는 청사진입니다. 테이블, 뷰, 인덱스, 제약조건 등의 정의가 포함되며, 데이터의 논리적 구성 방식을 나타냅니다.',
                                                                                     3,2,NOW(),NOW()),

                                                                                    ('정규화(Normalization)의 목적은 무엇인가요?',
                                                                                     '정규화는 데이터의 중복을 줄이고 이상현상(Anomaly)을 방지하기 위해 테이블을 분리하는 과정입니다. 제1정규형(1NF)부터 제3정규형(3NF), BCNF 등 단계별로 속성 간의 종속성을 줄입니다.',
                                                                                     4,2,NOW(),NOW()),

                                                                                    ('트랜잭션(Transaction)의 ACID 특성은 무엇인가요?',
                                                                                     '트랜잭션은 데이터베이스의 논리적 작업 단위로, ACID(원자성, 일관성, 고립성, 지속성) 특성을 가집니다. 이는 시스템 오류나 중단 상황에서도 데이터의 무결성을 보장합니다.',
                                                                                     5,2,NOW(),NOW()),

                                                                                    ('인덱스(Index)의 역할과 장단점을 설명해주세요.',
                                                                                     '인덱스는 테이블의 특정 컬럼에 대한 검색 속도를 향상시키는 자료구조입니다. 주로 B-Tree나 Hash 구조를 사용합니다. 읽기 속도는 빠르지만, 삽입·삭제 시 인덱스 갱신으로 인해 쓰기 성능이 저하될 수 있습니다.',
                                                                                     6,2,NOW(),NOW()),

                                                                                    ('JOIN의 종류에는 어떤 것들이 있나요?',
                                                                                     '대표적인 JOIN에는 INNER JOIN, LEFT OUTER JOIN, RIGHT OUTER JOIN, FULL OUTER JOIN이 있습니다. INNER JOIN은 두 테이블의 교집합을, LEFT/RIGHT JOIN은 한쪽 테이블의 모든 행과 일치하는 데이터를 반환합니다.',
                                                                                     7,2,NOW(),NOW()),

                                                                                    ('PRIMARY KEY와 FOREIGN KEY의 차이는 무엇인가요?',
                                                                                     'PRIMARY KEY는 각 행을 고유하게 식별하기 위한 제약조건으로, NULL 값을 허용하지 않습니다. FOREIGN KEY는 다른 테이블의 PRIMARY KEY를 참조하여 데이터의 참조 무결성을 보장합니다.',
                                                                                     8,2,NOW(),NOW()),

                                                                                    ('락(Lock)과 교착상태(Deadlock)는 무엇인가요?',
                                                                                     '락은 동시에 여러 트랜잭션이 같은 데이터를 수정하지 못하도록 보호하는 메커니즘입니다. 교착상태는 서로가 가진 락을 기다리며 무한 대기하는 상태로, 이를 방지하기 위해 타임아웃 설정이나 자원 획득 순서를 지정합니다.',
                                                                                     9,2,NOW(),NOW()),

                                                                                    ('NoSQL 데이터베이스와 RDBMS의 차이를 설명해주세요.',
                                                                                     'RDBMS는 테이블 기반의 정형 데이터를 관계 모델로 저장하며 SQL을 사용합니다. 반면 NoSQL은 비정형 데이터 저장에 유리하며, 키-값, 문서, 그래프 등 다양한 구조를 지원합니다. 수평적 확장성과 빠른 처리에 강점이 있습니다.',
                                                                                     10,2,NOW(),NOW());