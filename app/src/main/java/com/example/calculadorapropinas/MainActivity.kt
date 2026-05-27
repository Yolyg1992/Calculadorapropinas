package com.example.calculadorapropinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CalculadoraPropinas()
                }
            }
        }
    }
}

@Composable
fun CalculadoraPropinas() {
    val amount = remember { mutableStateOf("") }
    val percent = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }
    val tipAmount = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text(
            text = "Calculadora de Propinas",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF6200EE)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = amount.value,
            onValueChange = { amount.value = it },
            label = { Text("Monto de la cuenta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = percent.value,
            onValueChange = { percent.value = it },
            label = { Text("Porcentaje de propina (%)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val a = amount.value.toDoubleOrNull()
                val p = percent.value.toDoubleOrNull()
                if (a != null && p != null) {
                    val tip = (a * p) / 100
                    val total = a + tip
                    tipAmount.value = String.format(Locale.US, "%.2f", tip)
                    result.value = String.format(Locale.US, "%.2f", total)
                } else {
                    result.value = "Error"
                    tipAmount.value = "Error"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Propina", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (result.value.isNotEmpty() && result.value != "Error") {
            Text(
                text = "Total a pagar:",
                fontSize = 20.sp,
                color = Color.Gray
            )
            Text(
                text = "$${result.value}",
                fontSize = 36.sp,
                color = Color(0xFF4CAF50)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Propina:",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "$${tipAmount.value}",
                fontSize = 24.sp,
                color = Color(0xFFFF9800)
            )
        } else if (result.value == "Error") {
            Text(
                text = " Error: Ingresa números válidos",
                color = Color.Red,
                fontSize = 16.sp
            )
        }
    }
}