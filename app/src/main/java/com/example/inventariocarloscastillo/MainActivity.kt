package com.example.inventariocarloscastillo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// 🆕 Imports necesarios para UI moderna
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.inventariocarloscastillo.ui.theme.InventarioCarlosCastilloTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            InventarioCarlosCastilloTheme {

                // Scaffold mantiene estructura base de la app
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Reemplazamos Greeting por nuestra pantalla real
                    InventarioScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Modelo de datos (sin cambios, pero ahora se usa en UI moderna)
data class Producto(
    val nombre: String,
    val categoria: String,
    val precio: Double
)

@Composable
fun InventarioScreen(modifier: Modifier = Modifier) {

    // Estados (base del estado compuesto)
    var textoBusqueda by remember { mutableStateOf("") }
    var textoFiltrado by remember { mutableStateOf("") } // 🆕 usado con debounce
    var filtroActual by remember { mutableStateOf("Todas") }
    var ordenarPorPrecio by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val categorias = listOf("Todas", "Smartphones", "Computadoras", "Accesorios")

    // Debounce (evita filtrar en cada letra inmediatamente)
    LaunchedEffect(textoBusqueda) {
        delay(300)
        textoFiltrado = textoBusqueda
    }

    // Datos de ejemplo
    val todosLosProductos = listOf(
        Producto("Samsung S23", "Smartphones", 900.0),
        Producto("iPhone 14", "Smartphones", 1200.0),
        Producto("Laptop HP", "Computadoras", 800.0),
        Producto("Mouse Logitech", "Accesorios", 25.0),
        Producto("Teclado Redragon", "Accesorios", 45.0)
    )

    // FILTRO COMPUESTO + ORDENAMIENTO
    val listaMostrada = todosLosProductos
        .filter { producto ->

            val coincideCategoria =
                filtroActual == "Todas" || producto.categoria == filtroActual

            val coincideNombre =
                producto.nombre.contains(textoFiltrado, ignoreCase = true)

            coincideCategoria && coincideNombre
        }
        .let { lista ->
            if (ordenarPorPrecio) lista.sortedBy { it.precio } else lista
        }

    // UI PRINCIPAL
    Column(modifier = modifier) {

        // Título de la app
        Text(
            text = "Inventario",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        // Buscador con icono
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            label = { Text("Buscar producto...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            },
            singleLine = true
        )

        // ComboBox mejorado
        Box(modifier = Modifier.padding(16.dp)) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Categoría: $filtroActual")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categorias.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria) },
                        onClick = {
                            filtroActual = categoria
                            expanded = false
                        }
                    )
                }
            }
        }

        // Switch de ordenamiento
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Ordenar por precio")
            Switch(
                checked = ordenarPorPrecio,
                onCheckedChange = { ordenarPorPrecio = it }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Lista o mensaje vacío
        if (listaMostrada.isEmpty()) {

            // Mensaje mejorado
            Text(
                text = "😕 No se encontraron productos",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            LazyColumn {
                items(listaMostrada) { producto ->

                    // 🆕 Usamos Card en lugar de Text simple
                    ProductoCard(producto)
                }
            }
        }
    }
}

// Componente visual de producto (ANTES NO EXISTÍA)
@Composable
fun ProductoCard(producto: Producto) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),

        // Elevación para efecto moderno
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            // Nombre destacado
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Categoría con color del tema
            Text(
                text = producto.categoria,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Precio grande y llamativo
            Text(
                text = "$${producto.precio}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}