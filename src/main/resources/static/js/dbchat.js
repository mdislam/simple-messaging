"use strict";

let client = null;

function connect() {
	if (client !== null) {
		return;
	}

	let socket = new SockJS('/chat');
	client = new Stomp.over(socket);

	let headers = {};

	headers[csrfHeader] = csrfToken;
	client.connect(headers, function (frame) {

		client.debug("Subscribing");
		client.subscribe('/subscribe/messages', function(message) {
			showMessage(message.body);
			message.ack();
		});
	});
}


function disconnect() {
	if (client === null) {
		return;
	}

	client.disconnect();
	client = null;
}

function sendMessage(text) {
	if (client === null) {
		return;
	}
	var message = {
		message: text
	};

	client.send('/dbchat/chat', {}, JSON.stringify(message));
}

function showOldMessages() {

	let init = {
		credentials: 'same-origin'	
	};

	fetch("oldmessages", init)
		.then(res => res.json())
		.then(function(json) {
			for (let i in json) {
				showMessage(json[i]);
			}
	});
}

function showMessage(message) {
	let el = document.getElementById("chat-area");

	let messageElement = createMessageElement(message);
	el.innerHTML = el.innerHTML + messageElement;
}

function createMessageElement(message) {
	let obj = null;
	if (typeof message === "string") {
		obj = JSON.parse(message);
	} else {
		obj = message;
	}
	return "<p>" + new Date(obj.sent).toLocaleString() + "  <b>" + obj.sender + "</b>: " + obj.message;
}

function onPressChatHandler(e) {
	let c = e.which || e.keyCode; 
	if (c !== 13) { 
		return; 
	} 
	let el = document.getElementById('messagebox');
	sendMessage(el.value);
	el.value = "";
	
}

