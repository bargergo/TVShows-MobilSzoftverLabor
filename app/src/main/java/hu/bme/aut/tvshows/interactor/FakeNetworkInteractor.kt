package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.ShowData

class FakeNetworkInteractor : NetworkInteractor {
    override suspend fun search(keywords: String): String {
        return """
            [
              {
                "score": 5.5056925,
                "show": {
                  "id": 1859,
                  "url": "https://www.tvmaze.com/shows/1859/lucifer",
                  "name": "Lucifer",
                  "type": "Scripted",
                  "language": "English",
                  "genres": [
                    "Drama",
                    "Crime",
                    "Supernatural"
                  ],
                  "status": "Running",
                  "runtime": null,
                  "premiered": "2016-01-25",
                  "officialSite": "https://www.netflix.com/title/80057918",
                  "schedule": {
                    "time": "",
                    "days": []
                  },
                  "rating": {
                    "average": 8.4
                  },
                  "weight": 99,
                  "network": null,
                  "webChannel": {
                    "id": 1,
                    "name": "Netflix",
                    "country": null
                  },
                  "dvdCountry": null,
                  "externals": {
                    "tvrage": 45076,
                    "thetvdb": 295685,
                    "imdb": "tt4052886"
                  },
                  "image": {
                    "medium": "https://static.tvmaze.com/uploads/images/medium_portrait/265/664551.jpg",
                    "original": "https://static.tvmaze.com/uploads/images/original_untouched/265/664551.jpg"
                  },
                  "summary": "<p>The Devil has come to Los Angeles…</p><p>Based upon the characters created by Neil Gaiman, Sam Kieth and Mike Dringenberg for DC Entertainment's Vertigo imprint, <b>Lucifer</b> is the story of the original fallen angel. Bored and unhappy as the Lord of Hell, Lucifer Morningstar has abandoned his throne and retired to L.A., where he owns Lux, an upscale nightclub.</p><p>Charming, charismatic and devilishly handsome, Lucifer is enjoying his retirement, indulging in a few of his favorite things--wine, women and song-–when a beautiful pop star is brutally murdered outside of Lux. For the first time in roughly 10 billion years, he feels something awaken deep within him as a result of this murder. Compassion? Sympathy? The very thought disturbs him--as well as his best friend and confidante, Mazkeen aka Maze, a fierce demon in the form of a beautiful young woman.</p>",
                  "updated": 1617544797,
                  "_links": {
                    "self": {
                      "href": "https://api.tvmaze.com/shows/1859"
                    },
                    "previousepisode": {
                      "href": "https://api.tvmaze.com/episodes/1774443"
                    },
                    "nextepisode": {
                      "href": "https://api.tvmaze.com/episodes/1790681"
                    }
                  }
                }
              },
              {
                "score": 3.7588198,
                "show": {
                  "id": 46162,
                  "url": "https://www.tvmaze.com/shows/46162/lucifers-kingdom",
                  "name": "Lucifer's Kingdom",
                  "type": "Scripted",
                  "language": "Arabic",
                  "genres": [
                    "Drama",
                    "Thriller"
                  ],
                  "status": "To Be Determined",
                  "runtime": 45,
                  "premiered": "2020-01-31",
                  "officialSite": "https://shahid.mbc.net/ar/series/%D9%85%D9%85%D9%84%D9%83%D8%A9-%D8%A5%D8%A8%D9%84%D9%8A%D8%B3/series-387088",
                  "schedule": {
                    "time": "",
                    "days": [
                      "Monday",
                      "Tuesday",
                      "Wednesday",
                      "Thursday",
                      "Sunday"
                    ]
                  },
                  "rating": {
                    "average": null
                  },
                  "weight": 77,
                  "network": null,
                  "webChannel": {
                    "id": 379,
                    "name": "Shahid",
                    "country": null
                  },
                  "dvdCountry": null,
                  "externals": {
                    "tvrage": null,
                    "thetvdb": 376667,
                    "imdb": "tt11722830"
                  },
                  "image": {
                    "medium": "https://static.tvmaze.com/uploads/images/medium_portrait/299/749177.jpg",
                    "original": "https://static.tvmaze.com/uploads/images/original_untouched/299/749177.jpg"
                  },
                  "summary": "<p><b>Lucifer's Kingdom </b>follows the lives of the residents of the Haddocka neighbourhood by revealing their relationships and details of their daily lives during the January 25th revolution.</p>",
                  "updated": 1615581783,
                  "_links": {
                    "self": {
                      "href": "https://api.tvmaze.com/shows/46162"
                    },
                    "previousepisode": {
                      "href": "https://api.tvmaze.com/episodes/1916276"
                    }
                  }
                }
              },
              {
                "score": 3.6431558,
                "show": {
                  "id": 5187,
                  "url": "https://www.tvmaze.com/shows/5187/comet-lucifer",
                  "name": "Comet Lucifer",
                  "type": "Animation",
                  "language": "Japanese",
                  "genres": [
                    "Adventure",
                    "Anime",
                    "Fantasy",
                    "Science-Fiction"
                  ],
                  "status": "Ended",
                  "runtime": 25,
                  "premiered": "2015-10-04",
                  "officialSite": "http://comet-lucifer.jp/",
                  "schedule": {
                    "time": "22:30",
                    "days": [
                      "Sunday"
                    ]
                  },
                  "rating": {
                    "average": null
                  },
                  "weight": 0,
                  "network": {
                    "id": 132,
                    "name": "Tokyo MX",
                    "country": {
                      "name": "Japan",
                      "code": "JP",
                      "timezone": "Asia/Tokyo"
                    }
                  },
                  "webChannel": null,
                  "dvdCountry": null,
                  "externals": {
                    "tvrage": null,
                    "thetvdb": 299489,
                    "imdb": "tt5238484"
                  },
                  "image": {
                    "medium": "https://static.tvmaze.com/uploads/images/medium_portrait/21/54658.jpg",
                    "original": "https://static.tvmaze.com/uploads/images/original_untouched/21/54658.jpg"
                  },
                  "summary": "<p>On the planet Gift there are precious crystals called Giftjium buried in the earth. A young man named Sōgo lives in Garden Indigo, a prosperous mining town. Sōgo's hobby is to collect rare crystals. One day, he gets wrapped up in a riot caused by his classmates Kaon, Roman, and Otto, and he loses his way until he finds a lake deep under the ground's mining ruins. There, he meets a mysterious girl with blue hair and red eyes that gaze straight ahead. The story follows the adventure of what happens after this meeting.</p><p>(Source.ANN)</p>",
                  "updated": 1526311796,
                  "_links": {
                    "self": {
                      "href": "https://api.tvmaze.com/shows/5187"
                    },
                    "previousepisode": {
                      "href": "https://api.tvmaze.com/episodes/384356"
                    }
                  }
                }
              },
              {
                "score": 2.5614831,
                "show": {
                  "id": 15718,
                  "url": "https://www.tvmaze.com/shows/15718/the-devil",
                  "name": "The Devil",
                  "type": "Scripted",
                  "language": "Korean",
                  "genres": [
                    "Drama",
                    "Crime",
                    "Thriller"
                  ],
                  "status": "Ended",
                  "runtime": 65,
                  "premiered": "2007-03-21",
                  "officialSite": null,
                  "schedule": {
                    "time": "21:55",
                    "days": [
                      "Wednesday",
                      "Thursday"
                    ]
                  },
                  "rating": {
                    "average": null
                  },
                  "weight": 0,
                  "network": {
                    "id": 128,
                    "name": "KBS2",
                    "country": {
                      "name": "Korea, Republic of",
                      "code": "KR",
                      "timezone": "Asia/Seoul"
                    }
                  },
                  "webChannel": null,
                  "dvdCountry": null,
                  "externals": {
                    "tvrage": null,
                    "thetvdb": 188821,
                    "imdb": "tt1820124"
                  },
                  "image": {
                    "medium": "https://static.tvmaze.com/uploads/images/medium_portrait/53/132663.jpg",
                    "original": "https://static.tvmaze.com/uploads/images/original_untouched/53/132663.jpg"
                  },
                  "summary": "<p>When Homicide Detective Kang Oh Soo hit a dead end in his investigation of two seemly unrelated murders, except for the Tarot cards left behind at both crime scenes, he sought help from Seo Hae In, a woman with Token-object reading ability.</p>",
                  "updated": 1589557744,
                  "_links": {
                    "self": {
                      "href": "https://api.tvmaze.com/shows/15718"
                    },
                    "previousepisode": {
                      "href": "https://api.tvmaze.com/episodes/712687"
                    }
                  }
                }
              }
            ]
        """.trimIndent()
    }

    override suspend fun createShow(data: ShowData) {

    }
}