$(function() {
	$('#header .customer').on('mouseenter', function() {
		$('#header').addClass('on');
	})
	.on('mouseleave', function() {
		$('#header').removeClass('on');
	});
});