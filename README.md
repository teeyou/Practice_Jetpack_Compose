# Practice_Jetpack_Compose
## GoogleCodeLabs - https://codelabs.developers.google.com/?cat=Android&text=compose
### Basics (ViewModel, LazyColumn, SpringAnimation)
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Practice_Jetpack_Compose/tree/master/app/src/main/java/dgu/cse/teeu/practicecompose/basics)
<p align="center">
  <img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/637e6ed0-a7f0-4af4-96cf-d7fc966a02ab" width="200" height="400"/>
</p>
<p align="center">remember{mutableStateOf()} -> ViewModel로 변경해서 상태 저장</p>

#
### State (Hoisting, Stateless, TextField, KeyboardOptions, NumberFormat)
- [CodeLabs](https://developer.android.com/codelabs/basic-android-kotlin-compose-using-state?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Practice_Jetpack_Compose/tree/master/app/src/main/java/dgu/cse/teeu/practicecompose/state)
<p align="center">
  <img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/5b26b93a-202d-4937-9427-01580506e1ef" width="200" height="400"/>
</p>

#
### ViewModel & State (UiState, Screen, ViewModel, StateFlow)
- [CodeLabs](https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_ViewModel_State/tree/main/app/src/main/java/com/example/unscramble)
<p align="center">
  <img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/28cc946c-a81e-4b13-8dac-5546a2edc9ee" width="200" height="400"/>
</p>
<p align="center">StateFlow (Kotlin Library) -> State (Only Jetpack Compose) 로 변경</p>


```
* MainActivity는 초기화면을 구성하는 Composable만 호출
* values - dimens.xml 에서 padding 정의
* ui.theme - Shape.kt 파일 추가해서 테두리 부드럽게 하는 Shape 정의
* data 패키지 안에 상수 값, 데이터 셋 정의
  - const val 대_문_자 = value
  - Set<T> - 중복 허용하지 않고, 순서 따지지 않는 컬렉션

* ui 패키지 안에 
  - UiState (MVVM에서 Model에 해당) - data class 정의
  - Screen (MVVM에서 View에 해당) - Composable은 여기에서 만들고, 각 Composable 명칭은 대문자로 시작
    LocalContext.current로 Context 가져옴
    val activity = (LocalContext.current as Activity) 를 이용해서 Activity로 캐스팅해서 가져옴

  - ViewModel (MVVM에서 ViewModel에 해당) - 내부에서 사용하는 mutable과 외부에서 사용하는 immutable 사용
    ViewModel은 상위 Composable의 파라미터에서 디폴트 값으로 지정해서 생성 (Screen 최상위)
    하위 Composable에는 ViewModel 자체를 전달하지 않고, ViewModel을 통한 값, 함수만 전달하는 방식으로 사용

* 사용한 Composable
  - Column
    최상위 Composable에서 Modifier.verticalScroll(rememberScrollState()) 을 사용해서 가로모드 일 때 Scroll이 되도록 해줌

  - Text
    Modifier.clip()을 이용해서 테두리를 RoundedCornerShape()로 바꿈

  - Card 
    Light모드에서 elevation으로 그림자 효과를 줘서 계층 표현

  - Button, OutlinedButton
    Outlined는 배경색이 있는 Composable보다 덜 강조하는 효과

  - OutlinedTextField - 아래의 속성으로 키보드의 버튼 상태 및 액션 구현
    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    keyboardActions = KeyboardActions(onDone = { onKeyboardDone() } )

  - AlertDialog
    onDismissRequest = { } Dialog 외부 터치하거나, Back 버튼 눌러서 Dismiss 할 때 사용. 아무 동작도 안할거면 비워둔 상태로 사용
```

#
### Theme (M2)
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-theming-m2?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Code/tree/main/ThemingCodelabM2/app/src/main/java/com/codelab/theming) 
<p>
  <table>
    <tbody>
      <tr>
        <td><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming-m2/img/dd34ccbe5dcdfe58_856.png?hl=ko" width="200" height="400"/></td>
        <td><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming-m2/img/14515bb612cf4b30_856.png?hl=ko" width="200" height="400"/></td>
        <td><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming-m2/img/93f55b5d4cffb076_856.png?hl=ko" width="200" height="400"/></td>
      </tr>
      <tr>
        <td>Origin</td>
        <td>Light Mode</td>
        <td>Dark Mode</td>
      </tr>
    </tbody>
  </table>
</p>

```
* Material Theme
  - 색상 Color
  - 서체 Type
  - 도형 Shape
  으로 구성되고, Theme.kt 파일에서 사용
  MaterialTheme(
  colorScheme = colorScheme,
          typography = Typography,
          content = content,
          shapes = Shapes
  )

* Surface - 배경화면이나 넓은 범위에서 사용
* Primary, Secondary, Tetiary - 포그라운드 요소에 점차적으로 강조할 때 사용
* Container - Button과 같은 포그라운드 요소에서 사용. Text,Icon에서 안씀
* On - Text, Icon 같은 요소에서 parent color의 위에서 사용

  AppBar - primary
  FloatingActionButton - secondary

* 새로운 색상을 정의할 때, 정적으로 만들지 않고, 아래처럼 기존 색상을 복사해서 사용하는게 좋음
  val derivedColor = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)

* Surface(
    color: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(color),
    . . .
  )
  color - 해당 Composable의 색상
  contentColor - 해당 Composable 안에 있는 content의 색상
  contentColorFor() 메소드를 쓰면 color에 대응되는 on색상을 가져옴

* Modifier.background를 직접 호출할 때는 주의
* 요소의 색상을 설정할 때는 Surface를 사용. 알아서 적절한 콘텐츠 색상 CompositionLocal 값을 설정하기 때문에
  Row(Modifier.background(MaterialTheme.colorScheme.background))
  -> Surface(color = MaterialTheme.colorScheme.background) {Row()}

* AnnotatedString을 사용하여 텍스트에 여러 스타일을 적용가능
  val text = buildAnnotatedString { 
  	append("string") 
  	withStyle(SpanStyle(color = Color.Red, fontSize = 24.sp)) { append("style") }
  }
  Text(text = text, modifier = modifier)
```

#
### Theme (M3)
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-theming?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Code/tree/main/ThemingCodelab/app/src/main/java/com/example/reply) 
- Material3 theme builder custom (https://m3.material.io/theme-builder#/custom) (팔레트 조합 자동 생성)

<table>
   <tbody>
     <tr>
       <th><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming/img/fddf7b9cc99b1fe3_856.png?hl=ko" width="200" height="400"/></th>
       <th><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming/img/be7a661b4553167b_856.png?hl=ko" width="200" height="400"/></th>
       <th><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming/img/e70d762495173610_856.png?hl=ko" width="200" height="400"/></th>
     </tr>
     <tr>
       <th>ReplayApp()</th>
       <th>AppTheme { ReplayApp() }</th>
       <th>AppTheme { Surface(tonalElevation = 5.dp) { ReplayApp() }}}</th>
     </tr>
      <tr>
       <th><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming/img/b1b374b801dadc06_856.png?hl=ko" width="200" height="400"/></th>
       <th><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming/img/70ceac87233fe466_856.png?hl=ko" width="200" height="400"/></th>
       <th><img src="https://developer.android.com/static/codelabs/jetpack-compose-theming/img/1095e2b2c1ffdc14_856.png?hl=ko" width="200" height="400"/></th>
     </tr>
     <tr>
       <th>검색창 배경 색상</th>
       <th>플로팅 작업 버튼 색상</th>
       <th>동적 색상 + 상태표시줄 색상</th>
     </tr>
   </tbody>
</table>


```
* 검색창 배경 색상
Row(modifier = modifier.background(MaterialTheme.colorScheme.background)) 추가


* 플로팅 작업 버튼 색상
LargeFloatingActionButton(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer, - 컨테이너 색
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer, - 컨텐츠 색
	onClick = {} ...
)


* 카드 색상
Card(
	modifier = modifier... ,
	colors = CardDefaults.cardColors(
            containerColor = if (email.isImportant)
                MaterialTheme.colorScheme.secondaryContainer
            else 
	    MaterialTheme.colorScheme.surfaceVariant
) 	
{}


* 리스트 아이템 배경
Column(
	modifier = modifier
	.fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)	//순서 중요!
            .padding(20.dp)
)
{	//List item content }


* 동적 색상
동적 색상은 Android 12 이상에서 사용. 시스템 테마와 일관된 환경 제공
dynamicDarkColorScheme() 또는 dynamicLightColorScheme()을 사용하여 동적 색 구성표를 설정
그렇지 않은 경우 기본 밝은/어두운 ColorScheme을 사용하는 것으로 대체


* 상태표시줄 색상
val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }

    // Add primary status bar color from chosen color scheme.
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()	//Light or Dark 모드의 primary로 설정
            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = darkTheme
        }
    }



* 서체
Typography 생성자는 각 스타일의 기본값을 제공하므로 맞춤설정하지 않으려는 매개변수는 생략가능
현재 앱에 5가지 서체 스타일(headlineSmall, titleLarge, bodyLarge, bodyMedium, labelMedium)을 사용
Material Design 서체 스케일에서 15개의 기본 스타일 제공
Text() 컴포저블에 서체를 적용하지 않으면 기본적으로 Typography.bodyLarge로 대체


* 도형
도형 배율에는 다음과 같은 크기의 도형이 있음
아주 작게
작게
보통
크게
아주 크게

val shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

https://developer.android.com/static/codelabs/jetpack-compose-theming/img/539b114505a1bfed_856.png?hl=ko

도형을 가져오는 Modifier.clip, Modifier.background, Modifier.border 등의 Modifiers를 사용하여 구성요소에 도형을 적용할 수 있음
Column(modifier.background(color = ... , MaterialTheme.shapes.medium) )

RectangleShape 및 CircleShape으로도 도형 가져올 수 있음
Row(modifier = modifier.background(color = ... , shape = CircleShape) )


* 강조
on-color 조합으로 강조
배경이 secondaryContainer 로 설정되면, 그 위에 올라가는건(Text()) 기본적으로 onSecondaryContainer
배경이 surfaceVariant면, Text()는 onSurfaceVariant

```

#
### State 02 (Hoisting, Stateless, Stateful, mutableStateList, MVVM)
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-state?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_ViewModel_State_02/tree/master/app/src/main/java/dgu/cse/teeu/basicstatecodelab)
<p align="center">
  <img src="https://developer.android.com/static/codelabs/jetpack-compose-state/img/e7cc030cd7e8b66f.gif?hl=ko" width="200" height="400"/>
</p>

```
* 상태 호이스팅
remember가 있는 컴포저블은 내부 상태가 포함되어 있어서 재사용성이 적고, 테스트하기 어려움 (Stateful)
상태 호이스팅을 사용해서 Stateless 컴포저블로 쉽게 만들 수 있음
컴포저블은 필요한 매개변수만 전달해야 함
Stateful 컴포저블 안에 여러개의 Stateless 컴포저블을 넣어서 State를 공유할 수 있음 (매개변수에 필요한 것만 넣음)

컴포저블은 작고 재사용 가능해야 함

* LazyColumn을 이용해서 List를 만들 때
1. data class 정의하고,
2. Item 컴포저블 정의하고,
3. List 컴포저블 정의
결과적으로 Screen 컴포저블에서 상태를 가지고 있고, 이 안에 List 컴포저블이 생성되어서 화면에 표시 (MainActivity -> Screen() -> List() -> Item())

* ViewModel을 이용한 상태관리
remember 대신 모든 상태관리를 ViewModel에서 관리하도록 하면,
Stateful 컴포저블이 필요없어짐 (코드가 더 간단해지고 테스트하기 쉬워짐)

* List의 item을 업데이트하고 리컴포지션 시키는 2가지 방법
1. data class를 class로 변경하고, 변경되는 값을 mutableState<T>로 변경

data class WellnessTask(val id: Int, val label: String, var checked: Boolean = false) 아래처럼 변경

class WellnessTask(val id: Int, val label: String, initialChecked: Boolean = false) {
    var checked by mutableStateOf(initialChecked)
}

2. List에서 변경되는 item을 복사하고 삭제한 후에, item을 변경시켜서 다시 추가 (비용이 많이들어서 1번으로 해결하는게 좋음)
```

#
### Navigation 01 + ViewModel (AppBar with NavigateUp, Intent ACTION_SEND)
- [CodeLabs](https://developer.android.com/codelabs/basic-android-kotlin-compose-navigation?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Navigation_Basics/tree/main/app/src/main/java/com/example/cupcake)
<p align="center">
<img src="https://developer.android.com/static/codelabs/basic-android-kotlin-compose-navigation/img/fde052dfa0a1f56a.png?hl=ko" width="200" height="400"/>
<img src="https://developer.android.com/static/codelabs/basic-android-kotlin-compose-navigation/img/46faceda3f6d7c39.png?hl=ko" width="200" height="400"/>
<img src="https://developer.android.com/static/codelabs/basic-android-kotlin-compose-navigation/img/f1c19a026527d520.png?hl=ko" width="200" height="400"/>
</p>

### Navigation 02 (Tab, BackStack, Arguments, DeepLink)
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-navigation?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Code/tree/main/NavigationCodelab/app/src/main/java/com/example/compose/rally)
<p align="center">
<img src="https://developer.android.com/static/codelabs/jetpack-compose-navigation/img/87e7dfa76ef51d50_856.png?hl=ko" width="200" height="400"/>
<img src="https://developer.android.com/static/codelabs/jetpack-compose-navigation/img/a7c8a51fe2503409_856.png?hl=ko" width="200" height="400"/>
<img src="https://developer.android.com/static/codelabs/jetpack-compose-navigation/img/9e4c38a6bff0fdbb_856.png?hl=ko" width="200" height="400"/>
</p>

```
NavController는 항상 컴포저블 계층 구조의 최상위 수준에서 만들고 배치

Navigation의 세 가지 주요 부분은 NavController, NavGraph, NavHost
NavHost는 컨테이너 역할을 하며 그래프의 현재 대상을 표시하는 일을 담당
NavGraph - 이동 가능한 컴포저블 대상을 매핑하는 탐색 그래프
Scaffold() { NavHost() { composable(route = ""){Screen()} } }
composable()을 통해서 탐색 그래프에 추가

알아서 BackStack에 쌓여서 Back버튼 누르면 뒤로 감

동일한 tab을 반복해서 누르면 여러번 생성되는 문제가 발생
아래의 코드로 해결 할 수 있음
navController.navigate(route) { launchSingleTop = true }

아래처럼 확장함수로 정의해놓고, 
fun NavHostController.navigateSingleTopTo(route: String) { this.navigate(route) {launchSingleTop = true}}

navController.navigate(route) { launchSingleTop = true } 이 코드를 아래처럼
navController.navigateSingleTopTo(route) 사용하면 더 좋은 코드

launchSingleTop = true
- 백 스택에 사본이 최대 1개만 생성. (false라면 동일한 탭을 계속 눌르면 백스택에 누른 만큼 쌓임)

popUpTo(startDestination) { saveState = true } 
- 여러개의 탭을 선택하면 모든 탭이 백스택에 쌓이지 않게 함. 
- 시작 탭만 백스택에 추가되어서 시작 탭으로 바로 이동

restoreState = true
saveState에 의해 저장된 상태를 복원할지 여부를 결정.
저장된 state가 없으면 이 옵션은 효과가 없음

* 인수 전달
 composable(route = "route/{argument}")
 NavBackStackEntry에서 arguments를 가져올 수 있음

* 딥 링크
 앱 내의 특정 대상으로 직접 이동할 수 있는 링크

외부 앱에 딥 링크를 노출하는 것은 기본적으로 사용 설정되지 않아서 
Manifest에서 <intent-filter> 요소를 추가해야 함
<activity>
	...

 <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="앱이름" android:host="호스트에 등록된 컴포저블 경로" />
    </intent-filter>
</activity>
 
url은 다음과 같은 형태로 생성됨
앱이름://컴포저블경로 
다음으로 해당 컴포저블에서 deepLink 매개변수를 추가해야함

* AppBar의 Up버튼
 navController.previousBackStackEntry != null 을 이용해서 Up버튼을 숨기거나 보여줌

* 다른 앱으로 이동
 인텐트 객체를 만들고 ACTION_SEND 지정
 인텐트와 함께 전송되는 추가 데이터의 유형을 지정 ("text/plain" , "image/*", "video/*" 등)
 putExtra() 메서드로 공유할 텍스트 또는 이미지와 같은 추가 데이터를 인텐트에 전달
 context의 startActivity() 메서드를 호출하여 전달
```

#
### Animation
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-animation?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Code/tree/main/AnimationCodelab/finished/src/main/java/com/example/android/codelab/animation)
<p align="center">
<img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/32364ee6-a390-4ce4-aeaf-0fceda637087" width="200" height="400"/>
</p>

```
* 간단한 값 변경 애니메이션
val backgroundColor = if (tabPage == TabPage.Home) Purple100 else Green300 아래처럼 사용
val backgroundColor by animateColorAsState(if (tabPage == TabPage.Home) Purple100 else Green300)

* 가시성 애니메이션
if (extended) { ... } -> AnimatedVisibility(extended) { ... } 로 변경
FAB가 ScrollDown 되면 icon만 보이고, ScrollUp 되면 icon + Text 가 보이도록 활용

* 위에서 아래로, 아래에서 위로 SlideInOut 하는 애니메이션
AnimatedVisibility(
    visible = shown,
    enter = slideInVertically( initialOffsetY = { fullHeight -> -fullHeight } ),
    exit = slideOutVertically( targetOffsetY = { fullHeight -> -fullHeight } )
)

* animationSpec 매개변수를 사용하여 애니메이션을 추가로 맞춤설정
animationSpec = tween() 이징 곡선을 사용하여 지정된 durationMillis 동안 시작 값과 끝 값 간에 애니메이션을 처리

animationSpec = spring() dampingRatio는 스프링의 탄성. stiffness는 스프링이 종료 값으로 이동하는 속도를 정의
기본값은 Spring.DampingRatioNoBouncy, Spring.StiffnessMedium
spring 참고 이미지 - https://developer.android.com/static/images/jetpack/compose/animation-spring.gif?hl=ko

* content의 크기에 따른 애니메이션 animateContentSize
Column(modifier = Modifier.animateContentSize()){} 을 이용해서 item을 펼치거나 접을 때 애니메이션 적용가능

* Transition API - animate*AsState API를 사용하는 경우에는 불가능한 더 복잡한 애니메이션을 만들 수 있음
Tab 변경시 Border의 왼쪽, 오른쪽 이동에 활용

* 애니메이션 반복
animationSpec = infiniteRepeatable() - 데이터 로딩할 때 사용

* 동작 애니메이션
Animatable
화면을 터치했을 때 x,y좌표와 이동속도 감지
```

#
### Adaptive Layout (Compact, Medium, Expanded)
- [CodeLabs](https://developer.android.com/codelabs/basic-android-kotlin-compose-adaptive-content-for-large-screens?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Adaptive_Layout/tree/nav-update/app/src/main/java/com/example/reply)
<p align="center">
<img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/35d6c504-dd79-489e-a083-214def64433c" width="200" height="400"/>
<img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/b84a7f88-50a4-4e89-ad9b-06d0607382b8" width="300" height="200"/>
<img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/2b74a5da-cac9-4ab0-b888-4426e3ca3c6f" width="400" height="200"/>
</p>

```
Compact (세로모드) - NavigationBar + 리스트
Medium (가로모드, 폴더블 폈을 때, 태블릿 세로) - NavigationRail + 리스트
Expanded (태블릿 가로) - PermanentNavigationDrawer + 리스트 + 디테일

utils 패키지에서 아래와 같이 enum 클래스 정의
enum class NavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}
enum class ContentType {
    LIST_ONLY, LIST_AND_DETAIL
}

MainActivity에서
val windowSize = calculateWindowSizeClass(this) 
YourApp(windowSize = windowSize.widthSizeClass)

YourApp() {
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY

        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
            contentType = ReplyContentType.LIST_ONLY

        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ReplyContentType.LIST_AND_DETAIL

        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY

        }
    }

    Screen(navigationType, contentType, ...)
}
```

#
### Retrofit2 (Coil, Serialize, Converter, ViewModel Factory, AppBar ScrollBehavior)
- [CodeLabs_00 - Coil](https://developer.android.com/codelabs/basic-android-kotlin-compose-load-images?hl=ko#0)
- [CodeLabs_01 - Retrofit2 Basic](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?hl=ko#0)
- [CodeLabs_02 - Data Layer, UI Layer, Repository Pattern, DI](https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Retrofit2/tree/main/app/src/main/java/com/example/marsphotos)
<p align="center">
<img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/1b961a7d-0916-48c8-937a-c2bb32016406" width="200" height="400"/>
</p>

```
Json -> 코틀린 객체로 직렬화
@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)

Retrofit을 사용할 때 Converter Library로  Gson, Jackson, Moshi 등이 있는데,
이는 kotlin 문법에서 쓰는 생성자의 Default value를 무시하게 됨.
그래서 코틀린확장 컨버터를 사용하는게 좋음.

plugins에
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"를 추가하고
 
dependencies에
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
를 추가해서 사용


네트워크 연결시 다음과 같은 문제 발생할 수 있음
- API에 사용된 URL 또는 URI가 잘못됨
- 서버를 사용할 수 없어 앱을 서버에 연결할 수 없음	 
- 네트워크 지연 문제가 있음
- 기기의 인터넷 연결이 불안정하거나 기기가 인터넷에 연결되지 않음

따라서 네트워크로부터 데이터를 받아올 때, 
비정상적으로 종료되는 것을 막기 위해서 try-catch를 이용해서 아래처럼 사용
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }


sealed를 이용해서 클래스를 묶으면
sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}
when 에서 else를 안 써도 됨


AppBar의 스크롤 동작
- 공식문서 (https://developer.android.com/jetpack/compose/components/app-bars?hl=ko_)

TopAppBarScrollBehavior 3가지
1. enterAlwaysScrollBehavior - 사용자가 Scaffold의 내부 콘텐츠를 가져오면 상단 앱 바가 접힙니다. 앱 바는 사용자가 내부 콘텐츠를 끌어내리면 확장됩니다.
2. exitUntilCollapsedScrollBehavior - enterAlwaysScrollBehavior와 비슷하지만 사용자가 Scaffold 내부 콘텐츠의 끝에 도달하면 앱 바가 추가로 확장됩니다.
3. pinnedScrollBehavior - 앱 바가 제자리에 유지되고 스크롤에 반응하지 않습니다.


데이터 레이어, UI 레이어 분리
Repository(데이터 레이어), ViewModel(UI 레이어)
ViewModel이 데이터의 네트워크 요청을 직접 실행하지 않고, Repository가 데이터를 제공
Repository Pattern + DI를 이용해서 분리
Repository 이름 지정 규칙은 데이터 유형 + 저장소. 이 앱의 경우 MarsPhotosRepository

DI (의존성 주입)
코드의 유연성과 적응성을 높이려면 클래스가 종속되는 객체를 인스턴스화하면 안 됨.
종속되는 객체는 클래스 외부에서 인스턴스화한 후 전달.

ViewModel에 Repository를 전달하기 위해서 다음과 같이 Factory 사용 (기본적으로 ViewModel은 생성자를 가질 수 없어서 생성패턴을 이용)

MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {
	...

companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}

val marsViewModel: MarsViewModel = viewModel(factory = MarsViewModel.Factory)

```

#
### Migration (View -> Compose 이전, Unsplash API 사용, Retrofit2, Tab + Pager, Room, WorkManager, Paging)
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-migration?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Code/tree/main/MigrationCodelab/app/src/main/java/com/google/samples/apps/sunflower)
- [SourceCode - Final](https://github.com/teeyou/Compose_MigrationCodelab_sunflower/tree/main)
<p align="center">
<img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/b1ffb576-2b98-43bf-8066-c62cf0c30636" width="200" height="400"/>
</p>

```
* API KEY 세팅
	local.properties 에서
	UNSPLASH_ACCESS_KEY="YOUR_API_KEY" 추가

	build.gradle 에서
	import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties 추가
	fun getApiKey(propertyKey: String): String = gradleLocalProperties(rootDir).getProperty(propertyKey) 추가
	buildFeatures { buildConfig = true } 추가
	defaultConfig { buildConfigField("String", "UNSPLASH_ACCESS_KEY", getApiKey("UNSPLASH_ACCESS_KEY")) } 추가
	
	코드에서 BuildConfig.UNSPLASH_ACCESS_KEY 로 API KEY 사용


* xml에서 이전하려고 하는 View를 ComposeView로 변경
<androidx.compose.ui.platform.ComposeView
                android:id="@+id/compose_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent"/>

* 새로운 kt파일을 만들어서 기존의 View와 동일하도록 Composable 만들고,
	MaterialTheme.typography.h5 - textAppearanceHeadline5
	fillMaxWidth - layout_width 속성의 match_parent 
	padding(horizontal = ) - marginStart 및 marginEnd 
	wrapContentWidth(Alignment.CenterHorizontally) - gravity가 center_horizontal

* Activity 또는 Fragment 내에서
아래와 같이 setViewCompositionStrategy (Activity 또는 Fragment의 생명주기와 맞춰서 컴포지션),  setContent 작성
binding.apply {
	...

	composeView.apply {
                // Dispose the Composition when the view's LifecycleOwner is destroyed
                setViewCompositionStrategy(
                    ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
                )
                setContent {
                    SunflowerTheme {
                        PlantDetailDescription(plantDetailViewModel)
                    }
                }
            }
 }

* Composable에서 LiveData를 관찰하려면 LiveData.observeAsState() 함수 사용
val plant by plantDetailViewModel.plant.observeAsState()

    // If plant is not null, display the content
    plant?.let {
        PlantDetailContent(it)
    }

```

#
### Room Basics (AppContainer, ViewModelProvider)
- [CodeLabs 01](https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room?hl=ko#0)
- [CodeLabs 02](https://developer.android.com/codelabs/basic-android-kotlin-compose-update-data-room?hl=ko#0)
- [SourceCode](https://github.com/teeyou/Compose_CodeLabs_Room)
<p align="center">
<img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/55b4991d-091e-47af-b55d-c92c23e5f1fb" width="200" height="400"/>
</p>

```
Room - SQLite 위에 있는 추상화 레이어, 기본구현/복잡성을 숨겨서 사용하기 편리

@PrimaryKey(autoGenerate = true)
autoGenerate 매개변수를 true로 설정하면 각 항목에 고유한 ID 부여

DAO - 추상 인터페이스를 제공. DB작업 메소드를 모아둠. 단일책임원칙.

@Insert @Delete @Update 주석으로 메소드 정의

나머지 기능에는 편의 주석이 없으므로 @Query 주석을 사용하여 SQLite 쿼리를 직접 작성해야 함
@Query("SELECT * from items WHERE id = :id")
fun getItem(id: Int): Flow<Item>

지속성 레이어에서 Flow를 사용하는 것이 좋음
Flow를 리턴타입으로 쓰면, 데이터베이스의 데이터가 변경될 때마다 알림받음
Room은 Flow를 자동으로 업데이트

데이터베이스 작업은 시간이 오래 걸릴 수 있으므로 별도의 스레드에서 실행 (코루틴)
Room은 메인스레드에서 DB 액세스를 허용안함

OnConflictStrategy.IGNORE - 충돌발생시 새 항목 무시
@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insert(item: Item)

Entity와 DAO를 이용해서 추상 RoomDatabase 클래스를 만들고 @Database 주석
RoomDatabase는 싱글톤으로 생성

@Database(entities = [Item::class], version = 1, exportSchema = false)
exportSchema - 스키마 버전 기록 백업 유지 여부

@Volatile
private var Instance: InventoryDatabase? = null

휘발성 변수의 값은 캐시되지 않으며 모든 읽기와 쓰기는 기본 메모리에서 이루어짐
Instance 값이 항상 최신 상태로 유지되고 모든 실행 스레드에 동일하게 유지

동시에 여러 스레드에서 database 인스턴스를 요청할 수 있기 때문에 동기화 작업이 필요 (synchronized)
fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }


interface ItemRepository에 DAO에 매핑되는 함수 작성하고,
Repository를 구현하는 클래스 생성
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository
itemDao에 상응하는 함수 작성

* AppContainer 클래스
데이터베이스를 인스턴스화하고 DAO 인스턴스를 OfflineItemsRepository 클래스에 전달

interface AppContainer {
    val itemsRepository: ItemsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}

InventoryApplication 에서 AppDataContainer 인스턴스 생성하고,
AppViewModelProvider에서 생성자가 있는 ViewModel을 생성하는 Factory 작성해서
Screen 컴포저블에서 viewModel 사용할 때 Provider의 Factory 사용해서 생성함

아래의 함수를 작성하면 inventoryApplication().container로  AppDataContainer 접근 가능
fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)

ViewModel에서 State객체 생성할 때
var _uiState : MutableState
val uiState : State
2개를 만드는 것 대신에 아래처럼 1개의 인스턴스만 생성
var _uiState
private set

ViewModel에서 Flow를 노출할 때 권장되는 방법은 StateFlow
StateFlow를 사용하면 UI 수명 주기와 관계없이 데이터를 저장하고 관찰할 수 있음
Flow를 StateFlow로 변환하려면 stateIn 연산자 사용
val homeUiState: StateFlow<HomeUiState> = itemsRepository.getAllItemsStream().map { HomeUiState(it) }
        .stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS), initialValue = HomeUiState())

```
