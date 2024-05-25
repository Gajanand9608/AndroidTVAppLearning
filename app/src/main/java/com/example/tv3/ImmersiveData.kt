package com.example.tv3


/*data class for immersiveList*/
data class ImmersiveListItem(
    val title: String,
    val description: String,
    val backdrop: String,
    val poster: String,
    val runTime: Int,
    val releaseDate: String,
    val category: String
) {
    fun getHours(): String {
        return "${runTime / 60}h ${runTime % 60}m"
    }

    fun getYear(): String {
        return releaseDate.split("-")[0]
    }
}

fun getImmersiveList(): List<ImmersiveListItem> {
    return list
}

val list = listOf(
    ImmersiveListItem(
        title = "Meg 2: The Trench",
        description = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
        backdrop = "https://image.tmdb.org/t/p/original/qlxy8yo5bcgUw2KAmmojUKp4rHd.jpg",
        poster = "https://image.tmdb.org/t/p/original/8pjWz2lt29KyVGoq1mXYu6Br7dE.jpg",
        runTime = 116,
        releaseDate = "2023-08-02",
        category = "Action/Horror"
    ),
    ImmersiveListItem(
        title = "Indiana Jones and the Dial of Destiny",
        description = "Finding himself in a new era, and approaching retirement, Indy wrestles with fitting into a world that seems to have outgrown him. But as the tentacles of an all-too-familiar evil return in the form of an old rival, Indy must don his hat and pick up his whip once more to make sure an ancient and powerful artifact doesn't fall into the wrong hands.",
        backdrop = "https://image.tmdb.org/t/p/original/b0UUx8OvqQASesc78mXRzvjmxmj.jpg",
        poster = "https://image.tmdb.org/t/p/original/po6AvQRVi01teGlRMX3VYsJ5zuc.jpg",
        runTime = 155,
        releaseDate = "2023-06-28",
        category = "Action/Adventure"
    ),
    ImmersiveListItem(
        title = "Elemental",
        description = "In a city where fire, water, land and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental: how much they have in common.",
        backdrop = "https://image.tmdb.org/t/p/original/wVfnJX7FfUis4Yl6eIgTnYycrGb.jpg",
        poster = "https://image.tmdb.org/t/p/original/uahy4sZXrrYNrQBU7FZEWJAXiZm.jpg",
        runTime = 102,
        releaseDate = "2023-06-14",
        category = "Animation/Family"
    ),
    ImmersiveListItem(
        title = "Barbie",
        description = "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
        backdrop = "https://www.themoviedb.org/t/p/original/lLRrR9gQW2sMddvuc3AnA4JLDkt.jpg",
        poster = "https://www.themoviedb.org/t/p/original/frYbmlPnVwQNr1RrpyDhihPNz3B.jpg",
        runTime = 114,
        releaseDate = "2023-07-19",
        category = "Fantasy/Comedy"
    ),
    ImmersiveListItem(
        title = "The Last Voyage of the Demeter",
        description = "The crew of the merchant ship Demeter attempts to survive the ocean voyage from Carpathia to London as they are stalked each night by a merciless presence onboard the ship.",
        backdrop = "https://www.themoviedb.org/t/p/original/guRsapvkl8cScqVkeKyJn4NptSq.jpg",
        poster = "https://www.themoviedb.org/t/p/original/2xMJ9yAkcxvRbzR8403naynjlcg.jpg",
        runTime = 116,
        releaseDate = "2023-08-02",
        category = "Horror/Thriller"
    ),
    ImmersiveListItem(
        title = "Heart of Stone",
        description = "An intelligence operative for a shadowy global peacekeeping agency races to stop a hacker from stealing its most valuable — and dangerous — weapon.",
        backdrop = "https://www.themoviedb.org/t/p/original/goAzVKxNMUcF4YWLFEwvdA422Nn.jpg",
        poster = "https://www.themoviedb.org/t/p/original/XB5k1KYDr3dnieojeLOgoZbtSH.jpg",
        runTime = 124,
        releaseDate = "2023-08-09",
        category = "Action/Adventure"
    ),
    ImmersiveListItem(
        title = "Spider-Man: Across the Spider-Verse",
        description = "After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse, where he encounters the Spider Society, a team of Spider-People charged with protecting the Multiverse’s very existence. But when the heroes clash on how to handle a new threat, Miles finds himself pitted against the other Spiders and must set out on his own to save those he loves most.",
        backdrop = "https://www.themoviedb.org/t/p/original/kVd3a9YeLGkoeR50jGEXM6EqseS.jpg",
        poster = "https://www.themoviedb.org/t/p/original/nGxUxi3PfXDRm7Vg95VBNgNM8yc.jpg",
        runTime = 140,
        releaseDate = "2023-05-31",
        category = "Animation/Action"
    ),
    ImmersiveListItem(
        title = "Transformers: Rise of the Beasts",
        description = "When a new threat capable of destroying the entire planet emerges, Optimus Prime and the Autobots must team up with a powerful faction known as the Maximals. With the fate of humanity hanging in the balance, humans Noah and Elena will do whatever it takes to help the Transformers as they engage in the ultimate battle to save Earth.",
        backdrop = "https://www.themoviedb.org/t/p/original/p8djosrgWSKh6jQkrWJXzzZRABl.jpg",
        poster = "https://www.themoviedb.org/t/p/original/rkOE88JKK1lHSXFkDNd2zZW81LU.jpg",
        runTime = 127,
        releaseDate = "2023-06-06",
        category = "Action/Science Fiction"
    )
)