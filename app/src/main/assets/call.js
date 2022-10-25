var localVideo = document.getElementById('video'),vendorUrl = window.URL || window.webkitURL;

localVideo.style.opacity = 0

localVideo.onplaying = () => { localVideo.style.opacity = 1 }

var peer
function init(userId) {
  user = userId
  peer = new Peer(userId, {
      host: '192.168.242.91',
      port: 4001
  })
  listen()
  console.log("Aza4");
}

function listen() {
    console.log("Hi")
   peer.on('call', (call) => {
           call.answer(video.srcObject)
           console.log("Hi from reciver")
            console.log("Hi2")
           call.on('stream', (remoteStream) => {
               video.srcObject = remoteStream
               console.log("Hi for reciver")
           })
       })
   }

function toggleVideo(b) {
   if (b == "true") {
       localStream.getVideoTracks()[0].enabled = true
   } else {
       localStream.getVideoTracks()[0].enabled = false
   }
}

function toggleAudio(b) {
   if (b == "true") {
       localStream.getAudioTracks()[0].enabled = true
   } else {
       localStream.getAudioTracks()[0].enabled = false
   }
}

// peerjs --port 4001 --key peerjs --path /videocallapp