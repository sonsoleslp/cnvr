var http = require('http');
var exec = require('child_process').exec;
var URLS = ["http://localhost:8081", "http://localhost:3001",  "http://localhost:3002",  "http://localhost:3003"];
var NAMES = ["LOAD BALANCER", "WEB1", "WEB2", "WEB3"];
var VMS = ["haproxy", "cnvr1", "cnvr2", "cnvr3"];
var status = [true, true, true, true];


/**
 * Checks if the given URLs are up
 */
var checkService = function() {
	for (var url in URLS) {
		this.url = url;
		http.get(URLS[url], function (res) {

		   console.log(NAMES[this.url] + " is up: ", res.statusCode, "\n");
		   status[this.url] = true;

		}.bind({url})).on('error', function(e) {
			
			if (status[this.url] === true ) {
				// If they are down for the first time: attempt to reload vm
			 	status[this.url] = false;
			 	
				console.log(NAMES[this.url] + " is down. Attempting to restart.\n");

			 	exec('vagrant reload ' + VMS[this.url] , function (error, stdout, stderr) {
			 		console.log(stdout);
				  	if (error !== null) {
				    	console.log('exec error: ' + error);
				  	}
				});
			
			} else {
				// If they are still down: do nothing 	
				console.log(NAMES[this.url] + " still down.\n");
			}
		 
		}.bind({url}));
	}
	 
}


/**
 * Calls function every 120 seconds
 * 
 */
setInterval(function(){
  checkService();
}, 120000);