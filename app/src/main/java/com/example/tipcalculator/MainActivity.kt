package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                TipTimeLayout()
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var valorContaInput by remember { mutableStateOf("") }
    var percentualGorjetaInput by remember { mutableStateOf("") }
    var arredondarGorjeta by remember { mutableStateOf(false) }
    var gorjeta by remember { mutableStateOf(calcularGorjeta(0.0, 0.0, false)) }

    val valorConta = valorContaInput.toDoubleOrNull() ?: 0.0
    val percentualGorjeta = percentualGorjetaInput.toDoubleOrNull() ?: 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de Gorjeta",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.Start)
        )
        EditNumberField(
            label = "Valor da conta",
            value = valorContaInput,
            onValueChanged = { valorContaInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )
        EditNumberField(
            label = "Porcentagem da gorjeta",
            value = percentualGorjetaInput,
            onValueChanged = { percentualGorjetaInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )
        RoundTheTipRow(
            arredondar = arredondarGorjeta,
            onArredondarChanged = { arredondarGorjeta = it },
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Button(
            onClick = {
                gorjeta = calcularGorjeta(valorConta, percentualGorjeta, arredondarGorjeta)
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) { Text("Calcular") }
        Text(text = "Gorjeta: $gorjeta", fontSize = 20.sp)
    }
}

@Composable
fun EditNumberField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Composable
fun RoundTheTipRow(
    arredondar: Boolean,
    onArredondarChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Arredondar gorjeta?")
        Switch(checked = arredondar, onCheckedChange = onArredondarChanged)
    }
}

fun calcularGorjeta(
    valorConta: Double,
    percentualGorjeta: Double,
    arredondar: Boolean
): String {
    var gorjeta = (percentualGorjeta / 100) * valorConta
    if (arredondar) gorjeta = ceil(gorjeta)
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(gorjeta)
}