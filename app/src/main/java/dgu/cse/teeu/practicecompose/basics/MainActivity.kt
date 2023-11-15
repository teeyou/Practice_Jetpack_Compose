package dgu.cse.teeu.practicecompose.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dgu.cse.teeu.practicecompose.R
import dgu.cse.teeu.practicecompose.ui.theme.PracticeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MyApp()
//                }
            }
        }
    }
}

@Composable //기본적으로 Modifier를 갖도록 하는게 재사용하기에 좋음
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    val lazyListState = rememberLazyListState()

    if(shouldShowOnboarding)
        OnboardingScreen(modifier) {shouldShowOnboarding = false}
    else
        Greetings(modifier = modifier,
            state = lazyListState)
}
@Composable
fun Greeting(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//
//    var expanded by remember { mutableStateOf(false) }
////    val extraPadding = if (expanded) 48.dp else 0.dp
//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )   //spring이외에 tween, repeatable등이 있음
//    )
//
//    Surface(
////        color = MaterialTheme.colorScheme.primary,
//        color = MaterialTheme.colorScheme.primary,
//        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//    ) {
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
//                Text(text = "Hello, ")
//                Text(text = name, style = MaterialTheme.typography.headlineMedium.copy(
//                    fontWeight = FontWeight.ExtraBold)) //copy 함수를 이용해서 스타일 변경
//            }   //coerceAtLeast(0.dp) padding이 음수가 되지 않도록 설정해줘야 함. 앱이 다운 될 수 있음
//            ElevatedButton(
//                onClick = { expanded = !expanded }
//            ) {
//                Text(if(expanded) "Show less" else "Show more")
//            }
//        }
//    }
//}

@Composable
fun Greetings(modifier: Modifier = Modifier,
              state: LazyListState,
              names : List<String> = List(20){"$it"}
) {
    LazyColumn(modifier.padding(vertical = 4.dp),
        state = state) {
//        itemsIndexed(items = names) { index, item ->
//            Greeting(name = item)
//        }

        items(items = names) { name ->
            Greeting(name = name)
        }

//        for (name in names) {
//            Greeting(name = name)
//        }
    }
}


@Composable
fun OnboardingScreen(modifier: Modifier = Modifier,
                     onContinueClicked : () -> Unit) {
    // TODO: This state should be hoisted
//    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PracticeComposeTheme {
//        MyApp()
//    }
//}