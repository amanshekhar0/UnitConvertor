package com.example.unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme

import kotlin.math.roundToInt
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}



@Composable
fun UnitConvertor () {

    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("meters")}
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember{mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.00)}
    val oConversionFactor = remember { mutableStateOf(1.00)}


    val customTextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 32.sp,
        color= Color.Blue,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Normal,
        background = Color.Yellow


    )
    fun convertUnits(){
        //?: elvis operator its like a if statement
        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result =(inputValueDouble * conversionFactor.value * 100.0/oConversionFactor.value).roundToInt()/100.0
        outputValue= result.toString()
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        // here all ui elements will be stacked below each other
        Text("  Unit convertor",style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { Text("enter value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {

            Box {
                // input button
                Button(onClick = {iExpanded=true}) {

                    Text(inputUnit)

                    Icon(
                        Icons.Default.ArrowDropDown,

                        contentDescription = "    Arrow Down     "
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded= false}) {
                    DropdownMenuItem(
                        text = { Text("centimeters")},
                        onClick = {
                            iExpanded= false
                            inputUnit="centimeters"
                            conversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("meters")},
                        onClick = {
                            iExpanded= false
                            inputUnit="meters"
                            conversionFactor.value=1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("feets")},
                        onClick = {
                            iExpanded= false
                            inputUnit="feets"
                            conversionFactor.value=0.3048
                            convertUnits()}
                    )
                    DropdownMenuItem(
                        text = { Text("millimeters")},
                        onClick = {
                            iExpanded= false
                            inputUnit="millimeters"
                            conversionFactor.value=0.001
                            convertUnits()}
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {

                Button(onClick = { oExpanded= true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "arrow down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {  oExpanded=false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            oExpanded= false
                            outputUnit="centimeters"
                            oConversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("meters")},
                        onClick = {oExpanded=false
                            outputUnit="meters"
                            oConversionFactor.value=1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("feets")},
                        onClick = {oExpanded= false
                            outputUnit="centimeters"
                            oConversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("millimeters")},
                        onClick = {oExpanded=false
                            outputUnit="millimeters"
                            oConversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }

                    

            }

        }
            Spacer(modifier = Modifier.height(16.dp))
        // result test
        Text("Result $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium )

    }






}



@Preview(showBackground =  true)
@Composable
fun UnitConvertorPreview (){
    UnitConvertor()
}

