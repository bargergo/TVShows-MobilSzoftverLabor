package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.*

class FakeNetworkInteractor : NetworkInteractor {
    override suspend fun searchShows(keywords: String): List<ShowSearchResult> {
        return listOf(
            ShowSearchResult(
                50.1f,
                ShowSummary(
                    1,
                    "https://www.tvmaze.com/shows/1/under-the-dome",
                    "Under the Dome",
                    "Scripted",
                    "English",
                    listOf("Drama",
                        "Science-Fiction",
                        "Thriller"),
                    "Ended",
                    60,
                    "2013-06-24",
                    "http://www.cbs.com/shows/under-the-dome/",
                    Schedule(
                        "22:00",
                        listOf("Thursday")
                    ),
                    Rating(6.6f),
                    96,
                    Network(
                        2,
                        "CBS",
                        Country(
                            "United States",
                            "US",
                            "America/New_York"
                        )
                    ),
                    null,
                    null,
                    Externals(25988, 264492, "tt1553656"),
                    Image(
                        "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
                        "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg"
                    ),
                    "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>",
                    1617697381,
                    Links(
                        LinksSelf(
                            "https://api.tvmaze.com/shows/1"
                        ),
                        LinksSelf(
                            "https://api.tvmaze.com/episodes/185054"
                        )
                    )
                )
            )
        )
    }

    override suspend fun createShow(data: ShowData) {

    }
}