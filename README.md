# Practice_Jetpack_Compose
## GoogleCodeLabs - https://codelabs.developers.google.com/?cat=Android&text=compose
### Basics (ViewModel, LazyColumn, SpringAnimation)
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-basics?hl=ko#13)
- [SourceCode](https://github.com/teeyou/Practice_Jetpack_Compose/tree/master/app/src/main/java/dgu/cse/teeu/practicecompose/basics)
<p align="center">
  <img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/637e6ed0-a7f0-4af4-96cf-d7fc966a02ab" width="200" height="400"/>
</p>
<p align="center">remember{mutableStateOf()} -> ViewModel로 변경해서 상태 저장</p>

#
### State (Hoisting, Stateless, TextField, KeyboardOptions, NumberFormat)
- [CodeLabs](https://developer.android.com/codelabs/basic-android-kotlin-compose-using-state?hl=ko#11)
- [SourceCode](https://github.com/teeyou/Practice_Jetpack_Compose/tree/master/app/src/main/java/dgu/cse/teeu/practicecompose/state)
<p align="center">
  <img src="https://github.com/teeyou/Practice_Jetpack_Compose/assets/46315397/5b26b93a-202d-4937-9427-01580506e1ef" width="200" height="400"/>
</p>

#
### ViewModel & State (UiState, Screen, ViewModel, StateFlow)
- [CodeLabs](https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state?hl=ko#11)
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
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-theming-m2?hl=ko#8)
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
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-theming?hl=ko#8)
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
- [CodeLabs](https://developer.android.com/codelabs/jetpack-compose-state?hl=ko#12)
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

