package com.ariefzuhri.blu.utils;

import com.ariefzuhri.blu.model.AiredDate;
import com.ariefzuhri.blu.model.Movie;

import java.util.ArrayList;

import static com.ariefzuhri.blu.utils.Constants.STATUS_CURRENTLY;
import static com.ariefzuhri.blu.utils.Constants.STATUS_FINISHED;
import static com.ariefzuhri.blu.utils.Constants.STATUS_NOT_YET;
import static com.ariefzuhri.blu.utils.Constants.TYPE_MOVIE;
import static com.ariefzuhri.blu.utils.Constants.TYPE_TV;

public class DummyMovie {
    private static final int[] ids = {
            35851,
            21557,
            28725,
            1829,
            585,
            578,
            12355,
            523,
            199,
            41084,
            37779,
            35180,
            32093,
            22789,
            39730,
            17549,
            34798,
            31043,
            33352,
            43299
    };

    private static final String[] titles = {
            "Maquia: When the Promised Flower Blooms",
            "When Marnie Was There",
            "The Anthem of the Heart",
            "Tales from Earthsea",
            "Whisper of the Heart",
            "Grave of the Fireflies",
            "Wolf Children",
            "My Neighbor Totoro",
            "Spirited Away",
            "Made in Abyss 2",
            "The Promised Neverland",
            "March Comes in Like a Lion 2nd Season",
            "Tanaka-kun is Always Listless",
            "Barakamon",
            "Diary of Our Days at the Breakwater",
            "Non Non Biyori",
            "Laid-Back Camp",
            "ERASED",
            "Violet Evergarden",
            "Wonder Egg Priority"
    };

    private static final String[] posters = {
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx99457-PFq5dreQabIu.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx20555-QGbWQC3Kfpok.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx20968-0zFVsoFUYjeh.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1829-DqhBRfDU7n6D.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx585-5uq7j2xNNVmN.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx578-V18FG7FSVQXy.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx12355-wNsvhEsXEgrH.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx523-gOsP34LWBXTn.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx199-ehguwq1mPvtN.jpg",
            "https://stat.ameba.jp/user_images/20200118/18/bz-125-norinori/00/fc/p/o0650036414698212647.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx101759-ONAwIKCFiNtE.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx98478-dF3mpSKiZkQu.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21495-Xn1lHxRKDBXW.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx20722-mQQHyu4HXpCE.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx109019-xlrVQRdo1EQi.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx17549-n5m9PEt8SKIW.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx98444-zzhSw9o3LJSy.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21234-v2NMgPyoVRoM.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx21827-10F6m50H4GJK.png",
            "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx124845-7dRs0HgkFWgk.jpg"
    };

    private static final String[] covers = {
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/99457-OvX44AF17mEL.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20555-ASSOit2L6OeC.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20968-1ChkqEXgFAmf.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/1829-CogP9EXilVsU.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/585-FySIY9zeEDOc.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/578.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/12355.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/523-rl12Q0av1fCr.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/199-Sm2RU5PSqw7T.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/97986-mXXqF2jR0ywU.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/101759-MhlCoeqnODso.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/98478-Om9j0dRjdCD2.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/21495-HDkVTovzfNQA.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/20722-vjD7BMct1l1n.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/109019-CI5tlvudZ3zS.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/17549.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/98444-FpH9lzLiafe9.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/21234-7lfSSPoMmwr2.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/n21827-8pVyvhWDF7Q1.jpg",
            "https://s4.anilist.co/file/anilistcdn/media/anime/banner/124845-uw8Fiw3kWtbj.jpg"
    };

    private static final String[] scores = {
            "8.45",
            "8.11",
            "7.96",
            "6.95",
            "8.23",
            "8.51",
            "8.64",
            "8.31",
            "8.82",
            "N/A",
            "8.65",
            "9.00",
            "7.90",
            "8.41",
            "7.38",
            "7.95",
            "8.27",
            "8.37",
            "8.64",
            "8.30"
    };

    private static final String[] types = {
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_MOVIE,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV,
            TYPE_TV
    };

    private static final int[] episodes = {
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            0,
            12,
            22,
            12,
            12,
            12,
            12,
            12,
            12,
            13,
            12
    };

    private static final String[] statuses = {
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_NOT_YET,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_FINISHED,
            STATUS_CURRENTLY
    };

    private static final AiredDate[] airedDates = {
            new AiredDate("2018/02/24"),
            new AiredDate("2014/07/19"),
            new AiredDate("2015/09/19"),
            new AiredDate("2016/07/29"),
            new AiredDate("1995/07/15"),
            new AiredDate("1988/04/16"),
            new AiredDate("2012/07/21"),
            new AiredDate("1988/04/16"),
            new AiredDate("2001/07/20"),
            new AiredDate(null),
            new AiredDate("2019/01/10", "2019/03/29"),
            new AiredDate("2017/10/14", "2018/03/31"),
            new AiredDate("2016/04/09", "2016/06/25"),
            new AiredDate("2014/07/06", "2014/09/28"),
            new AiredDate("2020/04/07", "2020/09/22"),
            new AiredDate("2013/10/08", "2013/12/24"),
            new AiredDate("2018/01/04", "2018/03/22"),
            new AiredDate("2016/01/08", "2016/03/25"),
            new AiredDate("2018/01/11", "2018/04/05"),
            new AiredDate("2021/01/13", null)
    };

    //private static final String[] studios = {};

    private static final String[] genres = {
            "Drama, Fantasi, Psikologis",
            "Drama, Misteri, Psikologis",
            "Drama, Musik, Roman, Penggalan Kehidupan",
            "Petualangan, Fantasi",
            "Petualangan, Drama, Roman, Penggalan Kehidupan",
            "Drama",
            "Drama, Fantasi, Roman, Penggalan Kehidupan",
            "Petualangan, Fantasi, Supranatural",
            "Petualangan, Drama, Fantasi, Roman, Supranatural",
            "Petualangan, Drama, Fantasi, Misteri, Fiksi Ilmiah",
            "Drama, Fantasi, Horor, Misteri, Psikologis, Triler",
            "Drama, Penggalan Kehidupan",
            "Komedi, Penggalan Kehidupan",
            "Komedi, Penggalan Kehidupan",
            "Komedi, Penggalan Kehidupan, Olahraga",
            "Komedi, Penggalan Kehidupan",
            "Komedi, Penggalan Kehidupan",
            "Drama, Misteri, Psikologis, Supranatural, Triler",
            "Drama, Fantasi, Penggalan Kehidupan",
            "Laga, Drama, Fantasi, Misteri, Psikologis, Triler"
    };

    private static final int[] runtimes = {
            60+55,
            60+43,
            60+59,
            60+55,
            60+51,
            60+28,
            60+57,
            60+26,
            120+5,
            0,
            22,
            25,
            24,
            22,
            23,
            24,
            23,
            23,
            24,
            23
    };

    private static final String[] synopsises = {
            "Maquia is a member of a special race called the Iorph—mystical beings who can live for hundreds of years and remain separate from the lives and daily troubles of mankind. However, Maquia has always felt lonely despite being surrounded by her people, as she was orphaned from a young age. She daydreams about the outside world, but dares not travel from her home due to the warnings of the clan's chief.\n\nOne day however, the outside world finds her, as the power-hungry kingdom of Mezarte invades her homeland. They already have what is left of the giant dragons, the Renato, under their control, and now their king wishes to add the immortality of the Iorph to his bloodline.\n\nThe humans and their Renato ravage the Iorph homeland and kill most of its inhabitants. Caught in the midst of the attack, Maquia is carried off by one of the Renato that has gone berserk. It soon dies, and she is left deserted in a forest far from home, now truly alone save for the cries of a single baby off in the distance. Maquia finds the baby in a destroyed village and decides to raise him as her own, naming him Ariel. Although she knows nothing of the human world, how to raise a child that ages much faster than her, or how to live with the smoldering loneliness inside, she is determined to make it all work somehow.",
            "Suffering from frequent asthma attacks, young Anna Sasaki is quiet, unsociable, and isolated from her peers, causing her foster parent endless worry. Upon recommendation by the doctor, Anna is sent to the countryside, in hope that the cleaner air and more relaxing lifestyle will improve her health and help clear her mind. Engaging in her passion for sketching, Anna spends her summer days living with her aunt and uncle in a small town near the sea.\n\nOne day while wandering outside, Anna discovers an abandoned mansion known as the Marsh House. However, she soon finds that the residence isn't as vacant as it appears to be, running into a mysterious girl named Marnie. Marnie's bubbly demeanor slowly begins to draw Anna out of her shell as she returns night after night to meet with her new friend. But it seems there is more to the strange girl than meets the eye—as her time in the town nears its end, Anna begins to discover the truth behind the walls of the Marsh House.\n\nOmoide no Marnie tells the touching story of a young girl's journey through self-discovery and friendship, and the summer that she will remember for the rest of her life.",
            "Jun Naruse is a chatterbox whose life is colored by fairy tales and happy endings. However, influenced by her deep belief in those tales, she is too naive and trusting, and her words soon shatter her family's bond when she inadvertently reveals her father's affair. Naruse is scarred for life after being blamed for her parent's divorce, and her regrets soon manifest into a fairy egg—a being who seals her mouth from speaking in order to protect everyone's happy ending.\n\nNow, even in high school, Naruse's speech remains locked by the fairy egg. Even trying to speak causes her stomach to twist. Though unable to convey her thoughts through words, she is unexpectedly chosen to perform in a musical alongside three other students: Takumi Sakagami, Natsuki Nitou, and Daiki Tasaki. Naruse makes her way to the club room to reject the daunting task, but changes her mind when she overhears Sakagami's beautiful singing.\n\nPerhaps the fairy egg \"curse\" does not apply to singing, and perhaps Sakagami is the fairy tale prince she has been seeking all along. Will Naruse be able to convey the anthem of her heart?",
            "Calamities are plaguing the land of Earthsea and dragons have been seen fighting above the clouds—something which has never happened before. Sparrowhawk, a powerful Archmage, sets out to uncover the mystery behind these concerning events and meets Prince Arren along the way. Arren is the fugitive heir to the Kingdom of Enlad and a seemingly quiet and distressed lad. Wandering aimlessly in an attempt to escape the dark presence haunting him, he decides to tag along Sparrowhawk on his journey.\n\nHowever, their arrival in the seaside settlement of Hort Town is met with unexpected trouble—Lord Cob, a powerful evil wizard obsessed with eternal life, stands in their way. Forced to confront him, the pair joins forces with Tenar—an old friend of Sparrowhawk—and Therru, the ill-fated orphan girl she took in. But the enemy's cunning hobby of manipulating emotions may just prove to be catastrophic for the young prince.\n\nSet in a magical world, Ged Senki goes beyond the classical battle between the forces of good and evil, as it explores the inner battles of the heart.",
            "Shizuku Tsukishima is an energetic 14-year-old girl who enjoys reading and writing poetry in her free time. Glancing at the checkout cards of her books one evening, she notices that her library books are frequently checked out by a boy named Seiji Amasawa. Curiosity strikes Shizuku, and she decides to search for the boy who shares her love for literature.\n\nMeeting a peculiar cat on the train, Shizuku follows the animal and is eventually led to a quaint antique shop, where she learns about a cat statuette known as \"The Baron.\" Taking an interest in the shop, she surprisingly finds Seiji, and the two quickly befriend one another. Shizuku learns while acquainting herself with Seiji that he has a dream that he would like to fulfill, causing her dismay as she remains uncertain of her future and has yet to recognize her talents.\n\nHowever, as her relationship with Seiji grows, Shizuku becomes determined to work toward a goal. Guided by the whispers of her heart and inspiration from The Baron, she resolves to carve out her own potential and dreams.",
            "As World War II reaches its conclusion in 1945, Japan faces widespread destruction in the form of American bombings, devastating city after city. Hotaru no Haka, also known as Grave of the Fireflies, is the story of Seita and his sister Setsuko, two Japanese children whose lives are ravaged by the brutal war. They have lost their mother, their father, their home, and the prospect of a bright future—all tragic consequences of the war.\n\nNow orphaned and homeless, Seita and Setsuko have no choice but to drift across the countryside, beset by starvation and disease. Met with the apathy of adults along the way, they find that desperate circumstances can turn even the kindest of people cruel yet their youthful hope shines brightly in the face of unrelenting hardship, preventing the siblings from swiftly succumbing to an inevitable fate.",
            "Hana, a hard-working college student, falls in love with a mysterious man who attends one of her classes though he is not an actual student. As it turns out, he is not truly human either. On a full moon night, he transforms, revealing that he is the last werewolf alive. Despite this, Hana's love remains strong, and the two ultimately decide to start a family.\n\nHana gives birth to two healthy children—Ame, born during rainfall, and Yuki, born during snowfall—both possessing the ability to turn into wolves, a trait inherited from their father. All too soon, however, the sudden death of her lover devastates Hana's life, leaving her to raise a peculiar family completely on her own. The stress of raising her wild-natured children in a densely populated city, all while keeping their identity a secret, culminates in a decision to move to the countryside, where she hopes Ame and Yuki can live a life free from the judgments of society. Wolf Children is the heartwarming story about the challenges of being a single mother in an unforgiving modern world.",
            "In 1950s Japan, Tatsuo Kusakabe relocates himself and his two daughters, Satsuki and Mei, to the countryside to be closer to their mother, who is hospitalized due to long-term illness. As the girls grow acquainted with rural life, Mei encounters a small, bunny-like creature in the yard one day. Chasing it into the forest, she finds \"Totoro\"—a giant, mystical forest spirit whom she soon befriends. Before long, Satsuki too meets Totoro, and the two girls suddenly find their lives filled with magical adventures in nature and fantastical creatures of the woods.",
            "Stubborn, spoiled, and naïve, 10-year-old Chihiro Ogino is less than pleased when she and her parents discover an abandoned amusement park on the way to their new house. Cautiously venturing inside, she realizes that there is more to this place than meets the eye, as strange things begin to happen once dusk falls. Ghostly apparitions and food that turns her parents into pigs are just the start—Chihiro has unwittingly crossed over into the spirit world. Now trapped, she must summon the courage to live and work amongst spirits, with the help of the enigmatic Haku and the cast of unique characters she meets along the way.\n\nVivid and intriguing, Sen to Chihiro no Kamikakushi tells the story of Chihiro's journey through an unfamiliar world as she strives to save her parents and return home.",
            "Directly after the events of Made in Abyss Movie 3: Dawn of the Deep Soul, the third installment of Made in Abyss covers the adventure of Reg, Riko, and Nanachi in the Sixth Layer, The Capital of the Unreturned.",
            "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage—a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family, never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama.",
            "Now in his second year of high school, Rei Kiriyama continues pushing through his struggles in the professional shogi world as well as his personal life. Surrounded by vibrant personalities at the shogi hall, the school club, and in the local community, his solitary shell slowly begins to crack. Among them are the three Kawamoto sisters—Akari, Hinata, and Momo—who forge an affectionate and familial bond with Rei. Through these ties, he realizes that everyone is burdened by their own emotional hardships and begins learning how to rely on others while supporting them in return.\n\nNonetheless, the life of a professional is not easy. Between tournaments, championships, and title matches, the pressure mounts as Rei advances through the ranks and encounters incredibly skilled opponents. As he manages his relationships with those who have grown close to him, the shogi player continues to search for the reason he plays the game that defines his career.",
            "For high school student Tanaka, the act of being listless is a way of life. Known for his inattentiveness and ability to fall asleep anywhere, Tanaka prays that each day will be as uneventful as the last, seeking to preserve his lazy lifestyle however he can by avoiding situations that require him to exert himself. Along with his dependable friend Oota who helps him with tasks he is unable to accomplish, the lethargic teenager constantly deals with events that prevent him from experiencing the quiet and peaceful days he longs for.",
            "Seishuu Handa is an up-and-coming calligrapher: young, handsome, talented, and unfortunately, a narcissist to boot. When a veteran labels his award-winning piece as \"unoriginal,\" Seishuu quickly loses his cool with severe repercussions.\n\nAs punishment, and also in order to aid him in self-reflection, Seishuu's father exiles him to the Goto Islands, far from the comfortable Tokyo lifestyle the temperamental artist is used to. Now thrown into a rural setting, Seishuu must attempt to find new inspiration and develop his own unique art style—that is, if boisterous children (headed by the frisky Naru Kotoishi), fujoshi middle schoolers, and energetic old men stop barging into his house! The newest addition to the intimate and quirky Goto community only wants to get some work done, but the islands are far from the peaceful countryside he signed up for. Thanks to his wacky neighbors who are entirely incapable of minding their own business, the arrogant calligrapher learns so much more than he ever hoped to.",
            "Hina Tsurugi and her family have just moved to a quaint seaside town. Hoping to savor the sight of the peaceful ocean, Hina stumbles upon a girl named Yuuki Kuroiwa—an upperclassman at her new school—who invites Hina to join her in fishing. Hina reels in an octopus, which falls onto her; being afraid of bugs and big creatures, she panics and begs Yuuki to remove it from her. Yuuki sees this as an opportunity to force Hina to join the school's Breakwater Club—a club where members gather, catch, and eat various types of marine life as their main activity.\n\nAlthough her attempts to refuse to join fail, Hina slowly begins to discover the hidden joy in fishing. Her view on the sport changes, now looking forward to all the delightful experiences she can take part in alongside her fellow club members.",
            "Asahigaoka might look like typical, boring countryside to most; however, no day in this village can ever be considered colorless thanks to five students of varying ages occupying the only class in the only school in town. The youngest student is first grader Renge Miyauchi, who brings an unadulterated wit, curiosity, and her characteristic catchphrase, \"Nyanpasu!\" Then there are the Koshigaya siblings consisting of the quiet ninth grader and elder brother Suguru, diminutive eighth grader Komari, and the mischievous seventh grader Natsumi. The recent arrival of Tokyo-raised fifth grader Hotaru Ichijou, who appears overdeveloped for her age and thus naturally holds an air of maturity, rounds out this lively and vibrant group of five classmates.\n\nBased on the manga penned and illustrated by Atto, Non Non Biyori chronicles the not-so-normal daily lives of this group of friends as they engage in their own brand of fun and frolic, and playfully struggle with the realities of living in a rural area.",
            "While the perfect getaway for most girls her age might be a fancy vacation with their loved ones, Rin Shima's ideal way of spending her days off is camping alone at the base of Mount Fuji. From pitching her tent to gathering firewood, she has always done everything by herself, and has no plans of leaving her little solitary world.\n\nHowever, what starts off as one of Rin's usual camping sessions somehow ends up as a surprise get-together for two when the lost Nadeshiko Kagamihara is forced to take refuge at her campsite. Originally intending to see the picturesque view of Mount Fuji for herself, Nadeshiko's plans are disrupted when she ends up falling asleep partway to her destination. Alone and with no other choice, she seeks help from the only other person nearby. Despite their hasty introductions, the two girls nevertheless enjoy the chilly night together, eating ramen and conversing while the campfire keeps them warm. And even after Nadeshiko's sister finally picks her up later that night, both girls silently ponder the possibility of another camping trip together.",
            "When tragedy is about to strike, Satoru Fujinuma finds himself sent back several minutes before the accident occurs. The detached, 29-year-old manga artist has taken advantage of this powerful yet mysterious phenomenon, which he calls \"Revival,\" to save many lives.\n\nHowever, when he is wrongfully accused of murdering someone close to him, Satoru is sent back to the past once again, but this time to 1988, 18 years in the past. Soon, he realizes that the murder may be connected to the abduction and killing of one of his classmates, the solitary and mysterious Kayo Hinazuki, that took place when he was a child. This is his chance to make things right.\n\nBoku dake ga Inai Machi follows Satoru in his mission to uncover what truly transpired 18 years ago and prevent the death of his classmate while protecting those he cares about in the present.",
            "The Great War finally came to an end after four long years of conflict; fractured in two, the continent of Telesis slowly began to flourish once again. Caught up in the bloodshed was Violet Evergarden, a young girl raised for the sole purpose of decimating enemy lines. Hospitalized and maimed in a bloody skirmish during the War's final leg, she was left with only words from the person she held dearest, but with no understanding of their meaning.\n\nRecovering from her wounds, Violet starts a new life working at CH Postal Services after a falling out with her new intended guardian family. There, she witnesses by pure chance the work of an \"Auto Memory Doll,\" amanuenses that transcribe people's thoughts and feelings into words on paper. Moved by the notion, Violet begins work as an Auto Memory Doll, a trade that will take her on an adventure, one that will reshape the lives of her clients and hopefully lead to self-discovery.",
            "Following the suicide of her best and only friend, Koito Nagase, Ai Ooto is left grappling with her new reality. With nothing left to live for, she follows the instructions of a mysterious entity and gets roped into purchasing an egg, or specifically, a Wonder Egg.\n\nUpon breaking the egg in a world that materializes during her sleep, Ai is tasked with saving people from the adversities that come their way. In doing so, she believes that she has moved one step closer to saving her best friend. With this dangerous yet tempting opportunity in the palms of her hands, Ai enters a place where she must recognize the relationship between other people's demons and her own.\n\nAs past trauma, unforgettable regrets, and innate fears hatch in the bizarre world of Wonder Egg Priority, a young girl discovers the different inner struggles tormenting humankind and rescues them from their worst fears."
    };

    private static final String[] trailers = {
            "fi1gmq0CwcA",
            "jjmrxqcQdYg",
            "mnOKdfEwNMQ",
            "8hxYx3Jq3kI",
            "0pVkiod6V0U",
            "4vPeTSRd580",
            "8xLji7WsW0w",
            "92a7Hj0ijLs",
            "ByXuk9QqQkk",
            "l1kuOyZjXDo",
            "JIcjo7XVlOY",
            "OfSaJb5OOPA",
            "r0U83wtmk28",
            "u1pCw1Cr3_U",
            "PAvLwvItvts",
            "GtOCzzLNsOY",
            "vpH42sJ8t9c",
            "DwmxEAWjTQQ",
            "g5xWqjFglsk",
            "_TpTn3o-_Yk"
    };

    public static ArrayList<Movie> generateMovies(String type){
        ArrayList<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++){
            if (types[i].equals(type)){
                Movie movie = new Movie();
                movie.setId(ids[i]);
                movie.setTitle(titles[i]);
                movie.setPoster(posters[i]);
                movie.setCover(covers[i]);
                movie.setScore(scores[i]);
                movie.setType(types[i]);
                movie.setEpisodes(episodes[i]);
                movie.setStatus(statuses[i]);
                movie.setAiredDate(airedDates[i]);
                movie.setGenres(genres[i]);
                movie.setRuntime(runtimes[i]);
                movie.setSynopsis(synopsises[i]);
                movie.setTrailer(trailers[i]);
                movieList.add(movie);
            }
        }
        return movieList;
    }
}
