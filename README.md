# 설계

- Multi Module
- Layered Architecture (UI/Domain/Data)
- UI Layer 는 UDF로 구성되어 있습니다. 

## 사용 기술
- Kotlin 1.9.0
- JDK 17 
- UI Libraries (Compose, ViewModel(AAC), Navigation, AppCompat, Coil(Skydove`s Landscapist)...) 
- Kotlin Libraries (Coroutine,Serialization)
- Di Library (Dagger & Hilt)
- Network Library (Retrofit2, OkHttp)
- Test Library (Compose Test, JUnit4, Mocck, Kotest)
  

### 소개
안드로이드 최대 국내 컨퍼런스 DroidKnights 2023 App 의 Resource 를 그대로 활용 하여
손 코딩을 통해 프로젝트를 해석할겸 클론을 진행한 프로젝트 입니다. 

#### 기억에 남는점 
1. gradle::build-logic 에서 task 를 관리하는 plugin 을 커스텀 제작을 통해 다양한 모듈의 보일러 플레이트 코드를 모두 제거하였음  (Version Catalog 활용)
2. Data Layer:: Retrofit2 + Kotlin Serialization 활용, Repository 패턴, 외부 레이어에 데이터가 노출 될때 mapper 를 통해 공통 모델로 변환
3. Data Layer:: Test 는 api 와 dataSource 의 테스트용 가짜 구현 클래스를 만들어 두고 Repository 에 파라미터로 넘겨서 레포지토리의 비즈니스 로직을 테스트함, kotest가 사용 되었는데 시나리오가 필요한경우 BehaviorSpec() 로 진행하였고, 단발성 테스트는 StringSpec() 로 확인하였음
4. Data Layer:: DI -> DataModule은 깨끗하게 @Binds 로 구성했지만, FakeModule은 di를 이용했던 테스트의 흔적이 보이는 듯 하다. @Provides 를 통해 다른 파라미터를 넘겼을 듯 하다.   
5. Domain Layer:: UseCase 클래스를 통해서 단일 책임의 원칙을 준수, ViewModel 이 Repository 조율로 인한 많은 비즈니스 로직을 가지지 않도록 UseCase가 방지해줌 
6. Domain Layer:: GetBookmarkedSessionListUseCase 에서 2개의 Coroutine Flow를 리턴하는 UseCase를 Combine 하기 위해 flow{ emit(getSessionListUseCase()) }으로 먼저 emit()을 시도하고 combine을 시키는 코드가 있음
7. Ui Layer:: Theme, Color, Font, ui Component 은 designsystem 모듈에서 관리한다. 특히나 테마 관리에 철저했다. 심지어, 이미지 리소스도 다크모드 유무에 따라 바뀔 수 있는 painterResource를 감싼 컴포저블도 따로 만들어놨다.
8. Ui Layer:: ui 모듈이 designsystem 과 별도로 만들어진 이유가 추측이 됨, 앱의 특정 기능 상태에 따라 변하는 UI 이기 때문에 따로 만들어진것으로 보임
9. Ui Layer:: navigation 모듈이 있지만 사용 되지는 않았다. 2021년도 github 처럼 fun detail(context:Context, model:Session) 으로 사용하려 했던것 같다.
10. Ui Layer:: testing 모듈에 MainDispatcherRule 이 있다. test 의 헬퍼,유틸, 테스트데이터 생성, 셋업 및 설정 등등의 역활을 가지는 모듈 같다. 
11. Ui Layer:: feature::main 모듈은 MainActivity 를 가지고 있어서 메인 진입점과 동시에 Single Activity 구조라서, main 모듈에서 네비게이션 흐름을 담당하게 된다. 
12. Ui Layer:: 전체 ViewModel 관리는 UseCase 를 통해 앱데이터를 받아서 UI 상태로 가공 한다. Ui상태 가공 중에 생기는 error 는 cache 에서 errorFlow를 사용해서 네트워크 문제를 ui에 알린다. 그외 필요한 effect를 만들어서 사용하기도 한다. 
13. Ui Layer:: UI 상태는 sealed interface 를 활용했다. Ui 상태의 init 값으로 Loading 상태를 가지고 있고 Success 가지면 그에 맞는 상태의 ui를 렌더링 하게 된다.  
14. Ui Layer:: feature::session 모듈의 SessionState는 순수 UI로직만 다루는 state을 따로 다룬다.      
15. model 모듈:: 모든 레이어에 공통으로 쓰는 Application Model 을 정의 한다.
16. app 모듈:: app 의 permission , app resource 등등.. 앱의 configuration 을 관리 한다.
