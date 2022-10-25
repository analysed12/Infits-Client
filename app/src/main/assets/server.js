const app = require('express')()
const server = require('http').Server(app)
var io = require('socket.io')(server)

server.listen(4000,function() {
  console.log("Server is running")
})

const users = {}

var count = 0

io.on('connection', socket => {
  socket.emit('chat-message',"hello world")
  socket.on('new-user', name => {
    users[socket.id] = name
    console.log(name)
    console.log("con")
    socket.broadcast.emit('user-connected', name)

  })
  // socket.on('send-chat-message', message => {
  //   socket.broadcast.emit('chat-message', { message: message, name: users[socket.id] })
  // })
  socket.on('disconnect', () => {
    socket.broadcast.emit('user-disconnected', users[socket.id])
    delete users[socket.id]
    console.log("dis")
  })
})