import kotlinx.coroutines.async
import react.*
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.dom.html.ReactHTML.h3

val mainScope = MainScope()

val App = FC<Props> {
    var currentVideo: Video? by useState(null)
    var unwatchedVideos: List<Video> by useState(emptyList())
    var watchedVideos: List<Video> by useState(emptyList())

    useEffectOnce {
        mainScope.launch {
            unwatchedVideos = fetchVideos()
        }
    }
//    var unwatchedVideos: List<Video> by useState(listOf(
//        Video(1, "Opening Keynote", "Andrey Breslav", "https://youtu.be/PsaFVLr8t4E"),
//        Video(2, "Dissecting the stdlib", "Huyen Tue Dao", "https://youtu.be/Fzt_9I733Yg"),
//        Video(3, "Kotlin and Spring Boot", "Nicolas Frankel", "https://youtu.be/pSiZVAeReeg")
//    ))
//
//    var watchedVideos: List<Video> by useState(listOf(
//        Video(4, "Creating Internal DSLs in Kotlin", "Venkat Subramaniam", "https://youtu.be/JzTeAM8N1-o")
//    ))

    h3 {
        +"Videos to watch"
    }
    VideoList {
        videos = unwatchedVideos
        selectedVideo = currentVideo
        onSelectVideo = { video ->
            currentVideo = video
        }
    }
    h3 {
        +"Videos watched"
    }
    VideoList {
        videos = watchedVideos
        selectedVideo = currentVideo
        onSelectVideo = { video ->
            currentVideo = video
        }
    }
    currentVideo?.let { curr ->
        VideoPlayer {
            video = curr
            unwatchedVideo = curr in unwatchedVideos
            onWatchedButtonPressed = {
                if (video in unwatchedVideos) {
                    unwatchedVideos = unwatchedVideos - video
                    watchedVideos = watchedVideos + video
                } else {
                    watchedVideos = watchedVideos - video
                    unwatchedVideos = unwatchedVideos + video
                }
            }
        }
    }
}

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

suspend fun fetchVideo(id: Int): Video {
    val response = window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}
