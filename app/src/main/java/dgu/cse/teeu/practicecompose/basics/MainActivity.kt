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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dgu.cse.teeu.practicecompose.R
import dgu.cse.teeu.practicecompose.ui.theme.PracticeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable //기본적으로 Modifier를 갖도록 하는게 재사용하기에 좋음
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
//    val lazyListState = rememberLazyListState()
//    val viewModel : MainViewModel = viewModel()

    if(shouldShowOnboarding)
        OnboardingScreen(modifier) {shouldShowOnboarding = false}
    else
        Greetings(modifier = modifier)
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
fun Greetings(modifier: Modifier = Modifier,
              names : List<String> = List(20){"$it"}
) {
    LazyColumn(modifier.padding(vertical = 4.dp)) {
        itemsIndexed(items = names) { index, item ->
            Greeting(name = item, idx = index)
        }
    }
}
@Composable
fun Greeting(name: String, idx: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name, idx)
    }
}


@Composable
private fun CardContent(name: String, idx: Int) {
//    var expanded by remember { mutableStateOf(false) }
    val viewModel: MainViewModel = viewModel()
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
            if (/*expanded*/ viewModel.expandedList.value[idx]) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { /*expanded = !expanded*/ viewModel.changeState(idx,!viewModel.expandedList.value[idx]) }) {
            Icon(
                imageVector = if (/*expanded*/viewModel.expandedList.value[idx]) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (/*expanded*/viewModel.expandedList.value[idx]) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

class MainViewModel : ViewModel() {
    private val _expandedList : MutableState<List<Boolean>> = mutableStateOf(List<Boolean>(20){false})
    val expandedList : State<List<Boolean>> = _expandedList

    fun changeState(idx: Int, flag: Boolean) {
        val list = mutableListOf<Boolean>()
        _expandedList.value.forEach {
            list.add(it)
        }
        list[idx] = flag

        _expandedList.value = list
    }
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PracticeComposeTheme {
//        MyApp()
//    }
//}