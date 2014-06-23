//= require jquery/jquery-1.10.2.min.js
//= require bootstrap
//= require_self
//= require_tree .
//= require_full_tree .

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}