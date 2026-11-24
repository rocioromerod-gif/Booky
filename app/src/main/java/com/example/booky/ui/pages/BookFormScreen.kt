package com.example.booky.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenu
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booky.R
import com.example.booky.seeder.GeneroLibro
import com.example.booky.ui.theme.BookyTheme
import com.example.booky.viewmodel.BookFormVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookFormScreen(
    onSaved: () -> Unit,
    modifier: Modifier = Modifier,
    vm: BookFormVM = viewModel()
) {
    val state by vm.uiState.collectAsState()

    //Todo va dentro de una columna
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {
        Spacer(modifier = Modifier.height(38.dp))

            Text(
                text = stringResource(R.string.bienvenido),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = vm::onEmailChange,
            label = { Text(
                text = stringResource(R.string.correo),
                color = MaterialTheme.colorScheme.onTertiary
                ) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = state.errorEmail != null,
            supportingText = { state.errorEmail?.let { Text(stringResource(it)) } },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = vm::onPasswordChange,
            label = { Text(
                text = stringResource(R.string.contrasena),
                color = MaterialTheme.colorScheme.onTertiary
                ) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            //Opcion para que no se vea la contraseña
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isError = state.errorPassword != null,
            supportingText = { state.errorPassword?.let { Text(stringResource(it)) } },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        ExposedDropdownMenuBox(
            expanded = state.expanded,
            onExpandedChange = vm::onExpandedChange
        ) {
            OutlinedTextField(
                value = state.genero.name,
                onValueChange = {},
                label = { Text(
                    text = stringResource(R.string.genero_favorito),
                    color = MaterialTheme.colorScheme.onTertiary
                    ) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(state.expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )

            ExposedDropdownMenu(
                expanded = state.expanded,
                onDismissRequest = { vm.onExpandedChange(false) }
            ) {
                GeneroLibro.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.name, style = MaterialTheme.typography.bodyLarge) },
                        onClick = { vm.onGeneroChange(option) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = state.aceptaTerminos,
                    onCheckedChange = vm::onTerminosChange
                )
                Text(
                    text = stringResource(R.string.terminos),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            state.errorTerminos?.let {
                Text(
                    text = stringResource(it),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(38.dp))

        Button(
            onClick = {
                if (vm.onSave()) onSaved()
            },

            enabled = state.isValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onTertiary,
                contentColor = Color(0xFF272324),
                disabledContainerColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.3f),
                disabledContentColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.55f)
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
            text= stringResource(R.string.registrarme),
            style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookFormScreenPreview() {
    BookyTheme {
        BookFormScreen(onSaved = {})
    }
}