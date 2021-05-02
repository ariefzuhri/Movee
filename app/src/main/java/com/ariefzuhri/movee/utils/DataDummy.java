package com.ariefzuhri.movee.utils;

import com.ariefzuhri.movee.data.source.remote.entity.AiredDateEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CastEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CreditsEntity;
import com.ariefzuhri.movee.data.source.remote.entity.CrewEntity;
import com.ariefzuhri.movee.data.source.local.entity.GenreEntity;
import com.ariefzuhri.movee.data.source.remote.entity.MediaEntity;
import com.ariefzuhri.movee.data.source.remote.entity.StudioEntity;
import com.ariefzuhri.movee.data.source.remote.entity.TrailerEntity;
import com.ariefzuhri.movee.data.source.remote.response.CastItem;
import com.ariefzuhri.movee.data.source.remote.response.CreditsResponse;
import com.ariefzuhri.movee.data.source.remote.response.CrewItem;
import com.ariefzuhri.movee.data.source.remote.response.GenreItem;
import com.ariefzuhri.movee.data.source.remote.response.GenresResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.MovieItem;
import com.ariefzuhri.movee.data.source.remote.response.MovieResponse;
import com.ariefzuhri.movee.data.source.remote.response.MultiSearchResponse;
import com.ariefzuhri.movee.data.source.remote.response.ProductionCompanyItem;
import com.ariefzuhri.movee.data.source.remote.response.SearchResultItem;
import com.ariefzuhri.movee.data.source.remote.response.TVDetailsResponse;
import com.ariefzuhri.movee.data.source.remote.response.TVItem;
import com.ariefzuhri.movee.data.source.remote.response.TVResponse;
import com.ariefzuhri.movee.data.source.remote.response.VideoItem;
import com.ariefzuhri.movee.data.source.remote.response.VideosResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_MOVIE;
import static com.ariefzuhri.movee.utils.Constants.MEDIA_TYPE_TV;

public class DataDummy {
    public static CreditsEntity generateDummyMovieCredits(){
        List<CastEntity> castList = new ArrayList<>();
        castList.add(new CastEntity(1835721,
                "Manaka Iwami",
                "5a32b107c3a3680b881a5782",
                "/zJgSy0mSBVvRbR44Bub8NPUlNeS.jpg",
                "Acting",
                "Maquia (voice)"
        ));
        castList.add(new CastEntity(19588,
                "Miyu Irino",
                "5a32b115925141547201908f",
                "/dcZ4IJX8CBcJzxy8hhKFXv59LDE.jpg",
                "Acting",
                "Erial (voice)"
        ));
        castList.add(new CastEntity(144655,
                "Tomokazu Sugita",
                "5a32b1a0c3a36814ab01b6f4",
                "/tW9VGnxmdhy3XTWfssk8ySrDu7K.jpg",
                "Acting",
                "Isol (voice)"
        ));
        castList.add(new CastEntity(1253008,
                "Yoshimasa Hosoya",
                "5a32b1c9925141032c1a4b5a",
                "/r90gExomlgr7IEt3vZ1Y0xYkXHT.jpg",
                "Acting",
                "Lang (voice)"
        ));
        castList.add(new CastEntity(186341,
                "Miyuki Sawashiro",
                "5a32b890c3a36814ab01bdf6",
                "/o0ZlluSQHTyaKuFubRtW7rPxQBW.jpg",
                "Acting",
                "Rashine (voice)"
        ));
        castList.add(new CastEntity(1241566,
                "Rina Sato",
                "5a32b8a2925141547501997e",
                "/q1VWJEI3b386hEcYzzbiN4KmoPn.jpg",
                "Acting",
                "Mido (voice)"
        ));
        castList.add(new CastEntity(1254671,
                "Ai Kayano",
                "5a32b8b4925141547201979b",
                "/l8ywp387rfdAS7mOExe3cEZnsk0.jpg",
                "Acting",
                "Leilia (voice)"
        ));
        castList.add(new CastEntity(84505,
                "Hiroaki Hirata",
                "5a32b8c4c3a368148f018d2d",
                "/wbirlbjye7mfWkaCm0Jy4b2KAdI.jpg",
                "Acting",
                "Baro (voice)"
        ));
        castList.add(new CastEntity(1072774,
                "Yoko Hikasa",
                "5a32b8d20e0a264ccd1a6559",
                "/e6I7r45YZXgvlEVSGR3L14tSl87.jpg",
                "Acting",
                "Tita (voice)"
        ));
        castList.add(new CastEntity(151182,
                "Misaki Kuno",
                "5a32b8ecc3a36814ab01be5b",
                "/6qslBrgao1mthGJK3M9keUYyMIw.jpg",
                "Acting",
                "Medmel (voice)"
        ));
        castList.add(new CastEntity(149894,
                "Yuki Kaji",
                "5a680fc99251411bbb004405",
                "/8wKdPV11IwowfwoqGqMMNt9hmp6.jpg",
                "Acting",
                "Clear (voice)"
        ));

        List<CrewEntity> crewList = new ArrayList<>();
        crewList.add(new CrewEntity(57304,
                "Kenji Kawai",
                "59be9d7bc3a368301f000580",
                "/l6GYinTkWfH0tKMv2WnExwU1Yft.jpg",
                "Music"
        ));
        crewList.add(new CrewEntity(119240,
                "Kazuhiro Wakabayashi",
                "59be9d85c3a3682feb0005d6",
                null,
                "Sound Director"
        ));
        crewList.add(new CrewEntity(1255613,
                "Kenji Horikawa",
                "5a32b922925141547e019a7f",
                null,
                "Producer"
        ));
        crewList.add(new CrewEntity(1257117,
                "Mari Okada",
                "5a8d8c3e0e0a267c7400c336",
                "/mfq0VCH3tWJGOUGk1ivtyBH1wz3.jpg",
                "Director"
        ));
        crewList.add(new CrewEntity(1257117,
                "Mari Okada",
                "5a32afeb9251415472018f72",
                "/mfq0VCH3tWJGOUGk1ivtyBH1wz3.jpg",
                "Writer"
        ));
        crewList.add(new CrewEntity(1257117,
                "Mari Okada",
                "59be9d68c3a3682ff8000601",
                "/mfq0VCH3tWJGOUGk1ivtyBH1wz3.jpg",
                "Script"
        ));
        crewList.add(new CrewEntity(1680861,
                "Kazuki Higashiji",
                "5a32b9369251415472019829",
                null,
                "Art Direction"
        ));

        return new CreditsEntity(476292,
                castList,
                crewList
        );
    }

    public static MediaEntity generateDummyMovieDetails(){
        List<GenreEntity> genreList = new ArrayList<>();
        genreList.add(new GenreEntity(16, "Animation"));
        genreList.add(new GenreEntity(14, "Fantasy"));
        genreList.add(new GenreEntity(10749, "Romance"));
        genreList.add(new GenreEntity(18, "Drama"));

        List<StudioEntity> studioList = new ArrayList<>();
        studioList.add(new StudioEntity(20867,
                "P.A.Works",
                "/op3ROO9jB4KwLUZ16VAvuGasRRv.png"));

        return new MediaEntity(476292,
                "Maquia: When the Promised Flower Blooms",
                "/hL3NqRE2ccR4Y2sYSJTrmalRjrz.jpg",
                "/5nkKy7eEDsXqsxFLJy3GxZhE90J.jpg",
                8.4,
                330,
                68.634,
                MEDIA_TYPE_MOVIE,
                1,
                "Released",
                new AiredDateEntity("2018-02-24"),
                studioList,
                genreList,
                115,
                "Maquia is a member of a special race called the Iorph who can live for hundreds of years. However, Maquia has always felt lonely despite being surrounded by her people, as she was orphaned from a young age. She daydreams about the outside world, but dares not travel from her home due to the warnings of the clan's chief.  One day the kingdom of Mezarte invades her homeland. They already have what is left of the giant dragons, the Renato, under their control, and now their king wishes to add the immortality to his bloodline.  They ravage the Iorph homeland and kill most of its inhabitants. Caught in the midst of the attack, Maquia is carried off by one of the Renato. It soon dies, and she is left deserted in a forest, now truly alone save for the cries of a single baby off in the distance. Maquia finds the baby in a destroyed village and decides to raise him as her own, naming him Ariel. Although she knows nothing of the human world, how to raise a child that ages much faster than her.",
                null
        );
    }

    public static List<GenreEntity> generateDummyMovieGenres(){
        List<GenreEntity> genreList = new ArrayList<>();
        genreList.add(new GenreEntity(28, "Action"));
        genreList.add(new GenreEntity(12, "Adventure"));
        genreList.add(new GenreEntity(16, "Animation"));
        genreList.add(new GenreEntity(35, "Comedy"));
        genreList.add(new GenreEntity(80, "Crime"));
        genreList.add(new GenreEntity(99, "Documentary"));
        genreList.add(new GenreEntity(18, "Drama"));
        genreList.add(new GenreEntity(10751, "Family"));
        genreList.add(new GenreEntity(14, "Fantasy"));
        genreList.add(new GenreEntity(36, "History"));
        genreList.add(new GenreEntity(27, "Horror"));
        genreList.add(new GenreEntity(10402, "Music"));
        genreList.add(new GenreEntity(9648, "Mystery"));
        genreList.add(new GenreEntity(10749, "Romance"));
        genreList.add(new GenreEntity(878, "Science Fiction"));
        genreList.add(new GenreEntity(10770, "TV Movie"));
        genreList.add(new GenreEntity(53, "Thriller"));
        genreList.add(new GenreEntity(10752, "War"));
        genreList.add(new GenreEntity(37, "Western"));
        return genreList;
    }

    public static List<MediaEntity> generateDummyMovieLatestRelease(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(798141,
                "Doors",
                "/pGPUXyhQTOqskKdDOD3Fmicqfc0.jpg",
                "/n6v672SLXwM70aXEm5EQc7uwtXB.jpg",
                5.5,
                2,
                50.494,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2021-03-23"),
                new ArrayList<>(Collections.singletonList(878)),
                "Without warning, millions of mysterious alien “doors” suddenly appear around the globe. In a rush to determine the reason for their arrival, mankind must work together to understand the purpose of these cosmic anomalies.")
        );
        return movieList;
    }

    public static List<MediaEntity> generateDummyMovieNowPlaying(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(527774,
                "Raya and the Last Dragon",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg",
                8.4,
                1554,
                3828.086,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2021-03-03"),
                new ArrayList<>(Arrays.asList(16, 12, 14, 10751, 28)),
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.")
        );
        return movieList;
    }

    public static List<MediaEntity> generateDummyMoviePopular(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(791373,
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                8.7,
                3012,
                6011.914,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2021-03-18"),
                new ArrayList<>(Arrays.asList(28, 12, 14, 878)),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.")
        );
        return movieList;
    }

    public static List<MediaEntity> generateDummyMovieRecommendations(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(242828,
                "When Marnie Was There",
                "/qFXubMYrfSOyTFAD4m8hN8basID.jpg",
                "/axUX7urQDwCGQ9qbgh2Yys7qY9J.jpg",
                8.0,
                1143,
                36.023,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2014-07-19"),
                new ArrayList<>(Arrays.asList(16, 18)),
                "Upon being sent to live with relatives in the countryside due to an illness, an emotionally distant adolescent girl becomes obsessed with an abandoned mansion and infatuated with a girl who lives there - a girl who may or may not be real.")
        );
        return movieList;
    }

    public static List<MediaEntity> generateDummyMovieTopRated(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(724089,
                "Gabriel's Inferno Part II",
                "/pci1ArYW7oJ2eyTo2NMYEKHHiCP.jpg",
                "/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg",
                8.8,
                1195,
                14.236,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2020-07-31"),
                new ArrayList<>(Collections.singletonList(10749)),
                "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?")
        );
        return movieList;
    }

    public static List<MediaEntity> generateDummyMovieTrending(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(791373,
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                8.7,
                3012,
                6011.914,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2021-03-18"),
                new ArrayList<>(Arrays.asList(28, 12, 14, 878)),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.")
        );
        return movieList;
    }

    public static List<MediaEntity> generateDummyMovieUpcoming(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(791373,
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                8.7,
                3012,
                6011.914,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2021-03-18"),
                new ArrayList<>(Arrays.asList(28, 12, 14, 878)),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.")
        );
        return movieList;
    }

    public static List<TrailerEntity> generateDummyMovieVideos(){
        List<TrailerEntity> trailerList = new ArrayList<>();
        trailerList.add(new TrailerEntity("5c60047dc3a3683ccf8f9487",
                "Maquia - Official Trailer | MadFest Premiere",
                "YouTube",
                "Trailer",
                "AEWvRqZQ0RU")
        );
        return trailerList;
    }

    public static List<MediaEntity> generateDummyMultiSearch(){
        List<MediaEntity> movieList = new ArrayList<>();
        movieList.add(new MediaEntity(83097,
                "The Promised Neverland",
                "/oBgRCpAbtMpk1v8wfdsIph7lPQE.jpg",
                "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                9.2,
                559,
                137.103,
                MEDIA_TYPE_TV,
                new AiredDateEntity("2019-01-11"),
                new ArrayList<>(Arrays.asList(16, 9648, 10765, 10759, 18)),
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \\\"Mama,\\\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\\n\\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.")
        );
        movieList.add(new MediaEntity(634521,
                "The Promised Neverland",
                "/sKR2IF9gbWqUlhZPa29xOQKSBUx.jpg",
                "/nBJuqyCXXtTEiOeqtIBnwhqqc0K.jpg",
                7.5,
                2,
                10.596,
                MEDIA_TYPE_MOVIE,
                new AiredDateEntity("2020-12-18"),
                new ArrayList<>(Arrays.asList(28, 53, 14, 9648, 12, 18)),
                "A group of the smartest kids at a seemingly perfect orphanage uncover its dark secret, and set in motion a dangerous and desperate escape plan.")
        );
        return movieList;
    }
    public static CreditsEntity generateDummyTVCredits(){
        List<CastEntity> castList = new ArrayList<>();
        castList.add(new CastEntity(1254135,
                "Sumire Morohoshi",
                "5c9f4822c3a36873558127c2",
                "/sVRDVTYpsIyXIScHDTvgOY8Q8xQ.jpg",
                "Acting",
                "Emma (voice)"
        ));
        castList.add(new CastEntity(1691384,
                "Lynn",
                "5ca4ce0dc3a3685ab90c9fb3",
                "/rrTMVSvlTiQgJ5SNHttk5gFaeL9.jpg",
                "Acting",
                "Gilda (voice)"
        ));
        castList.add(new CastEntity(2280305,
                "Shinei Ueki",
                "5ca4cdfc9251412ded1e52fa",
                "/4d1Qc7dJ3waRjD5PIzcZJe1L3sP.jpg",
                "Acting",
                "Don (voice)"
        ));
        castList.add(new CastEntity(1253008,
                "Yoshimasa Hosoya",
                "5a32b1c9925141032c1a4b5a",
                "/r90gExomlgr7IEt3vZ1Y0xYkXHT.jpg",
                "Acting",
                "Lang (voice)"
        ));
        castList.add(new CastEntity(2933350,
                "Erisa Kuon",
                "5ffdad4594d8a8004129328b",
                "/5t2YAYpkuuy9db64FOtbHomqrq.jpg",
                "Acting",
                "Jemima (voice)"
        ));
        castList.add(new CastEntity(1941815,
                "Mari Hino",
                "5e5844a50398ab0016a1c134",
                "/icbUNrwMWmaHAXmdzDSahrzZYUi.jpg",
                "Acting",
                "Thomas (voice)"
        ));
        castList.add(new CastEntity(2259057,
                "Yuko Mori",
                "5e5844fa35811d00155fc628",
                "/7qpVgzAidbwdrsJIPtNgtJ22gxN.jpg",
                "Acting",
                "Lannion (voice)"
        ));
        castList.add(new CastEntity(1324650,
                "Shizuka Ishigami",
                "5e5844c7a93d25001353c9a4",
                "/r1px7oBmsRbXdxymYIPN28Pa9hS.jpg",
                "Acting",
                "Nat (voice)"
        ));
        castList.add(new CastEntity(1254671,
                "Ai Kayano",
                "5e584532f48b3400137a829e",
                "/l8ywp387rfdAS7mOExe3cEZnsk0.jpg",
                "Acting",
                "Anna (voice)"
        ));
        castList.add(new CastEntity(2415369,
                "Nao Shiraki",
                "600aeb084d0e8d003e71f62a",
                "/kGM4LwJSvHXqF3jny8SjO363ZUk.jpg",
                "Acting",
                "Yvette (voice)"
        ));
        castList.add(new CastEntity(2409935,
                "Koko Hayashi",
                "600aeb17dd83fa003d900385",
                "/Aq8pvNast1w5dnsndgenOfsm8AO.jpg",
                "Acting",
                "Rossi (voice)"
        ));
        castList.add(new CastEntity(1643476,
                "Yoshino Aoyama",
                "600aeb234d0e8d003c714c83",
                "/18nwTfvgurEelSQqPKbMMtUnKja.jpg",
                "Acting",
                "Mark (voice)"
        ));
        castList.add(new CastEntity(2363264,
                "Hiyori Kouno",
                "601d78d5cf62cd003e0888df",
                "/zQjhUCi5skwmdDSQY2OhfxNUOpT.jpg",
                "Acting",
                "Christy (voice)"
        ));

        List<CrewEntity> crewList = new ArrayList<>();
        crewList.add(new CrewEntity(2237132,
                "Kaiu Shirai",
                "601db6af945c20003fda9289",
                null,
                "Series Composition"
        ));
        crewList.add(new CrewEntity(2870811,
                "Qiu Jiahe",
                "604f3add976a230028bc89fa",
                null,
                "Opening/Ending Animation"
        ));
        crewList.add(new CrewEntity(2851165,
                "Hero",
                "604f3ae24ca676003d25b582",
                null,
                "Opening/Ending Animation"
        ));
        crewList.add(new CrewEntity(3011641,
                "ump",
                "604f3ae768929c0053c9ee1d",
                null,
                "Opening/Ending Animation"
        ));
        crewList.add(new CrewEntity(3004369,
                "Liu Yuyuan",
                "604f3af122e480006ad5f3f5",
                null,
                "Opening/Ending Animation"
        ));
        crewList.add(new CrewEntity(2887522,
                "Ken Yamamoto",
                "604f3b1ced96bc002948f7a7",
                null,
                "Opening/Ending Animation"
        ));
        crewList.add(new CrewEntity(1015969,
                "Toshiya Ono",
                "5ca4cd72c3a3685aad0ca4af",
                null,
                "Series Composition"
        ));
        crewList.add(new CrewEntity(1566234,
                "Kazuaki Shimada",
                "5e58467c35811d0017620d0e",
                null,
                "Character Designer"
        ));
        crewList.add(new CrewEntity(2237132,
                "Kaiu Shirai",
                "601db68fa199a6004105d2e2",
                null,
                "Comic Book"
        ));
        crewList.add(new CrewEntity(2237134,
                "Posuka Demizu",
                "601db697ccb15f003f8ab483",
                null,
                "Comic Book"
        ));
        crewList.add(new CrewEntity(2237134,
                "Posuka Demizu",
                "601db6a52e069700417031bd",
                null,
                "Original Series Design"
        ));
        crewList.add(new CrewEntity(931450,
                "Mamoru Kanbe",
                "604f3b4d2cefc20054fa924f",
                null,
                "Series Director"
        ));

        return new CreditsEntity(83097,
                castList,
                crewList
        );
    }

    public static MediaEntity generateDummyTVDetails(){
        List<GenreEntity> genreList = new ArrayList<>();
        genreList.add(new GenreEntity(16, "Animation"));
        genreList.add(new GenreEntity(9648, "Mystery"));
        genreList.add(new GenreEntity(10765, "Sci-Fi & Fantasy"));
        genreList.add(new GenreEntity(10759, "Action & Adventure"));
        genreList.add(new GenreEntity(18, "Drama"));

        List<StudioEntity> studioList = new ArrayList<>();
        studioList.add(new StudioEntity(121589,
                "CloverWorks",
                "/kZvTniBgpTzAThHCa0OZ1FJtRjy.png"));

        return new MediaEntity(83097,
                "The Promised Neverland",
                "/oBgRCpAbtMpk1v8wfdsIph7lPQE.jpg",
                "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                9.2,
                559,
                137.103,
                MEDIA_TYPE_TV,
                24,
                "Returning Series",
                new AiredDateEntity("2019-01-11", "2021-03-26"),
                studioList,
                genreList,
                23,
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
                null
        );
    }

    public static List<GenreEntity> generateDummyTVGenres(){
        List<GenreEntity> genreList = new ArrayList<>();
        genreList.add(new GenreEntity(10759, "Action & Adventure"));
        genreList.add(new GenreEntity(16, "Animation"));
        genreList.add(new GenreEntity(35, "Comedy"));
        genreList.add(new GenreEntity(80, "Crime"));
        genreList.add(new GenreEntity(99, "Documentary"));
        genreList.add(new GenreEntity(18, "Drama"));
        genreList.add(new GenreEntity(10751, "Family"));
        genreList.add(new GenreEntity(10762, "Kids"));
        genreList.add(new GenreEntity(9648, "Mystery"));
        genreList.add(new GenreEntity(10763, "News"));
        genreList.add(new GenreEntity(10764, "Reality"));
        genreList.add(new GenreEntity(10765, "Sci-Fi & Fantasy"));
        genreList.add(new GenreEntity(10766, "Soap"));
        genreList.add(new GenreEntity(10767, "Talk"));
        genreList.add(new GenreEntity(10768, "War & Politics"));
        genreList.add(new GenreEntity(37, "Western"));
        return genreList;
    }

    public static List<MediaEntity> generateDummyTVLatestRelease(){
        List<MediaEntity> tvList = new ArrayList<>();
        tvList.add(new MediaEntity(120424,
                "Demi Lovato: Dancing with the Devil",
                "/sSxWljZhNAOsmyzvnLGHhEOnSb9.jpg",
                "/v6FrR1oc2e4xcrsx2mC1bDmukws.jpg",
                0,
                0,
                29.102,
                MEDIA_TYPE_TV,
                new AiredDateEntity("2021-03-23"),
                new ArrayList<>(Collections.singletonList(99)),
                "Demi Lovato holds nothing back in this powerful four part documentary series exploring every aspect that led to her nearly fatal overdose in 2018, and her awakenings in the aftermath. Director Michael D. Ratner is granted unprecedented access to the superstar’s personal and musical journey during the most trying time of her life as she unearths her prior traumas and discovers the importance of her physical, emotional, and mental health. Far deeper than an inside look beyond the celebrity surface, this is an intimate portrait of addiction, and the process of healing and empowerment.")
        );
        return tvList;
    }

    public static List<MediaEntity> generateDummyTVOnTheAir(){
        List<MediaEntity> tvList = new ArrayList<>();
        tvList.add(new MediaEntity(88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                7.7,
                1366,
                2593.349,
                MEDIA_TYPE_TV,
                new AiredDateEntity("2021-03-19"),
                new ArrayList<>(Arrays.asList(10765, 10759, 18)),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.")
        );
        return tvList;
    }

    public static List<MediaEntity> generateDummyTVPopular(){
        List<MediaEntity> tvList = new ArrayList<>();
        tvList.add(new MediaEntity(88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                7.7,
                1366,
                2593.349,
                MEDIA_TYPE_TV,
                new AiredDateEntity("2021-03-19"),
                new ArrayList<>(Arrays.asList(10765, 10759, 18)),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.")
        );
        return tvList;
    }

    public static List<MediaEntity> generateDummyTVRecommendations(){
        List<MediaEntity> tvList = new ArrayList<>();
        tvList.add(new MediaEntity(72636,
                "Made In Abyss",
                "/5ICCEOKdqHFGp03zNMZmi95q9WB.jpg",
                "/uzp513qTcHsAavlCJ58x5d73bzy.jpg",
                8.9,
                186,
                50.823,
                MEDIA_TYPE_TV,
                new AiredDateEntity("2017-07-07"),
                new ArrayList<>(Arrays.asList(16, 18, 10759, 10765)),
                "Located in the center of a remote island, the Abyss is the last unexplored region, a huge and treacherous fathomless hole inhabited by strange creatures where only the bravest adventurers descend in search of ancient relics. In the upper levels of the Abyss, Riko, a girl who dreams of becoming an explorer, stumbles upon a mysterious little boy.")
        );
        return tvList;
    }

    public static List<MediaEntity> generateDummyTVTopRated(){
        List<MediaEntity> tvList = new ArrayList<>();
        tvList.add(new MediaEntity(100,
                "I Am Not an Animal",
                "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                null,
                9.4,
                604,
                12.223,
                MEDIA_TYPE_TV,
                new AiredDateEntity("2020-07-31"),
                new ArrayList<>(Arrays.asList(16, 35)),
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.")
        );
        return tvList;
    }

    public static List<MediaEntity> generateDummyTVTrending(){
        List<MediaEntity> tvList = new ArrayList<>();
        tvList.add(new MediaEntity(88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                7.7,
                1366,
                2593.349,
                MEDIA_TYPE_TV,
                new AiredDateEntity("2021-03-19"),
                new ArrayList<>(Arrays.asList(10765, 10759, 18)),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.")
        );
        return tvList;
    }

    public static List<TrailerEntity> generateDummyTVVideos(){
        List<TrailerEntity> trailerList = new ArrayList<>();
        trailerList.add(new TrailerEntity("5c3224cec3a3680b8ca6c045",
                "The Promised Neverland Trailer 1",
                "YouTube",
                "Trailer",
                "ApLudqucq-s")
        );
        trailerList.add(new TrailerEntity("5c3224e1c3a3680b8ca6c0b6",
                "The Promised Neverland Trailer 2",
                "YouTube",
                "Trailer",
                "JIcjo7XVlOY")
        );
        trailerList.add(new TrailerEntity("601d79837314a1003b007e1b",
                "The Promised Neverland OP Opening HD",
                "YouTube",
                "Opening Credits",
                "4GDVEl3qw2M")
        );
        return trailerList;
    }

    public static CreditsResponse generateRemoteDummyMovieCredits(){
        List<CastItem> cast = new ArrayList<>();
        cast.add(new CastItem(1835721,
                "Manaka Iwami",
                "5a32b107c3a3680b881a5782",
                "/zJgSy0mSBVvRbR44Bub8NPUlNeS.jpg",
                "Acting",
                "Maquia (voice)"
        ));
        cast.add(new CastItem(19588,
                "Miyu Irino",
                "5a32b115925141547201908f",
                "/dcZ4IJX8CBcJzxy8hhKFXv59LDE.jpg",
                "Acting",
                "Erial (voice)"
        ));
        cast.add(new CastItem(144655,
                "Tomokazu Sugita",
                "5a32b1a0c3a36814ab01b6f4",
                "/tW9VGnxmdhy3XTWfssk8ySrDu7K.jpg",
                "Acting",
                "Isol (voice)"
        ));
        cast.add(new CastItem(1253008,
                "Yoshimasa Hosoya",
                "5a32b1c9925141032c1a4b5a",
                "/r90gExomlgr7IEt3vZ1Y0xYkXHT.jpg",
                "Acting",
                "Lang (voice)"
        ));
        cast.add(new CastItem(186341,
                "Miyuki Sawashiro",
                "5a32b890c3a36814ab01bdf6",
                "/o0ZlluSQHTyaKuFubRtW7rPxQBW.jpg",
                "Acting",
                "Rashine (voice)"
        ));
        cast.add(new CastItem(1241566,
                "Rina Sato",
                "5a32b8a2925141547501997e",
                "/q1VWJEI3b386hEcYzzbiN4KmoPn.jpg",
                "Acting",
                "Mido (voice)"
        ));
        cast.add(new CastItem(1254671,
                "Ai Kayano",
                "5a32b8b4925141547201979b",
                "/l8ywp387rfdAS7mOExe3cEZnsk0.jpg",
                "Acting",
                "Leilia (voice)"
        ));
        cast.add(new CastItem(84505,
                "Hiroaki Hirata",
                "5a32b8c4c3a368148f018d2d",
                "/wbirlbjye7mfWkaCm0Jy4b2KAdI.jpg",
                "Acting",
                "Baro (voice)"
        ));
        cast.add(new CastItem(1072774,
                "Yoko Hikasa",
                "5a32b8d20e0a264ccd1a6559",
                "/e6I7r45YZXgvlEVSGR3L14tSl87.jpg",
                "Acting",
                "Tita (voice)"
        ));
        cast.add(new CastItem(151182,
                "Misaki Kuno",
                "5a32b8ecc3a36814ab01be5b",
                "/6qslBrgao1mthGJK3M9keUYyMIw.jpg",
                "Acting",
                "Medmel (voice)"
        ));
        cast.add(new CastItem(149894,
                "Yuki Kaji",
                "5a680fc99251411bbb004405",
                "/8wKdPV11IwowfwoqGqMMNt9hmp6.jpg",
                "Acting",
                "Clear (voice)"
        ));

        List<CrewItem> crew = new ArrayList<>();
        crew.add(new CrewItem(57304,
                "Kenji Kawai",
                "59be9d7bc3a368301f000580",
                "/l6GYinTkWfH0tKMv2WnExwU1Yft.jpg",
                "Music"
        ));
        crew.add(new CrewItem(119240,
                "Kazuhiro Wakabayashi",
                "59be9d85c3a3682feb0005d6",
                null,
                "Sound Director"
        ));
        crew.add(new CrewItem(1255613,
                "Kenji Horikawa",
                "5a32b922925141547e019a7f",
                null,
                "Producer"
        ));
        crew.add(new CrewItem(1257117,
                "Mari Okada",
                "5a8d8c3e0e0a267c7400c336",
                "/mfq0VCH3tWJGOUGk1ivtyBH1wz3.jpg",
                "Director"
        ));
        crew.add(new CrewItem(1257117,
                "Mari Okada",
                "5a32afeb9251415472018f72",
                "/mfq0VCH3tWJGOUGk1ivtyBH1wz3.jpg",
                "Writer"
        ));
        crew.add(new CrewItem(1257117,
                "Mari Okada",
                "59be9d68c3a3682ff8000601",
                "/mfq0VCH3tWJGOUGk1ivtyBH1wz3.jpg",
                "Script"
        ));
        crew.add(new CrewItem(1680861,
                "Kazuki Higashiji",
                "5a32b9369251415472019829",
                null,
                "Art Direction"
        ));

        return new CreditsResponse(476292,
                cast,
                crew
        );
    }

    public static MovieDetailsResponse generateRemoteDummyMovieDetails(){
        List<GenreItem> genreList = new ArrayList<>();
        genreList.add(new GenreItem(16, "Animation"));
        genreList.add(new GenreItem(14, "Fantasy"));
        genreList.add(new GenreItem(10749, "Romance"));
        genreList.add(new GenreItem(18, "Drama"));

        List<ProductionCompanyItem> studioList = new ArrayList<>();
        studioList.add(new ProductionCompanyItem(20867,
                "P.A.Works",
                "/op3ROO9jB4KwLUZ16VAvuGasRRv.png"));

        return new MovieDetailsResponse(476292,
                "Maquia: When the Promised Flower Blooms",
                "/hL3NqRE2ccR4Y2sYSJTrmalRjrz.jpg",
                "/5nkKy7eEDsXqsxFLJy3GxZhE90J.jpg",
                8.4,
                330,
                68.634,
                "Released",
                "2018-02-24",
                studioList,
                genreList,
                115,
                "Maquia is a member of a special race called the Iorph who can live for hundreds of years. However, Maquia has always felt lonely despite being surrounded by her people, as she was orphaned from a young age. She daydreams about the outside world, but dares not travel from her home due to the warnings of the clan's chief.  One day the kingdom of Mezarte invades her homeland. They already have what is left of the giant dragons, the Renato, under their control, and now their king wishes to add the immortality to his bloodline.  They ravage the Iorph homeland and kill most of its inhabitants. Caught in the midst of the attack, Maquia is carried off by one of the Renato. It soon dies, and she is left deserted in a forest, now truly alone save for the cries of a single baby off in the distance. Maquia finds the baby in a destroyed village and decides to raise him as her own, naming him Ariel. Although she knows nothing of the human world, how to raise a child that ages much faster than her."
        );
    }

    public static GenresResponse generateRemoteDummyMovieGenres(){
        List<GenreItem> results = new ArrayList<>();
        results.add(new GenreItem(28, "Action"));
        results.add(new GenreItem(12, "Adventure"));
        results.add(new GenreItem(16, "Animation"));
        results.add(new GenreItem(35, "Comedy"));
        results.add(new GenreItem(80, "Crime"));
        results.add(new GenreItem(99, "Documentary"));
        results.add(new GenreItem(18, "Drama"));
        results.add(new GenreItem(10751, "Family"));
        results.add(new GenreItem(14, "Fantasy"));
        results.add(new GenreItem(36, "History"));
        results.add(new GenreItem(27, "Horror"));
        results.add(new GenreItem(10402, "Music"));
        results.add(new GenreItem(9648, "Mystery"));
        results.add(new GenreItem(10749, "Romance"));
        results.add(new GenreItem(878, "Science Fiction"));
        results.add(new GenreItem(10770, "TV Movie"));
        results.add(new GenreItem(53, "Thriller"));
        results.add(new GenreItem(10752, "War"));
        results.add(new GenreItem(37, "Western"));
        return new GenresResponse(results);
    }

    public static MovieResponse generateRemoteDummyMovieLatestRelease(){
        List<MovieItem> results = new ArrayList<>();
        results.add(new MovieItem(798141,
                "Doors",
                "/pGPUXyhQTOqskKdDOD3Fmicqfc0.jpg",
                "/n6v672SLXwM70aXEm5EQc7uwtXB.jpg",
                5.5,
                2,
                50.494,
                "2021-03-23",
                new ArrayList<>(Collections.singletonList(878)),
                "Without warning, millions of mysterious alien “doors” suddenly appear around the globe. In a rush to determine the reason for their arrival, mankind must work together to understand the purpose of these cosmic anomalies.")
        );
        return new MovieResponse(1, 16, results, 303);
    }

    public static MovieResponse generateRemoteDummyMovieNowPlaying(){
        List<MovieItem> results = new ArrayList<>();
        results.add(new MovieItem(527774,
                "Raya and the Last Dragon",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg",
                8.4,
                1554,
                3828.086,
                "2021-03-03",
                new ArrayList<>(Arrays.asList(16, 12, 14, 10751, 28)),
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.")
        );
        return new MovieResponse(1, 16, results, 303);
    }

    public static MovieResponse generateRemoteDummyMoviePopular(){
        List<MovieItem> results = new ArrayList<>();
        results.add(new MovieItem(791373,
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                8.7,
                3012,
                6011.914,
                "2021-03-18",
                new ArrayList<>(Arrays.asList(28, 12, 14, 878)),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.")
        );
        return new MovieResponse(1, 16, results, 303);
    }

    public static MovieResponse generateRemoteDummyMovieRecommendations(){
        List<MovieItem> results = new ArrayList<>();
        results.add(new MovieItem(242828,
                "When Marnie Was There",
                "/qFXubMYrfSOyTFAD4m8hN8basID.jpg",
                "/axUX7urQDwCGQ9qbgh2Yys7qY9J.jpg",
                8.0,
                1143,
                36.023,
                "2014-07-19",
                new ArrayList<>(Arrays.asList(16, 18)),
                "Upon being sent to live with relatives in the countryside due to an illness, an emotionally distant adolescent girl becomes obsessed with an abandoned mansion and infatuated with a girl who lives there - a girl who may or may not be real.")
        );
        return new MovieResponse(1, 16, results, 303);
    }

    public static MovieResponse generateRemoteDummyMovieTopRated(){
        List<MovieItem> results = new ArrayList<>();
        results.add(new MovieItem(724089,
                "Gabriel's Inferno Part II",
                "/pci1ArYW7oJ2eyTo2NMYEKHHiCP.jpg",
                "/jtAI6OJIWLWiRItNSZoWjrsUtmi.jpg",
                8.8,
                1195,
                14.236,
                "2020-07-31",
                new ArrayList<>(Collections.singletonList(10749)),
                "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?")
        );
        return new MovieResponse(1, 16, results, 303);
    }

    public static MovieResponse generateRemoteDummyMovieTrending(){
        List<MovieItem> results = new ArrayList<>();
        results.add(new MovieItem(791373,
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                8.7,
                3012,
                6011.914,
                "2021-03-18",
                new ArrayList<>(Arrays.asList(28, 12, 14, 878)),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.")
        );
        return new MovieResponse(1, 16, results, 303);
    }

    public static MovieResponse generateRemoteDummyMovieUpcoming(){
        List<MovieItem> results = new ArrayList<>();
        results.add(new MovieItem(791373,
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                8.7,
                3012,
                6011.914,
                "2021-03-18",
                new ArrayList<>(Arrays.asList(28, 12, 14, 878)),
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.")
        );
        return new MovieResponse(1, 16, results, 303);
    }

    public static VideosResponse generateRemoteDummyMovieVideos(){
        List<VideoItem> results = new ArrayList<>();
        results.add(new VideoItem("5c60047dc3a3683ccf8f9487",
                "Maquia - Official Trailer | MadFest Premiere",
                "YouTube",
                "Trailer",
                "AEWvRqZQ0RU")
        );
        return new VideosResponse(476292, results);
    }

    public static MultiSearchResponse generateRemoteDummyMultiSearch(){
        List<SearchResultItem> results = new ArrayList<>();
        results.add(new SearchResultItem(83097,
                null,
                "The Promised Neverland",
                "/oBgRCpAbtMpk1v8wfdsIph7lPQE.jpg",
                "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                9.2,
                559,
                137.103,
                MEDIA_TYPE_TV,
                null,
                "2019-01-11",
                new ArrayList<>(Arrays.asList(16, 9648, 10765, 10759, 18)),
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \\\"Mama,\\\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\\n\\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.")
        );
        results.add(new SearchResultItem(634521,
                "The Promised Neverland",
                null,
                "/sKR2IF9gbWqUlhZPa29xOQKSBUx.jpg",
                "/nBJuqyCXXtTEiOeqtIBnwhqqc0K.jpg",
                7.5,
                2,
                10.596,
                MEDIA_TYPE_MOVIE,
                "2020-12-18",
                null,
                new ArrayList<>(Arrays.asList(28, 53, 14, 9648, 12, 18)),
                "A group of the smartest kids at a seemingly perfect orphanage uncover its dark secret, and set in motion a dangerous and desperate escape plan.")
        );
        return new MultiSearchResponse(1, 1, results, 2);
    }

    public static CreditsResponse generateRemoteDummyTVCredits(){
        List<CastItem> cast = new ArrayList<>();
        cast.add(new CastItem(1254135,
                "Sumire Morohoshi",
                "5c9f4822c3a36873558127c2",
                "/sVRDVTYpsIyXIScHDTvgOY8Q8xQ.jpg",
                "Acting",
                "Emma (voice)"
        ));
        cast.add(new CastItem(1691384,
                "Lynn",
                "5ca4ce0dc3a3685ab90c9fb3",
                "/rrTMVSvlTiQgJ5SNHttk5gFaeL9.jpg",
                "Acting",
                "Gilda (voice)"
        ));
        cast.add(new CastItem(2280305,
                "Shinei Ueki",
                "5ca4cdfc9251412ded1e52fa",
                "/4d1Qc7dJ3waRjD5PIzcZJe1L3sP.jpg",
                "Acting",
                "Don (voice)"
        ));
        cast.add(new CastItem(1253008,
                "Yoshimasa Hosoya",
                "5a32b1c9925141032c1a4b5a",
                "/r90gExomlgr7IEt3vZ1Y0xYkXHT.jpg",
                "Acting",
                "Lang (voice)"
        ));
        cast.add(new CastItem(2933350,
                "Erisa Kuon",
                "5ffdad4594d8a8004129328b",
                "/5t2YAYpkuuy9db64FOtbHomqrq.jpg",
                "Acting",
                "Jemima (voice)"
        ));
        cast.add(new CastItem(1941815,
                "Mari Hino",
                "5e5844a50398ab0016a1c134",
                "/icbUNrwMWmaHAXmdzDSahrzZYUi.jpg",
                "Acting",
                "Thomas (voice)"
        ));
        cast.add(new CastItem(2259057,
                "Yuko Mori",
                "5e5844fa35811d00155fc628",
                "/7qpVgzAidbwdrsJIPtNgtJ22gxN.jpg",
                "Acting",
                "Lannion (voice)"
        ));
        cast.add(new CastItem(1324650,
                "Shizuka Ishigami",
                "5e5844c7a93d25001353c9a4",
                "/r1px7oBmsRbXdxymYIPN28Pa9hS.jpg",
                "Acting",
                "Nat (voice)"
        ));
        cast.add(new CastItem(1254671,
                "Ai Kayano",
                "5e584532f48b3400137a829e",
                "/l8ywp387rfdAS7mOExe3cEZnsk0.jpg",
                "Acting",
                "Anna (voice)"
        ));
        cast.add(new CastItem(2415369,
                "Nao Shiraki",
                "600aeb084d0e8d003e71f62a",
                "/kGM4LwJSvHXqF3jny8SjO363ZUk.jpg",
                "Acting",
                "Yvette (voice)"
        ));
        cast.add(new CastItem(2409935,
                "Koko Hayashi",
                "600aeb17dd83fa003d900385",
                "/Aq8pvNast1w5dnsndgenOfsm8AO.jpg",
                "Acting",
                "Rossi (voice)"
        ));
        cast.add(new CastItem(1643476,
                "Yoshino Aoyama",
                "600aeb234d0e8d003c714c83",
                "/18nwTfvgurEelSQqPKbMMtUnKja.jpg",
                "Acting",
                "Mark (voice)"
        ));
        cast.add(new CastItem(2363264,
                "Hiyori Kouno",
                "601d78d5cf62cd003e0888df",
                "/zQjhUCi5skwmdDSQY2OhfxNUOpT.jpg",
                "Acting",
                "Christy (voice)"
        ));

        List<CrewItem> crew = new ArrayList<>();
        crew.add(new CrewItem(2237132,
                "Kaiu Shirai",
                "601db6af945c20003fda9289",
                null,
                "Series Composition"
        ));
        crew.add(new CrewItem(2870811,
                "Qiu Jiahe",
                "604f3add976a230028bc89fa",
                null,
                "Opening/Ending Animation"
        ));
        crew.add(new CrewItem(2851165,
                "Hero",
                "604f3ae24ca676003d25b582",
                null,
                "Opening/Ending Animation"
        ));
        crew.add(new CrewItem(3011641,
                "ump",
                "604f3ae768929c0053c9ee1d",
                null,
                "Opening/Ending Animation"
        ));
        crew.add(new CrewItem(3004369,
                "Liu Yuyuan",
                "604f3af122e480006ad5f3f5",
                null,
                "Opening/Ending Animation"
        ));
        crew.add(new CrewItem(2887522,
                "Ken Yamamoto",
                "604f3b1ced96bc002948f7a7",
                null,
                "Opening/Ending Animation"
        ));
        crew.add(new CrewItem(1015969,
                "Toshiya Ono",
                "5ca4cd72c3a3685aad0ca4af",
                null,
                "Series Composition"
        ));
        crew.add(new CrewItem(1566234,
                "Kazuaki Shimada",
                "5e58467c35811d0017620d0e",
                null,
                "Character Designer"
        ));
        crew.add(new CrewItem(2237132,
                "Kaiu Shirai",
                "601db68fa199a6004105d2e2",
                null,
                "Comic Book"
        ));
        crew.add(new CrewItem(2237134,
                "Posuka Demizu",
                "601db697ccb15f003f8ab483",
                null,
                "Comic Book"
        ));
        crew.add(new CrewItem(2237134,
                "Posuka Demizu",
                "601db6a52e069700417031bd",
                null,
                "Original Series Design"
        ));
        crew.add(new CrewItem(931450,
                "Mamoru Kanbe",
                "604f3b4d2cefc20054fa924f",
                null,
                "Series Director"
        ));

        return new CreditsResponse(83097, cast, crew);
    }

    public static TVDetailsResponse generateRemoteDummyTVDetails(){
        List<GenreItem> genres = new ArrayList<>();
        genres.add(new GenreItem(16, "Animation"));
        genres.add(new GenreItem(9648, "Mystery"));
        genres.add(new GenreItem(10765, "Sci-Fi & Fantasy"));
        genres.add(new GenreItem(10759, "Action & Adventure"));
        genres.add(new GenreItem(18, "Drama"));

        List<ProductionCompanyItem> productionCompanies = new ArrayList<>();
        productionCompanies.add(new ProductionCompanyItem(121589,
                "CloverWorks",
                "/kZvTniBgpTzAThHCa0OZ1FJtRjy.png"));

        return new TVDetailsResponse(83097,
                "The Promised Neverland",
                "/oBgRCpAbtMpk1v8wfdsIph7lPQE.jpg",
                "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                9.2,
                559,
                137.103,
                2,
                24,
                "Returning Series",
                "2019-01-11",
                "2021-03-26",
                productionCompanies,
                genres,
                new ArrayList<>(Collections.singletonList(23)),
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama."
        );
    }

    public static GenresResponse generateRemoteDummyTVGenres(){
        List<GenreItem> genres = new ArrayList<>();
        genres.add(new GenreItem(10759, "Action & Adventure"));
        genres.add(new GenreItem(16, "Animation"));
        genres.add(new GenreItem(35, "Comedy"));
        genres.add(new GenreItem(80, "Crime"));
        genres.add(new GenreItem(99, "Documentary"));
        genres.add(new GenreItem(18, "Drama"));
        genres.add(new GenreItem(10751, "Family"));
        genres.add(new GenreItem(10762, "Kids"));
        genres.add(new GenreItem(9648, "Mystery"));
        genres.add(new GenreItem(10763, "News"));
        genres.add(new GenreItem(10764, "Reality"));
        genres.add(new GenreItem(10765, "Sci-Fi & Fantasy"));
        genres.add(new GenreItem(10766, "Soap"));
        genres.add(new GenreItem(10767, "Talk"));
        genres.add(new GenreItem(10768, "War & Politics"));
        genres.add(new GenreItem(37, "Western"));
        return new GenresResponse(genres);
    }

    public static TVResponse generateRemoteDummyTVLatestRelease(){
        List<TVItem> results = new ArrayList<>();
        results.add(new TVItem(120424,
                "Demi Lovato: Dancing with the Devil",
                "/sSxWljZhNAOsmyzvnLGHhEOnSb9.jpg",
                "/v6FrR1oc2e4xcrsx2mC1bDmukws.jpg",
                0,
                0,
                29.102,
                "2021-03-23",
                new ArrayList<>(Collections.singletonList(99)),
                "Demi Lovato holds nothing back in this powerful four part documentary series exploring every aspect that led to her nearly fatal overdose in 2018, and her awakenings in the aftermath. Director Michael D. Ratner is granted unprecedented access to the superstar’s personal and musical journey during the most trying time of her life as she unearths her prior traumas and discovers the importance of her physical, emotional, and mental health. Far deeper than an inside look beyond the celebrity surface, this is an intimate portrait of addiction, and the process of healing and empowerment.")
        );
        return new TVResponse(1, 1, results, 4);
    }

    public static TVResponse generateRemoteDummyTVOnTheAir(){
        List<TVItem> results = new ArrayList<>();
        results.add(new TVItem(88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                7.7,
                1366,
                2593.349,
                "2021-03-19",
                new ArrayList<>(Arrays.asList(10765, 10759, 18)),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.")
        );
        return new TVResponse(1, 42, results, 821);
    }

    public static TVResponse generateRemoteDummyTVPopular(){
        List<TVItem> results = new ArrayList<>();
        results.add(new TVItem(88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                7.7,
                1366,
                2593.349,
                "2021-03-19",
                new ArrayList<>(Arrays.asList(10765, 10759, 18)),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.")
        );
        return new TVResponse(1, 500, results, 10000);
    }

    public static TVResponse generateRemoteDummyTVRecommendations(){
        List<TVItem> results = new ArrayList<>();
        results.add(new TVItem(72636,
                "Made In Abyss",
                "/5ICCEOKdqHFGp03zNMZmi95q9WB.jpg",
                "/uzp513qTcHsAavlCJ58x5d73bzy.jpg",
                8.9,
                186,
                50.823,
                "2017-07-07",
                new ArrayList<>(Arrays.asList(16, 18, 10759, 10765)),
                "Located in the center of a remote island, the Abyss is the last unexplored region, a huge and treacherous fathomless hole inhabited by strange creatures where only the bravest adventurers descend in search of ancient relics. In the upper levels of the Abyss, Riko, a girl who dreams of becoming an explorer, stumbles upon a mysterious little boy.")
        );
        return new TVResponse(1, 2, results, 40);
    }

    public static TVResponse generateRemoteDummyTVTopRated(){
        List<TVItem> results = new ArrayList<>();
        results.add(new TVItem(100,
                "I Am Not an Animal",
                "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                null,
                9.4,
                604,
                12.223,
                "2020-07-31",
                new ArrayList<>(Arrays.asList(16, 35)),
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.")
        );
        return new TVResponse(1, 86, results, 1712);
    }

    public static TVResponse generateRemoteDummyTVTrending(){
        List<TVItem> results = new ArrayList<>();
        results.add(new TVItem(88396,
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                7.7,
                1366,
                2593.349,
                "2021-03-19",
                new ArrayList<>(Arrays.asList(10765, 10759, 18)),
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.")
        );
        return new TVResponse(1, 1000, results, 20000);
    }

    public static VideosResponse generateRemoteDummyTVVideos(){
        List<VideoItem> results = new ArrayList<>();
        results.add(new VideoItem("5c3224cec3a3680b8ca6c045",
                "The Promised Neverland Trailer 1",
                "YouTube",
                "Trailer",
                "ApLudqucq-s")
        );
        results.add(new VideoItem("5c3224e1c3a3680b8ca6c0b6",
                "The Promised Neverland Trailer 2",
                "YouTube",
                "Trailer",
                "JIcjo7XVlOY")
        );
        results.add(new VideoItem("601d79837314a1003b007e1b",
                "The Promised Neverland OP Opening HD",
                "YouTube",
                "Opening Credits",
                "4GDVEl3qw2M")
        );
        return new VideosResponse(83097, results);
    }
}
