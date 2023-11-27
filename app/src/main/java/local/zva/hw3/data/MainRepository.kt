package local.zva.hw3.data

import local.zva.hw3.R
import local.zva.hw3.domain.Film

class MainRepository {
    val filmDataBase = listOf(
        Film("Prazske noci", R.drawable.pst_1_pn, "Что-то там про Прагу.", 7.7f),
        Film("Santo el enmascarado de plata y Blue Demon contra los monstruos",
            R.drawable.pst_2_sbvm,
            "To foil his plan for world domination, wrestling superheroes El Santo " +
                    "and Blue Demon battle the mad Dr. Halder and his army of reanimated monsters."),
        Film("The Maze", R.drawable.pst_3_m, "Шотландия, Замок, Лабиринт из кустов ..etc"),
        Film("Forbidden Planet", R.drawable.pst_4_fp, "Sci-fi 56го года)", 4.6f),
        Film("Colossus: The Forbin Project", R.drawable.pst_5_c,
            "Thinking this will prevent war, the US government gives an impenetrable " +
                    "supercomputer total control over launching nuclear missiles." +
                    " But what the computer does with the power is unimaginable to its creators."),
        Film("Tanin no kao", R.drawable.pst_6_af, "A businessman with a disfigured face obtains a lifelike mask from his doctor, but the mask starts altering his personality."),
        Film("Invasión", R.drawable.pst_7_i, "Вторжение..", 9.8f),
        Film("Дорога к звездам", R.drawable.pst_8_rs, "Советская НФ", 7.2f),
    )
}