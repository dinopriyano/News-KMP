package com.dino.newskmp.feature.news.data

import com.dino.newskmp.feature.news.domain.model.News
import com.dino.newskmp.feature.news.domain.model.Source
import news_kmp.composeapp.generated.resources.*
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.avatar_one
import news_kmp.composeapp.generated.resources.avatar_three
import news_kmp.composeapp.generated.resources.avatar_two
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.random.Random


/**
 * Created by dinopriyano on 11/11/23.
 */

@OptIn(ExperimentalResourceApi::class)
object DummyData {
    val categories = listOf(
        "Trending", "Health", "Sports", "Finance", "Technology", "Lifestyle"
    )

    val newsList = listOf(
        News(
            source = Source(id = null, name = "NBC Southern California"),
            author = "Eric Leonard, Willian Avila",
            title = "‘Started seeing body parts': Day laborers say Tarzana murder suspect hired them to move human remains - NBC Southern California",
            description = "A group of day laborers said they were paid $500 to help move trash bags containing body parts out of a Tarzana home at the center of a murder investigation.",
            url = "http://www.nbclosangeles.com/news/local/day-laborers-hired-by-tarzana-murder-suspect-to-move-body-parts-human-remains-encino/3266061/",
            urlToImage = "https://media.nbclosangeles.com/2023/11/image-34-1.png?resize=1200%2C675&quality=85&strip=all",
            publishedAt = "2023-11-11T05:54:09Z",
            content = "A group of day laborers said they were hired to help move trash bags containing body parts out of a Tarzana home at the center of a murder investigation.\r\nIn an exclusive interview with NBC4 on Frida… [+2852 chars]"
        ),
        News(
            source = Source(id = "usa-today", name = "USA Today"),
            author = "Doc Louallen",
            title = "Mega Millions winning numbers for Nov. 10 drawing - USA TODAY",
            description = "Friday's Mega Millions jackpot sits at $223 million after no winner Tuesday. Here are the winning numbers.",
            url = "https://www.usatoday.com/story/money/2023/11/10/mega-millions-winning-numbers/71537814007/",
            urlToImage = "https://www.usatoday.com/gcdn/authoring/authoring-images/2023/08/01/USAT/70509836007-usatsi-21125108.jpg?crop=2822,1594,x0,y0&width=2822&height=1594&format=pjpg&auto=webp",
            publishedAt = "2023-11-11T04:59:49Z",
            content = "Tuesday's Mega Millions drawing didn't produce any winners and as a result, the jackpot has increased to a substantial $223 million prize for Friday's draw.\r\nThe numbers were drawn just after 11 p.m.… [+2394 chars]"
        ),
        News(
            source = Source(id = null, name = "Sports Illustrated"),
            author = "Frankie Taddeo",
            title = "Tom Brady Echoes Michigan Players With Defiant Response to Jim Harbaugh Suspension - Sports Illustrated",
            description = "The former Wolverines quarterback joined the chorus of current players to show his support for the program.",
            url = "https://www.si.com/college/2023/11/11/tom-brady-echoes-michigan-players-defiant-response-jim-harbaugh-suspension",
            urlToImage = "https://www.si.com/.image/ar_1.91%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_1200/MTk4NDUwNDU4MjkxODczNjIx/tom-brady.jpg",
            publishedAt = "2023-11-11T04:25:32Z",
            content = "On Friday, several Michigan star players responded to the Big Ten’s announcement that Jim Harbaugh has been suspended for the remainder of the regular season with a one-word message.\r\nThe No. 3 Wolve… [+1127 chars]"
        ),
        News(
            source = Source(id = "al-jazeera-english", name = "Al Jazeera English"),
            author = "Al Jazeera",
            title = "Israeli forces close in on al-Shifa Hospital, trapping thousands - Al Jazeera English",
            description = "Thousands of people are holed up in Gaza’s largest hospital, which Israel claims is a Hamas command centre.",
            url = "https://www.aljazeera.com/news/2023/11/11/israeli-forces-close-in-on-al-shifa-hospital-trapping-thousands",
            urlToImage = "https://www.aljazeera.com/wp-content/uploads/2023/11/2023-11-07T142409Z_236973342_RC2E84AH54H7_RTRMADP_3_ISRAEL-PALESTINIANS-1699673594.jpg?resize=1200%2C675",
            publishedAt = "2023-11-11T03:54:47Z",
            content = "Israel's military has closed in on the front gates of Gaza's biggest hospital, where thousands of injured and displaced people are trapped amid intensive Israeli bombardment.\r\nIsraeli forces had fully … [+3532 chars]"
        ),
        News(
            source = Source(id = "deadline", name = "Deadline"),
            author = "Dominic Patten",
            title = "SAG-AFTRA Reveals More On Wage Increases & Other Details Of “Landmark” Deal With Studios; Ratification Vote Set To Start Tuesday - Deadline",
            description = "Following a vote Friday by the majority of SAG-AFTRA’s National Board to approve a tentative agreement reached with studio CEOs and the AMPTP earlier this week, the actors guild has released …",
            url = "https://deadline.com/2023/11/actors-strike-deal-details-sag-aftra-1235599842/",
            urlToImage = "https://deadline.com/wp-content/uploads/2023/11/sag-2.jpg?w=1024",
            publishedAt = "2023-11-11T03:32:00Z",
            content = "Following a vote Friday by the majority of SAG-AFTRA‘s National Board to approve a tentative agreement reached with studio CEOs and the AMPTP earlier this week, the actors guild has released more det… [+7759 chars]"
        ),
        News(
            source = Source(id = "eonline", name = "Eonline.com"),
            author = "Gabrielle Chung",
            title = "Khloe Kardashian Gives Inside Look at Birthday Party for Niece Dream - E! NEWS",
            description = "Khloe Kardashian threw Rob Kardashian and Blac Chyna's daughter Dream a beauty parlor-themed party for her seventh birthday. See photos from the lavish bash.",
            url = "https://www.eonline.com/news/1389710/khloe-kardashian-gives-inside-look-at-7th-birthday-party-for-niece-dream-kardashian",
            urlToImage = "https://akns-images.eonline.com/eol_images/Entire_Site/20231010/cr_1200x1200-231110182217-400470297_3669909199996999_2783559035221052467_n.jpg?fit=around%7C1080:1080&output-quality=90&crop=1080:1080;center,top",
            publishedAt = "2023-11-11T03:00:00Z",
            content = "Now this a dream party.\r\nKhloe Kardashian went all out to celebrate her niece Dream Kardashian's seventh birthday, throwing an extravagant bash centered around all-things beauty.\r\nTo kick off the Nov… [+1039 chars]"
        ),
        News(
            source = Source(id = "youtube", name = "YouTube"),
            author = "ACC Digital Network",
            title = "Arizona vs. Duke Condensed Game | 2023-24 ACC Men’s Basketball - ACC Digital Network",
            description = "No. 2 Duke fell to No. 12 Arizona, 78-73 in Cameron Indoor Stadium. Kyle Filipowski led all scorers with a big 25 points and 8 rebounds in the loss, while Je...",
            url = "https://www.youtube.com/watch?v=obxNh2QGXMQ",
            urlToImage = "https://i.ytimg.com/vi/obxNh2QGXMQ/maxresdefault.jpg",
            publishedAt = "2023-11-11T02:32:40Z",
            content = "The exciting game between Arizona and Duke concluded with Arizona taking the win by a small margin. Key players from both teams performed exceptionally, but Arizona clinched the game in the end."
        ),
        News(
            source = Source(id = "youtube", name = "YouTube"),
            author = "KTVU FOX 2 San Francisco",
            title = "APEC, where world leaders will meet face to face, gets underway Saturday - KTVU FOX 2 San Francisco",
            description = "Preparations for the Asia Pacific Economic Cooperation summit (APEC) are in full swing. The conference ramps up next week, but the city is already preparing ...",
            url = "https://www.youtube.com/watch?v=7oTJEarAHuo",
            urlToImage = "https://i.ytimg.com/vi/7oTJEarAHuo/hqdefault.jpg",
            publishedAt = "2023-11-11T02:28:29Z",
            content = "The APEC summit is set to bring together leaders from across the Asia Pacific region to discuss economic policies and strategies for cooperation. Preparations are well underway to ensure a successful and productive event."
        ),
        News(
            source = Source(id = "politico", name = "Politico"),
            author = "Staff Writer",
            title = "U.S. debt in danger of downgrade by Moody's as shutdown looms - POLITICO",
            description = "The credit rating firm cited risks to the U.S. fiscal outlook — namely, higher interest rates “without effective fiscal policy measures to reduce government spending or increase revenues.”",
            url = "https://www.politico.com/news/2023/11/10/u-s-debt-in-danger-of-downgrade-by-moodys-as-shutdown-looms-00126680",
            urlToImage = "https://static.politico.com/f8/cd/92ddec7a41f6904ad5438608982a/c8cc72f5-f63e-4ad7-8b36-2e4412fd71ca-hako8.JPG",
            publishedAt = "2023-11-11T01:56:53Z",
            content = "In light of the upcoming government shutdown, Moody's has warned of a potential downgrade to the U.S. debt rating. This could lead to an increase in borrowing costs, impacting the already strained U.S. fiscal situation."
        ),
        News(
            source = Source(id = "cbs-news", name = "CBS News"),
            author = "Mark Strassmann",
            title = "Demand for RSV shot is so high, CDC is recommending it only for the most vulnerable - CBS News",
            description = "Demand for the Beyfortus antibody shot has been so high that the CDC recommends doctors give it only to their most vulnerable patients.",
            url = "https://www.cbsnews.com/news/rsv-beyfortus-antibody-shot-demand-supply-respiratory-syncytial-virus/",
            urlToImage = "https://assets1.cbsnewsstatic.com/hub/i/r/2023/11/11/7eada018-d17d-4918-ae65-5890e5723fb7/thumbnail/1200x630/78bdfdccecd0f03075229feaee7f2db3/1112-en-strassmann.jpg?v=8afad5e29ac9a704fff9d98ba34d3649",
            publishedAt = "2023-11-11T01:25:19Z",
            content = "Atlanta — Lucas Cerna's mother is getting him a respiratory syncytial virus shot while his pediatrician still has it in stock. \r\n\"I have just been seeing a lot in the news, and babies get really sick… [+2061 chars]"
        )
    )

    private val avatars = listOf(
        Res.drawable.avatar_one,
        Res.drawable.avatar_two,
        Res.drawable.avatar_three,
        Res.drawable.avatar_four,
        Res.drawable.avatar_five,
        Res.drawable.avatar_six
    )

    fun getRandomAvatar(): DrawableResource {
        return avatars[Random.nextInt(0, 5)]
    }
}