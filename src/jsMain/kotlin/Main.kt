import kotlinx.browser.document
import react.*
import emotion.react.css
import csstype.Position
import csstype.px
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.img
import react.dom.client.createRoot
import kotlinx.serialization.Serializable

fun main() {
    val container = document.getElementById("root") ?: error("Couldn't find root container!")
    createRoot(container).render(App.create()
//        Fragment.create {
//        h1 {
//            +"KotlinConf Explorer"
//        }
//        div {
//            h3 {
//                +"Videos to watch"
//            }
//            for (video in unwatchedVideos) {
//                p {
//                    +"${video.speaker}: ${video.title}"
//                }
//            }
//            h3 {
//                +"Videos watched"
//            }
//            for (video in watchedVideos) {
//                p {
//                    +"${video.speaker}: ${video.title}"
//                }
//            }
//        }
//        div {
//            css {
//                position = Position.absolute
//                top = 10.px
//                right = 10.px
//            }
//            h3 {
//                +"John Doe: Building and breaking things"
//            }
//            img {
//                src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
//            }
//        }
//    }
    )
}

@Serializable
data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)