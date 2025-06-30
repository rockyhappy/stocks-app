package com.devrachit.groww.presentation.screens.search.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp


@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onSearchClick: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.card_elevated),
            unfocusedContainerColor = colorResource(R.color.card_elevated),
            disabledContainerColor = colorResource(R.color.card_elevated),
            errorContainerColor = colorResource(R.color.card_elevated),
            focusedLabelColor = colorResource(R.color.black),
            unfocusedLabelColor = colorResource(R.color.black),
            disabledLabelColor = colorResource(R.color.black),
            focusedBorderColor = colorResource(R.color.black),
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            cursorColor = colorResource(R.color.black)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.sdp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
            }
        )
    )
}