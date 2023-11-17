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
### Theme
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
