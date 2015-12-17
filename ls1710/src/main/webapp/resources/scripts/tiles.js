var expandDuration = 100;
function expand(event) {
	console.log("expand click");
	var tile = $(event.target).closest('[id*=tile_content]');
	var tileImg = $(tile).find('img'); 
	console.log(tile);
	if($(tile).height() < 90) {
		$(tile).animate({height : "+=50"}, expandDuration);
		if (tileImg.length > 0) {
			$(tileImg).animate({
				height : "+=50",
				width : "+=50"
			}, expandDuration);
		}
	} else {
		$(tile).animate({height : "-=50"}, expandDuration);
		if (tileImg.length > 0) {
			$(tileImg).animate({
				height : "-=50",
				width : "-=50"
			}, expandDuration);
		}
	}
}

function cleanDate() {
	var lastDate;
	$('.date').each(function(index,elem) {
		var currentDate = $(elem).text();
		if(lastDate != currentDate) {
			lastDate = currentDate;
		} else {
			$(elem).remove();
		}
	})
}