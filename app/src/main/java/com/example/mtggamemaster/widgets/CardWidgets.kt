package com.example.mtggamemaster.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mtggamemaster.R
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.models.card.Card

@Composable
fun CardWidget(
    modifier: Modifier = Modifier,
    card: Card,
    navController: NavController,

    onFavoriteClick: (String) -> Unit = {},
    onCardClick: (String) -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(false) }
    isFavorite = card.isFavorite

    Card(
        modifier = modifier
            .fillMaxWidth(0.5f)
            .padding(5.dp)
            .clickable { onCardClick(card.id) },

        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(273.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(420.dp),
                    model = card.imageUrl,
                    contentScale = ContentScale.Fit,
                    contentDescription = card.name,
                    placeholder = painterResource(R.drawable.card_placeholder)
                )
                IconButton(onClick = {
                    onFavoriteClick(card.id)
                    isFavorite = !isFavorite
                }) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like!"
                    )
                }
            }

        }
    }
}

@Composable
fun CardWidgetDetail(
    modifier: Modifier = Modifier,
    currentCard: Card,
    navController: NavController
) {
    Column(
        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val infoModifier = Modifier
            .padding(4.dp)

        val infoStyle = TextStyle(
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "This is card ${currentCard.name}",
            fontSize = 24.sp,
            style = TextStyle(
                Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Blue, Color.Magenta)
                )
            )
        )
        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )

        CardWidget(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            card = currentCard,
            navController = navController
        )

        DetailRowSingle(title = "Type", text = "${currentCard.type}")
//        Text(modifier = infoModifier, style = infoStyle, text = "Type: ${currentCard.type}")

        DetailRowSingle(title = "P/T", text = "${currentCard.power} / ${currentCard.toughness}")
//        Text(
//            modifier = infoModifier,
//            style = infoStyle,
//            text = "P/T: ${currentCard.power} / ${currentCard.toughness}"
//        )

        DetailRowSingle(title = "Mana cost", text = "${currentCard.manaCost}")
//        Text(
//            modifier = infoModifier,
//            style = infoStyle,
//            text = "Mana cost: ${currentCard.manaCost}"
//        )

        DetailRowSingle(title = "Rarity", text = "${currentCard.rarity}")
//        Text(modifier = infoModifier, style = infoStyle, text = "Rarity: ${currentCard.rarity}")

        DetailRowSingle(title = "Text", text = "${currentCard.text}")
//        currentCard.text?.let { Text(modifier = infoModifier, text = it) }

        DetailRowSingle(title = "Rulings", text = "")
        DetailRowList(prefix = "-", list = currentCard.rulings)
//        Text(modifier = infoModifier, style = infoStyle, text = "Rulings: ")
//        if (currentCard.rulings != null) {
//            for (rule in currentCard.rulings!!)
//                Text(text = "${rule.key}: ${rule.value}")
//        }

        DetailRowSingle(title = "Set", text = "${currentCard.setName}")
//        Text(modifier = infoModifier, style = infoStyle, text = "Set: ${currentCard.setName}")

        DetailRowSingle(title = "Original Text", text = "${currentCard.originalText}")
//        Text(
//            modifier = infoModifier,
//            style = infoStyle,
//            text = "Original Text: ${currentCard.originalText}"
//        )

        DetailRowSingle(title = "Legalities", text = "")
        DetailRowList(prefix = "-", list = currentCard.legalities)
//        Text(modifier = infoModifier, style = infoStyle, text = "Legalities: ")
//        if (currentCard.legalities != null) {
//            for (legality in currentCard.legalities!!)
//                Text(text = "- ${legality.key}: ${legality.value}")
//        }
    }
}

@Composable
fun DetailRowSingle(title: String, text: String) {
    val rowHeight = 32.dp
    val headerWidth = 128.dp

    Row (
        modifier = Modifier
//            .height(rowHeight)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(4.dp)
    ){
        Text(
            modifier = Modifier
                .size(headerWidth, rowHeight),

            style = TextStyle(
                fontWeight = FontWeight.Bold
            ),

            text = "$title:"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),

            style = TextStyle(
                fontWeight = FontWeight.Normal
            ),

            text = text
        )
    }
}

@Composable
fun DetailRowList(prefix: String, list: Map<String, String>?) {
    if(list == null) return
    for(item in list) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),

            style = TextStyle(
                fontWeight = FontWeight.Normal
            ),

            text = "$prefix ${item.key}: ${item.value}"
        )
    }
}

@Composable
fun CardWidgetGrid(
    cardList: List<Card>,
    navController: NavController,
    innerPadding: PaddingValues
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(innerPadding),
        columns = GridCells.Fixed(2)
    ) {
        items(cardList) { card ->
            CardWidget(
                card = card,
                navController = navController
            ) { cardID ->
                navController.navigate("${Screens.carddetailscreen}/$cardID")
            }
        }
    }
}