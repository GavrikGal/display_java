var expandDuration = 100;
var measTiles = $('[id*=measTiles]');
var timeoutId;
var currentEvent;

measTiles.data("noSelectedTileStyle", $('.tile').css("box-shadow"));

function select(event) {
	currentEvent = event;
	timeoutId = setTimeout('console.log("Fire");selectTile()', 500);
	
}

function selectTile() {
	console.log("selectTile " + currentEvent.type);
	var tile = $(currentEvent.target).closest('.tile');
	var tiles = $('.tile');
	tiles.not(tile).removeClass("tile-selected");
	tile.toggleClass("tile-selected");
	return false;
}

function expand(event) {
//	clearTimeout(timeoutId);
//	clearTimeout(timeoutId - 1);
//	console.log(event.type);
//	moreSpectrums();
//	loadMoreData();
	var tile = $(event.target).closest('[id*=tile_content]');
	if (tile.length < 1 ) {
		tile = $(event.target).find('[id*=tile_content]');
	}
	console.log("печаль: " + event.target.tagName+ " tile: " + tile.length);
	toggleContentHeight(tile);
	return true;
}

/*$(function() {
    $.contextMenu({
        selector: '.context-menu-one', 
        callback: function(key, options) {
            var m = "clicked: " + key;
            window.console && console.log(m) || alert(m); 
        },
        items: {
            "edit": {name: "Edit", icon: "edit"},
            "cut": {name: "Cut", icon: "cut"},
           copy: {name: "Copy", icon: "copy"},
            "paste": {name: "Paste", icon: "paste"},
            "delete": {name: "Delete", icon: "delete"},
            "sep1": "---------",
            "quit": {name: "Quit", icon: function(){
                return 'context-menu-icon context-menu-icon-quit';
            }}
        }
    });

    $('.context-menu-one').on('click', function(e){
        console.log('clicked', this);
    })    
});*/

//JAVASCRIPT (jQuery)

//Trigger action when the contexmenu is about to be shown
$('.context-menu-one').bind("contextmenu", function (event) {
 
 // Avoid the real one
	 event.preventDefault();
	 
	 // Show contextmenu
	 $(".custom-menu").finish().toggle(100).
	 
	 // In the right position (the mouse)
	 css({
	     top: event.pageY + "px",
	     left: event.pageX + "px"
	 });
});


//If the document is clicked somewhere
$(document).bind("mousedown", function (e) {
 
	 // If the clicked element is not the menu
	 if (!$(e.target).parents(".custom-menu").length > 0) {
	     
	     // Hide it
	     $(".custom-menu").hide(100);
	 }
});


//If the menu element is clicked
$(".custom-menu li").click(function(event){
 
	 // This is the triggered action name
	 switch($(this).attr("data-action")) {
	     
	     // A case for each action. Your actions here
	     case "first": expand(event); break;
	     case "second": alert("second"); break;
	     case "third": alert("third"); break;
	 }
	
	 // Hide it AFTER the action was triggered
	 $(".custom-menu").hide(100);
});

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

function fadeInNewSpectrum(event) {
	var tile = $(event.target).closest('[id*=tile_content]');
	var datagridRows = tile.find('.ui-datagrid-row');
	console.log(datagridRows.length);
	datagridRows.slice(2).hide().fadeIn(1000);
}

function checkContentHeight() {
	var spectrumsContent = $('[id*=spectrums_content]', '[id*=dataTilesForm]');
	spectrumsContent.each(function(index, elem) {
		if($(elem).find('.ui-datagrid-row').length  > 2) {
			toggleContentHeight($(elem).closest('[id*=tile_content]'));
		}
	})

	
}

function toggleContentHeight(tile) {
	var tileImg = $(tile).find('img');
	var datagridRows = $(tile).find('.ui-datagrid-row');
	if($(tile).height() < 90) {
			$(tile).animate({height : "+=50"}, expandDuration);
			if (tileImg.length > 0) {
				$(tileImg).animate({
					height : "+=50",
					width : "+=50"
				}, expandDuration);
			}
		//}
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
