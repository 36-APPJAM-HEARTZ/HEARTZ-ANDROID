# 💔 ByeBoo

> 이별을 **성장의 디딤돌**로 만드는 정서 회복 경험을 제공합니다! 
><br/>이별 후의 감정을 맞춤형 퀘스트로 정리하고 극복하도록 돕는 감정 케어 서비스
<br/>

## ⭐ ByeBoo 주요 기능
1️⃣ **퀘스트 기능** <br/>
본문내용
<br/>
<br/>
2️⃣3️⃣4️⃣ **퀘스트 기능** <br/>
본문내용
<br/>


## **👀 Contributors**

|                              👑 이종훈 <br> [@fredleeJH](https://github.com/fredleeJH)                   |                           정소희 <br> [@sohee6989](https://github.com/sohee6989)                             |                  주아연 <br> [@znayeonzn](https://github.com/znayeonzn)                             |
|:--------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------:|
| <img width="250" src="https://avatars.githubusercontent.com/u/155813460?v=4"/> | <img width="250" src="https://avatars.githubusercontent.com/u/144779368?v=4"/> | <img width="250" src="https://avatars.githubusercontent.com/u/142514626?v=4"/> |
|                                              `정보 입력`<br>`홈`                                                    |                                              `텍스트 기반 퀘스트`<br>`오프보딩`                                               |                           `행동 기반 퀘스트`<br>`오프보딩`<br>                                                      |

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

🎉[그라운드룰](https://lively-mars-3b7.notion.site/225ab823e68d8012a77df3bb361c33a7?source=copy_link)
💫[Git Convention](https://lively-mars-3b7.notion.site/Git-Convention-216ab823e68d80f88991f7d974e17541?source=copy_link)<br/>
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
