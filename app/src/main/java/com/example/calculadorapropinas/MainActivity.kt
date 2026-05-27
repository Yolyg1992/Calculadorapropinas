package com.example.calculadorapropinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculatorApp()
                }
            }
        }
    }
}

@Composable
fun TipCalculatorApp() {
    var amountInput by remember { mutableStateOf("") }
    var tipPercentageInput by remember { mutableStateOf("") }
    var totalResult by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculadora de Propinas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Monto de la Cuenta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tipPercentageInput,
            onValueChange = { tipPercentageInput = it },
            label = { Text("Porcentaje (%)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val billAmount = amountInput.toDoubleOrNull()
                val tipPercent = tipPercentageInput.toDoubleOrNull()

                if (billAmount != null && tipPercent != null) {
                    val tipAmount = (billAmount * tipPercent) / 100
                    val total = billAmount + tipAmount
                    totalResult = "Total a pagar: $${"%.2f".format(total)} \n (Propina: $${"%.2f".format(tipAmount)})"
                } else {
                    totalResult = "Error: Ingresa números válidos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Propina")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = totalResult,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TipCalculatorApp()
    }
}