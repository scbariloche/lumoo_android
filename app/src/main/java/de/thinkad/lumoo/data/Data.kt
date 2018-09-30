package de.thinkad.lumoo.data

import de.thinkad.lumoo.R
import de.thinkad.lumoo.business.interfaces.Filterable
import java.io.Serializable
import java.util.*

/**
 * Created by andreas on 11.06.2018.
 */

val res_chat = 11
val req_chat = 10

val req_profile = 20
val res_profile = 21

data class User(var id: Long,
                var profilepic: Image,
                var name: String,
                var first_name: String,
                var spontaneous: Boolean,
                var taken: Boolean,
                var about: String,
                var what: String,
                var female: Boolean,
                var interests: MutableList<Interest>,
                var images: MutableList<Image>,
                var distance: Float,
                var trusted: Boolean = false,
                var chats: MutableList<Chat> = mutableListOf()) : Serializable, Filterable {
    override fun filterableString(): String {
        return "$name$first_name"
    }
}

data class Interest(var id: Long,
                    var name: String) : Serializable


data class Image(var id: Long,
                 var uri: Int) : Serializable


data class Chat(var user: User,
                var message: String,
                var timestamp: Date,
                var sent: Boolean) : Serializable


data class Event(
        var id: Long,
        var title: String,
        var description: String,
        var date: Date,
        var place: Int,
        var regularly: Boolean,
        var frequency: Long,
        var public: Boolean,
        var host: User,
        var image: Image,
        var participants: MutableList<User>
) : Serializable


var test_interesets = mutableListOf<Interest>(
        Interest(1, "Kunst"),
        Interest(2, "Asien"),
        Interest(3, "Kochen"),
        Interest(4, "Fahrrad"),
        Interest(5, "Kino"),
        Interest(6, "Spiritualität"),
        Interest(7, "Reisen"),
        Interest(8, "Essen")
)
var test_images = mutableListOf<Image>(
        Image(1, R.drawable.dog),
        Image(2, R.drawable.sailing),
        Image(3, R.drawable.flower),
        Image(4, R.drawable.dog),
        Image(5, R.drawable.sailing)
)

var test_user = User(
        2,
        Image(0, R.drawable.profilbild),
        "Griesbeck",
        "Clara",
        false,
        true,
        "Immer am lachen, liebe Blumen, male gerne Aquarell und spreche gerne Englisch",
        "Ich suche nette, neue Freunde um Ausstellungen der Kunst oder  der asiatischen Kultur zu besuchen. Gerne auch um ausländische Restaurants zu besuchen.",
        true,
        mutableListOf<Interest>(test_interesets[2]),
        test_images,
        0.4f)


var test_users = mutableListOf<User>(
        User(
                0,
                Image(0, R.drawable.profilbild2),
                "Blank",
                "Babsi",
                false,
                true,
                "Ich mache für mein Leben gerne Rad- und Wandertouren, bin sportlich und mache gerne schlechte Witze.",
                "Ich suche sportliche, neue Freunde um lange Touren zu machen und später bei einem gemütlichen Bier zu entspannen",

                true,
                mutableListOf<Interest>(
                        test_interesets[1],
                        test_interesets[5],
                        test_interesets[6],
                        test_interesets[2],
                        test_interesets[0],
                        test_interesets[3],
                        test_interesets[4]
                ),
                test_images,
                3.7f),
        User(
                1,
                Image(0, R.drawable.profilbild3),
                "Perkounig",
                "Hildegard",
                true,
                false,
                "Immer am lachen, liebe Blumen, male gerne Aquarell und spreche gerne Englisch",
                "Ich suche nette, neue Freunde um Ausstellungen der Kunst oder  der asiatischen Kultur zu besuchen. Gerne auch um ausländische Restaurants zu besuchen.",
                true,
                mutableListOf<Interest>(test_interesets[2], test_interesets[1]),
                test_images, 5.1f),
        User(
                2,
                Image(0, R.drawable.profilbild4),
                "König",
                "Hans",
                true,
                true,
                "Ich mache für mein Leben gerne Rad- und Wandertouren, bin sportlich und mache gerne schlechte Witze.",
                "Ich suche sportliche, neue Freunde um lange Touren zu machen und später bei einem gemütlichen Bier zu entspannen",

                false,

                mutableListOf<Interest>(test_interesets[2], test_interesets[0], test_interesets[4]),
                test_images,
                1.1f,
                true
        )
)
var test_events = mutableListOf<Event>(
        Event(
                1,
                "Kinoabend",
                "Der neueste Blockbuster",
                Date(System.currentTimeMillis()),
                123,
                false,
                0,
                true,
                test_users[0],
                Image(0, R.drawable.ic_theaters_dark_24dp),
                test_users
        ),
        Event(
                2,
                "Fahrradtour",
                "die Donau entlang",
                Date(System.currentTimeMillis() + 2628000000),
                456,
                false,
                0,
                true,
                test_users[1],
                Image(0, R.drawable.ic_bike_dark_24dp),
                test_users
        ),
        Event(
                3,
                "Wanderung",
                "die Donau entlang",
                Date(System.currentTimeMillis() + 2628000000),
                456,
                false,
                0,
                true,
                test_users[1],
                Image(0, R.drawable.ic_walk_dark_24dp),
                test_users
        )
)

var test_chats1 = mutableListOf<Chat>(
        Chat(

                test_users[0],
                "Hallo Babsi!",
                Date(System.currentTimeMillis() - 82800000),
                true
        ),
        Chat(

                test_users[0],
                "Hallo Clara, \nwie gehts? Dein Aquarell Bild ist super schön, was du hochgeladen hast!",
                Date(System.currentTimeMillis() - 2000),
                false
        ),
        Chat(test_users[0],
                "Dankesehr! Ich male seit einigen Jahren schon und freue mich, " +
                        "wenn Leute meine Bilder  mögen.\nMir gehts super und selbst?\n\nDein Hund " +
                        "sieht sehr nach Urlaub aus, wo wart ihr denn da am Meer?",
                Date(System.currentTimeMillis()),
                true
        ))

var test_chats2 = mutableListOf<Chat>(
        Chat(

                test_users[0],
                "Hallo Hildegard!",
                Date(System.currentTimeMillis() - 82800000),
                true
        ),
        Chat(
                test_users[0],
                "Hallo Clara, \nwie gehts? Vorne weg: Du darfst mich Hilde nennen",
                Date(System.currentTimeMillis() - 2000),
                false
        ),
        Chat(test_users[0],
                "Das ist nett! \nIch habe gesehen, dass du gerne kochst. Hast du schon vo der aktuellen Kochmesse gehört?",
                Date(System.currentTimeMillis()),
                true
        ))

var test_chats3 = mutableListOf<Chat>(
        Chat(

                test_users[0],
                "Hallo Hans!\n Ich  finde dein Profilbild richtig ansprechend",
                Date(System.currentTimeMillis() - 82800000),
                true
        ),
        Chat(
                test_users[0],
                "Hallo Clara, \nwie gehts? Vielen Dank. Ich fotografiere für mein Leben gern. Das Portrait habe ich mithilfe eines Stativs in meinem Studio gemacht",
                Date(System.currentTimeMillis() - 2000),
                false
        ),
        Chat(test_users[0],
                "Das hört sich wahnsinnig interessant an",
                Date(System.currentTimeMillis()),
                true
        ))
val test_chats = mutableListOf<MutableList<Chat>>(test_chats1, test_chats2, test_chats3)