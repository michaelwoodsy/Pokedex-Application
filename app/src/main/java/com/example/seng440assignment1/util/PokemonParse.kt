package com.example.seng440assignment1.util

import androidx.compose.ui.graphics.Color
import com.example.seng440assignment1.R
import com.example.seng440assignment1.data.remote.responses.Stat
import com.example.seng440assignment1.data.remote.responses.Type
import com.example.seng440assignment1.ui.theme.*
import java.util.*

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseTypeToName(type: Type): Int {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> R.string.normal
        "fire" -> R.string.fire
        "water" -> R.string.water
        "electric" -> R.string.electric
        "grass" -> R.string.grass
        "ice" -> R.string.ice
        "fighting" -> R.string.fighting
        "poison" -> R.string.poison
        "ground" -> R.string.ground
        "flying" -> R.string.flying
        "psychic" -> R.string.psychic
        "bug" -> R.string.bug
        "rock" -> R.string.rock
        "ghost" -> R.string.ghost
        "dragon" -> R.string.dragon
        "dark" -> R.string.dark
        "steel" -> R.string.steel
        "fairy" -> R.string.fairy
        else -> R.string.normal
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): Int {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> R.string.hp
        "attack" -> R.string.attack
        "defense" -> R.string.defense
        "special-attack" -> R.string.special_attack
        "special-defense" -> R.string.special_defense
        "speed" -> R.string.speed
        else -> R.string.hp
    }
}

fun parseNameToCleanName(name: String): String {
    return when(name.lowercase(Locale.ROOT)) {
        "nidoran-f" -> "Nidoran♀"
        "nidoran-m" -> "Nidoran♂"
        "mr-mime" -> "Mr. Mime"
        "ho-oh" -> "Ho-Oh"
        "deoxys-normal" -> "Deoxys"
        "wormadam-plant" -> "Wormadam"
        "mime-jr" -> "Mime Jr."
        "porygon-z" -> "Porygon-Z"
        "giratina-altered" -> "Giratina"
        "shaymin-land" -> "Shaymin"
        "basculin-red-striped" -> "Basculin"
        "darmanitan-standard" -> "Darmanitan"
        "tornadus-incarnate"-> "Tornadus"
        "thundurus-incarnate" -> "Thundurus"
        "landorus-incarnate" -> "Landorus"
        "keldeo-ordinary" -> "Keldeo"
        "meloetta-aria" -> "Meloetta"
        "meowstic-male" -> "Meowstic"
        "aegislash-shield" -> "Aegislash"
        "pumpkaboo-average" -> "Pumpkaboo"
        "gourgeist-average" -> "Gourgeist"
        "zygarde-50" -> "Zygarde"
        "oricorio-baile" -> "Oricorio"
        "lycanroc-midday" -> "Lycanroc"
        "wishiwashi-solo" -> "Wishiwashi"
        "type-null" -> "Type: Null"
        "minior-red-meteor" -> "Minior"
        "mimikyu-disguised" -> "Mimikyu"
        "tapu-koko" -> "Tapu Koko"
        "tapu-lele" -> "Tapu Lele"
        "tapu-bulu" -> "Tapu Bulu"
        "tapu-fini" -> "Tapu Fini"
        "toxtricity-amped" -> "Toxtricity"
        "mr-rime" -> "Mr. Rime"
        "eiscue-ice" -> "Eiscue"
        "indeedee-male" -> "Indeedee"
        "morpeko-full-belly" -> "Morpeko"
        "urshifu-single-strike" -> "Urshifu"
        else -> name
    }
}