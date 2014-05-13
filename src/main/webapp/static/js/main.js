$(document).ready(function() {
    // check username availability on focus lost
    $('#username').blur(function() {
        checkAvailability();
    });
});
 
function checkAvailability() {
    $.getJSON("register/checkname", { username: $('#username').val() }, function(availability) {
    	$('#username.errors').remove();
    	if (availability.available) {
        	$('#username').after('<span id="username.errors">Ok</span>');	
        } else {
        	$('#username').after('<span id="username.errors">User already exists. Use another login: </span>' + availability.suggestions[0]);
        }
    });
}

function voteQuote(quoteId, choice) {
    $.getJSON(baseUrl() + "/quote/" + quoteId + "/" + choice, {}, function(currentVote) {
    	if (!isNaN(currentVote)) {
    		$("#" + quoteId).text(currentVote);
        }
    });
}

function baseUrl() {
	var appName = location.pathname.split("/")[1];
	appName == undefined ? appName = "" : appName = "/" + appName;
	return location.protocol + "//" + location.host + appName;
}