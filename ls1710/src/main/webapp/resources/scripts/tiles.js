var expandDuration = 100;
function expand(event) {
	console.log("expand click");
	var tile = $(event.target).closest('[id*=tile_content]');
	toggleContentHeight(tile);
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

function checkContentHeight() {
	var spectrumsContent = $('[id*=spectrums_content]', '[id*=dataTilesForm]');
	spectrumsContent.each(function(index, elem) {
		if($(elem).find('.ui-datagrid-row').length > 2) {
			toggleContentHeight($(elem).closest('[id*=tile_content]'));
		}
	})

	
}

function toggleContentHeight(tile) {
	var tileImg = $(tile).find('img'); 
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