//jQuery和CSS计数器
function count($this){
  	        var current = parseInt($this.html(), 11);
  	        current = current + 1; /* Where 1 is increment */
  	 
  	        $this.html(++current);
  	        if(current > $this.data('count')){
  	            $this.html($this.data('count'));
  	        } else {
  	            setTimeout(function(){count($this)}, 50);
  	        }
}
function stats(){
	jQuery(".stats h4").each(function() {
		   jQuery(this).data('count', parseInt(jQuery(this).html(), 10));
		   jQuery(this).html('0');
		   count(jQuery(this));
	});
}
stats();

/*$(function(){
    var stats_height = $('.stats').offset().top;
    $(window).scroll(function(){
        var this_scrollTop = $(this).scrollTop();
        if(this_scrollTop>stats_height-200 ){
        	stats();
        }
    });
});*/