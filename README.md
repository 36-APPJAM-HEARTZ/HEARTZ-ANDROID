# 💔 ByeBoo

> 이별을 **성장의 디딤돌**로 만드는 정서 회복 경험을 제공합니다!<br/>
>이별 후의 감정을 맞춤형 퀘스트로 정리하고 극복하도록 돕는 감정 케어 서비스<br/>

## ⭐ ByeBoo 주요 기능
🏠 **홈** <br/>
ByeBoo의 메인 캐릭터인 '보리'를 만나볼 수 있으며<br/>
보리가 해주는 위로와 응원의 한마디를 제공 받을 수 있습니다.<br/>
또한, 퀘스트 및 전체 연정의 진행 상태를 한 눈에 확인할 수 있습니다.<br/>

🕹️ **퀘스트** <br/>
하나의 이별 극복 여정은 총 5단계로 구성되며, 한 여정은 총 30개의 퀘스트로 이루어집니다.<br/>
사용자는 단계별 퀘스트에 따라 감정과 상황을 직면하고 정리해 나가는 경험을 할 수 있습니다.<br/>
>**📝질문형**<br/>
>감정과 상황을 글로 마주하는 자기 성찰 중심의 퀘스트입니다.<br/>
>이별에 대해 천천히 돌아보며 감정의 실체를 파악하고, 스스로 이해하는 과정을 돕습니다.

>**🧗‍♂️행동형**<br/>
>몸을 움직이며 감정을 정리하는 실천 중심의 퀘스트입니다.<br/>
>간단한 행동을 통해 머릿속을 환기시키고, 무기력에서 벗어나 스스로 일상의 루틴을 되찾을 수 있도록 유도합니다.

<br/>


## **👀 Contributors**

|                              👑 이종훈 <br> [@fredleeJH](https://github.com/fredleeJH)                   |                           정소희 <br> [@sohee6989](https://github.com/sohee6989)                             |                  주아연 <br> [@znayeonzn](https://github.com/znayeonzn)                             |
|:--------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------:|
| <img width="250" src="https://avatars.githubusercontent.com/u/155813460?v=4"/> | <img width="250" src="https://avatars.githubusercontent.com/u/144779368?v=4"/> | <img width="250" src="https://avatars.githubusercontent.com/u/142514626?v=4"/> |
|                                              `정보 입력`<br>`홈`<br>`온보딩`                                                    |                                              `텍스트 기반 퀘스트`<br>`마이페이지`                                               |                           `행동 기반 퀘스트`<br>`오프보딩`<br>                                                      |

<br/>

## **⚒️ Tech Stacks**
| 항목              | 기술 스택 |
|------------------|---------|
| Architecture     | Clean Architecture |
| Pattern          | MVVM  |  
| DI               | Hilt  |
| Asynchronous     | Coroutine, Flow |
| Network          | Retrofit2, OkHttp |
| Navigation       | Single Activity Architecture (SAA), Jetpack Navigation |
| UI Framework     | Jetpack Compose          |
| Image Processing | Coil, Lottie             |
| Logging          | Timber                   |

<br/>

### **🔍 기술 선정 이유** ###

**1️⃣ Architecture: Clean Architecture** <br/>
기존 과제에서 폴더링을 제대로 사용해보지 못하여 클린 아키텍처 구조를 도입했습니다.<br/>
각 계층 간 의존성을 최소화하고 비즈니스 로직과 UI 계층을 철저하게 분리할 수 있습니다.

**2️⃣ Pattern: MVVM** <br/>
러닝커브가 조금 높은 MVI를 적용하기보다 MVVM을 조금 더 체계적으로 사용해보고자 도입했습니다.

**3️⃣ Dependency Injection: Hilt** <br/>
의존성 주입으로 뷰모델 관리를 더욱 편하게 하고자 도입했습니다.
구글이 공식 지원하는 DI 라이브러리로 보일러플레이트 코드를 최소화할 수 있습니다.

**4️⃣ Navigation: Type-Safety Navigation**<br/>
기존 문자열 기반 네비게이션은 런타임 오류를 유발할 수 있기 때문에 타입 안정성을 지원하는 Type-Safety Navigation을 도입했습니다.

<br/>

## **🎁 Convention**

🎉 [그라운드룰](https://lively-mars-3b7.notion.site/225ab823e68d8012a77df3bb361c33a7?source=copy_link)<br/>
💫 [Git Convention](https://lively-mars-3b7.notion.site/Git-Convention-216ab823e68d80f88991f7d974e17541?source=copy_link)<br/>
✍️ [Issue & PR Convention](https://lively-mars-3b7.notion.site/Issue-PR-Convention-216ab823e68d803ba888cf3702831e2f?source=copy_link)<br/>
📂 [Packaging Convention](https://lively-mars-3b7.notion.site/Package-Convention-216ab823e68d80e68b7efed278181350?source=copy_link)<br/>

<br/>

## **🗂️ Foldering**
```
📂 heartz
┣ 📂 core
┃ ┣ 📂 designsystem
┃ ┣ 📂 util
┃ ┣ 📂 base
┣ 📂 data
┃ ┣ 📂 dto 
┃ ┣ 📂 service                
┃ ┣ 📂 datasource            
┃ ┣ 📂 datasourceimpl       
┃ ┣ 📂 repositoryimpl   
┃ ┣ 📂 mapper
┣ 📂 domain
┃ ┣ 📂 model           
┃ ┣ 📂 repository
┣ 📂 feature
┃ ┣ 📂 home
┃ ┃ ┣ 📄 HomeScreen.kt
┃ ┃ ┣ 📄 HomeViewModel.kt
┃ ┃ ┣ 📄 HomeUiState.kt

```
