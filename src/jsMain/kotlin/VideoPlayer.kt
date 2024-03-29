import csstype.Display
import csstype.NamedColor
import csstype.Position
import csstype.px
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3

external interface VideoPlayerProps : Props {
    var video: Video
    var unwatchedVideo: Boolean
    var onWatchedButtonPressed: (Video) -> Unit
}

val VideoPlayer = FC<VideoPlayerProps> { props ->
    div {
        css {
            position = Position.absolute
            top = 10.px
            right = 10.px
        }
        h3 {
            +"${props.video.speaker}: ${props.video.title}"
        }
        button {
            css {
                display = Display.block
                backgroundColor = if (props.unwatchedVideo) NamedColor.lightgreen else NamedColor.red
            }
            onClick = {
                props.onWatchedButtonPressed(props.video)
            }
            if (props.unwatchedVideo) {
                +"Mark as watched"
            } else {
                +"Mark as unwatched"
            }
        }
//        img {
//            src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
//        }
        div {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            EmailShareButton {
                url = props.video.videoUrl
                EmailIcon {
                    size = 32
                    round = true
                }
            }
            TelegramShareButton {
                url = props.video.videoUrl
                TelegramIcon {
                    size = 32
                    round = true
                }
            }
            LinkedinShareButton {
                url = props.video.videoUrl
                LinkedinIcon {
                    size = 32
                    round = true
                }
            }
        }
        ReactPlayer {
            url = props.video.videoUrl
            controls = true
        }
    }
}