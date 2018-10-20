var $$ContextURL = "/bhms";


$.datepicker.setDefaults({ changeMonth: true,changeYear: true,yearRange: "-100:+10"});

function cusConfirm() {
	var deferred=$.Deferred();
	console.log("cusConfirm->fired");
	$('<div></div>').appendTo('body').html(`
	<p>
	Are you sure ?
	</p>
	`)
			.dialog({
				title : 'Confirm window',
				zIndex : 10000,
				autoOpen : true,
				width : '300',
				resizable : false,
				buttons : {
					"Yes" : function() {
						deferred.resolve(true);
						$(this).dialog("close");
					},
					"No" : function() {
						$(this).dialog("close");
					}
				},
				close : function(event, ui) {
					$(this).remove();
				}
			});
	return deferred.promise();
}


function cusPF(input){
     // custom prevent Eclipse formating ${variable} inside Javascipt code
	return input;
}


// Prevent Submit Form via enter
$(document).on("keypress", "form", function(event) {
	return event.keyCode != 13;
});