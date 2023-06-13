/*
Practice Jetpack Compose.

By @alottachairs

 */

package com.example.lifegivesyoulemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lifegivesyoulemons.ui.theme.LifeGivesYouLemonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeGivesYouLemonsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Lemon preview"
)

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    LemonadeButtonAndText()
}

@Composable
fun LemonadeButtonAndText(modifier: Modifier = Modifier){

    var stage by remember { mutableStateOf(1) }

    if (stage == 5){
        stage = 1
    }

    var squeezeCount by remember { mutableStateOf(0) }

    val ImageResource = when (stage){
        1 -> painterResource(id = R.drawable.lemon_tree)
        2 -> painterResource(id = R.drawable.lemon_squeeze)
        3 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }
    val contentDescriptionResource : String = when (stage) {
        1 -> stringResource(id = R.string.lemon_tree)
        2 -> stringResource(id = R.string.lemon)
        3 -> stringResource(id = R.string.glass_of_lemonade)
        else -> stringResource(id = R.string.empty_glass)
    }
    val TextContent = when (stage) {
        1 -> stringResource(id = R.string.tree)
        2 -> stringResource(id = R.string.keep_tapping)
        3 -> stringResource(id = R.string.drink_it)
        else -> stringResource(id = R.string.empty)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if(stage == 2 && squeezeCount == 0){stage++}
            else if(stage == 2 && squeezeCount > 0){squeezeCount--}
            else if(stage == 1 && squeezeCount == 0){squeezeCount = (2..4).random(); stage++}
            else{stage++}
        },
            shape = RoundedCornerShape(34.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        )

        {
            Image(
                painter = ImageResource,
                contentDescription = contentDescriptionResource
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = TextContent,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )

    }

}
