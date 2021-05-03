package hu.bme.aut.tvshows.mock

import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.model.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class FakeNetworkInteractor @Inject constructor() : NetworkInteractor {
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
                    LocalDate.parse("2013-06-24", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
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

    override suspend fun getShow(
        id: Long,
        includeSeasons: Boolean,
        includeCast: Boolean
    ): ShowDetails {
        TODO("Not yet implemented")
    }

    override suspend fun updateShow(id: Long, data: ShowData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteShow(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun createShow(data: ShowData) {

    }

    override suspend fun getEpisodes(seasonId: Long): List<Episode> {
        TODO("Not yet implemented")
    }

    override suspend fun createEpisode(seasonId: Long, data: EpisodeData) {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisode(episodeId: Long): Episode {
        TODO("Not yet implemented")
    }

    override suspend fun updateEpisode(id: Long, data: EpisodeData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEpisode(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getSeasons(showId: Long): List<Season> {
        TODO("Not yet implemented")
    }

    override suspend fun createSeason(showId: Long, data: SeasonData) {
        TODO("Not yet implemented")
    }

    override suspend fun getSeason(seasonId: Long): Season {
        TODO("Not yet implemented")
    }

    override suspend fun updateSeason(seasonId: Long, data: SeasonData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSeason(seasonId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getCast(showId: Long): List<Cast> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCast(showId: Long, data: List<CastData>) {
        TODO("Not yet implemented")
    }

    override suspend fun getPerson(id: Long): Person {
        TODO("Not yet implemented")
    }

    override suspend fun updatePerson(id: Long, data: PersonData) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePerson(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun createPerson(data: PersonData) {
        TODO("Not yet implemented")
    }

    override suspend fun searchPeople(keywords: String): List<PersonSearchResult> {
        TODO("Not yet implemented")
    }
}