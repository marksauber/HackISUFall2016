//---------------------------------------------------------------
// The purpose is to introduce you to websockets
// This is a SERVER that is SEPARATE from the http server.
//
// Your webpage (in this case the index.html in this directory)
// will be SERVED by the http server. THEN, it will connect to the
// websocket server. Then - they will talk to each other!
//
// Note that in regular http - the server cannot initiate a conversation
// Here, the websocket server sends a message to the client browser.
//
// This example has THREE parts
// 1) The http server code (which is same as what we did earlier)
// 2) This code - this is the web socket server
// It prints what it got from client. It also sends a message to the
// client after every 1 second.
// 3) The html or client code. Note how it connects to the websocket
// and how it sends and receives messages
//
// To RUN THIS EXAMPLE
// First, run node httpServer.js on one terminal
// Next, run node 1_ws.js on another terminal
// Next, type localhost:4000/index.html on some browser
//
//---------------------------------------------------------------
var io = require('socket.io').listen(5000);

var characters =
	{
		/* Gud guise */
		'Merlin':
		{
			isBad: false,
			knows:
			[
				'Morgana',
				'Assassin',
				'Oberon',
				'Dark Helmet',
				'Dark Christmas Tree',
				'Knife Dude'
			]
		},
		'Percival':
		{
			isBad: false,
			knows:
			[
				'Merlin',
				'Morgana'
			]
		},
		'Sweaty-B':
		{
			isBad: false,
			knows: []
		},
		'Light Helmet':
		{
			isBad: false,
			knows: []
		},
		'Land-o\'-Lakes Lady':
		{
			isBad: false,
			knows: []
		},
		'Lady Dank':
		{
			isBad: false,
			knows: []
		},
		'Christmas Tree':
		{
			isBad: false,
			knows: []
		},
		'Good Lancelot':
		{
			isBad: false,
			knows: []
		}
		/* Bed guise */
		'Mordred':
		{
			isBad: true,
			knows:
			[
				'Morgana',
				'Assassin',
				'Dark Helmet',
				'Dark Christmas Tree',
				'Knife Dude'
			]
		},
		'Morgana':
		{
			isBad: true,
			knows:
			[
				'Mordred',
				'Assassin',
				'Dark Helmet',
				'Dark Christmas Tree',
				'Knife Dude'
			]
		},
		'Assassin':
		{
			isBad: true,
			knows:
			[
				'Mordred',
				'Morgana',
				'Dark Helmet',
				'Dark Christmas Tree',
				'Knife Dude'
			]
		},
		'Oberon':
		{
			isBad: true,
			knows:
			[
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet',
				'Dark Christmas Tree',
				'Knife Dude'
			]
		},
		'Dark Helmet':
		{
			isBad: true,
			knows:
			[
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Christmas Tree',
				'Knife Dude'
			]
		},
		'Dark Christmas Tree':
		{
			isBad: true,
			knows:
			[
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet',
				'Knife Dude'
			]
		},
		'Knife Dude':
		{
			isBad: true,
			knows:
			[
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet',
				'Dark Christmas Tree'
			]
		}
	};

var scenarios =
	[
		{
			players: 5,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Mordred',
				'Morgana'
			],
			assassin: 'Morgana',
			quests:
			[
				{
					send: 2,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 2,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				}
			]
		},
		{
			players: 6,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Mordred',
				'Morgana'
			],
			assassin: 'Morgana',
			quests:
			[
				{
					send: 2,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				}
			]
		},
		{
			players: 7,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Mordred',
				'Morgana',
				'Assassin'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 2,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 2
				},
				{
					send: 4,
					fail: 1
				}
			]
		},
		{
			players: 8,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Lady Dank',
				'Mordred',
				'Morgana',
				'Assassin'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 5,
					fail: 2
				},
				{
					send: 5,
					fail: 1
				}
			]
		},
		{
			players: 9,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Lady Dank',
				'Christmas Tree',
				'Mordred',
				'Morgana',
				'Assassin'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 5,
					fail: 2
				},
				{
					send: 5,
					fail: 1
				}
			]
		},
		{
			players: 10,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Lady Dank',
				'Christmas Tree',
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 5,
					fail: 2
				},
				{
					send: 5,
					fail: 1
				}
			]
		},
		{
			players: 11,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Lady Dank',
				'Christmas Tree',
				'Land-o\'-Lakes Lady',
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 3,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 4,
					fail: 2
				},
				{
					send: 5,
					fail: 1
				},
				{
					send: 6,
					fail: 2
				},
				{
					send: 7,
					fail: 1
				}
			]
		},
		{
			players: 12,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Lady Dank',
				'Christmas Tree',
				'Land-o\'-Lakes Lady',
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet',
				'Dark Christmas Tree'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 3,
					fail: 1
				},
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 4,
					fail: 2
				},
				{
					send: 5,
					fail: 1
				},
				{
					send: 6,
					fail: 2
				},
				{
					send: 7,
					fail: 1
				}
			]
		},
		{
			players: 13,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Lady Dank',
				'Christmas Tree',
				'Land-o\'-Lakes Lady',
				'Good Lancelot',
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet',
				'Dark Christmas Tree'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 5,
					fail: 2
				},
				{
					send: 6,
					fail: 1
				},
				{
					send: 7,
					fail: 2
				},
				{
					send: 7,
					fail: 1
				}
			]
		},
		{
			players: 14,
			characters:
			[
				'Merlin',
				'Percival',
				'Sweaty-B',
				'Light Helmet',
				'Lady Dank',
				'Christmas Tree',
				'Land-o\'-Lakes Lady',
				'Good Lancelot',
				'Mordred',
				'Morgana',
				'Assassin',
				'Dark Helmet',
				'Dark Christmas Tree',
				'Knife Dude'
			],
			assassin: 'Assassin',
			quests:
			[
				{
					send: 3,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 4,
					fail: 1
				},
				{
					send: 5,
					fail: 2
				},
				{
					send: 6,
					fail: 1
				},
				{
					send: 7,
					fail: 2
				},
				{
					send: 7,
					fail: 1
				}
			]
		}
	];
// sockets.on('disconnect')
io.sockets.on('connection', function(socket) {
  socket.on('startGame', function(content) {
    console.log(content);
    socket.emit('server', "This is the server: got your message");
   
  });
    socket.on('finish', function(score) {
   
    if (checkNewLeaderboard(score)) {
			socket.emit('getName', 0);
			socket.on('givenName', function (name) {     
					var i;
					for( i=0;i<leaderboard.length;i++) {
							if(leaderboard[i].score<=score) {
									var obj = {};
									obj["name"] = name;
									obj["score"] = score;
									leaderboard.splice(i, 0, obj);
									console.log(leaderboard);
									socket.emit('newLeaderboard',leaderboard);
									break;
							}
					}
			});
		}
  });
});