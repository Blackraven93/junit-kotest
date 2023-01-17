package com.example.junitandkotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.junitandkotest.ui.theme.JunitAndKoTestTheme
import java.lang.reflect.Constructor
import androidx.compose.runtime.ReusableComposeNode as ReusableComposeNode

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JunitAndKoTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    println(ColoredTextDemo(
                        text = "Hello Compose",
                        color = Color.Cyan
                    ))
                }
            }
        }
    }
}

@Composable
fun Welcome(name: String) {
    Text(
        text = stringResource(id = R.string.hello, name),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ColoredTextDemo(text: String = "", color: Color = Color.Black) {
    Text(text = text, style = TextStyle(color = color))
}

@Suppress( "ComposableLambdaParameterPosition")
@Composable inline fun Layout(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    measurePolicy: MeasurePolicy
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    ReusableComposeNode<ComposeUiNode, Applier<Any>>(
        factory = ComposeUiNode.Constructor,
        update = {
            set(measurePolicy, ComposeUiNode.SetMeasurePolicy)
            set(density, ComposeUiNode.SetDensity)
            set(layoutDirection, ComposeUiNode.SetLayoutDirection)
        },
        skippableUpdate = materializerOf(modifier),
        content = content
    )
}

@Composable @ExplicitGroupsComposable
inline fun <T, reified E : Applier<*>> ReusableComposeNode(
    noinline factory: () -> T,
    update: @DisallowComposableCalls Updater<T>.() -> Unit,
    noinline skippableUpdater: @Composable SkippableUpdater<T>.() -> Unit,
    content: @Composable () -> Unit
) {
    if (currentComposer.applier !is E)
    currentComposer.startReusableNode()
    if (currentComposer.inserting) {
        currentComposer.createNode(factory)
    } else {
        currentComposer.useNode()
    }

    currentComposer.disableReusing()
    Updater<T>(currentComposer).update()
    currentComposer.enableReusing()
    SkippableUpdater<T>(currentComposer).skippableUpdater()
    currentComposer.startReplaceableGroup(0x7ab4aae9)
    content()
    currentComposer.endReplaceableGroup()
    currentComposer.endNode()
}

@PublishedApi
internal interface ComposeUiNode {
    var measurePolicy: MeasurePolicy
    var layoutDirection: LayoutDirection
    var density: Density
    var modifier: Modifier

    companion object {
        val Constructor: () -> ComposeUiNode = LayoutNode.Constructor
        val SetModifier: ComposeUiNode.(Modifier) -> Unit = { this.modifier = it }
        val SetDensity: ComposeUiNode.(Density) -> Unit = { this.density = it}
        val SetMeasurePolicy: ComposeUiNode.(MeasurePolicy) -> Unit = { this.measurePolicy = it }
        val SetLayoutDirection: ComposeUiNode.(LayoutDirection) -> Unit = { this.layoutDirection = it}
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JunitAndKoTestTheme {
        ColoredTextDemo(
            text = "Hello Compose",
            color = Color.Cyan
        )
    }
}

