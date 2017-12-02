var http = require('http');
var exec = require('child_process').exec;
var URL = "http://localhost:"
var services = {
		"haproxy": ["8081"],
		"cnvr1": ["3001"],
		"cnvr2": ["3002"],
		"cnvr3": ["3003"]//,
//		"zk":["2181","2182","2183"]
};

var status = {
		"haproxy":true,
		"cnvr1": true,
		"cnvr2": true,
//		"cnvr3": true,
//		"zk": true
};



/**
 * Checks if the given URLs are up
 */
var checkService = function() {
	for (var service in services){
		for (var url in services[service]) {
			this.url = url;
			http.get(URL + services[service][url], function (res) {
	
			   console.log(this.service + " is up: ", res.statusCode, "\n");
			   status[this.service] = true;
	
			}.bind({url,service})).on('error', function(e) {
				console.log(e)
				if (status[this.service] === true ) {
					// If they are down for the first time: attempt to reload vm
				 	status[this.service] = false;
				 	
					console.log(this.service + " is down. Attempting to restart.\n");
					
				 	exec('vagrant reload ' + this.service , function (error, stdout, stderr) {
				 		console.log(stdout);
					  	if (error !== null) {
					    	console.log('exec error: ' + error);
					  	}
					});
				
				} else {
					// If they are still down: do nothing 	
					console.log(this.service + " still down.\n");
				}
			 
			}.bind({url, service}));
		}
	}
	 
}



/**
 * Calls function every 120 seconds
 * 
 */

var TIMER = 12;
checkService();
setInterval(function(){
  checkService();
}, TIMER * 1000);



var counter = TIMER;


/**
 * Prints timeout log
 */
setInterval(function(){
	process.stdout.write("\r\x1b[K")
	counter--;
	process.stdout.write( "Próximo chequeo en :  " + counter +"")
	if (counter == 0) {
		counter = TIMER;
		process.stdout.write("\n\n");
	}
}, 1000);
