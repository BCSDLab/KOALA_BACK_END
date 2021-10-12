var stompClient = null;
var currentRoom = null;
var subscription = null;

function setRoom(roomName) {
    currentRoom = roomName.text();
    $("#currentRoom").text(currentRoom);

    if(subscription !== null) {
        subscription.unsubscribe();
        subscription = stompClient.subscribe('/sub/' + currentRoom, function (chat) {
            showChat(JSON.parse(chat.body).sender + " : " + JSON.parse(chat.body).message);
        });
        $("#chats").html("");
    }

}

function setConnected(connected) {

    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#name").attr("disabled" , true);
    }
    else {
        $("#name").attr("disabled" , false);
    }
}

function connect() {

    if(!validation()){
        console.log("실패");
        return
    }

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        subscription = stompClient.subscribe('/sub/' + currentRoom, function (chat) {
            showChat(JSON.parse(chat.body).sender + " : " + JSON.parse(chat.body).message);
        });
    });

}

function validation() {

    if($("#name").val() === "") {
        alert("이름을 입력해주세요");
        return false;
    }

    if(currentRoom === null) {
        alert("방을 선택해주세요");
        return false;
    }

    return true;
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
}

function send() {
    var name = $("#name").val();
    var content = $("#content").val();
    stompClient.send("/pub/chat/message" , {}, JSON.stringify({sender: name, message: content}));
    $("#content").val("");
}

function showChat(message) {
    $("#chats").append("<tr><td colspan='2'>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {e.preventDefault();});
    $("#connect").click(function() { connect(); });
    $("#disconnect").click(function() { disconnect(); });
    $("#send").click(function() { send(); });
    $("a[href=\\#]").click(function() {setRoom($(this))})
});