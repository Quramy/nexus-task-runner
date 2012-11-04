(function($, nexusTasks) {

	var aveilableTasks = [];

	function split(val) {
		return val.split(/,\s*/);
	}
	function extractLast(term) {
		return split(term).pop();
	}

	function changeAveilable() {
		var selected = $('select.nexus-selection').val();
		if (selected && nexusTasks[selected]) {
		} else {
			selected = $('select.nexus-selection>option:first').val();
		}
		aveilableTasks = nexusTasks[selected] ? nexusTasks[selected] : [];
	}
	
	changeAveilable();

	$('select.nexus-selection').change(function() {
		changeAveilable();
	});

	$('input:text.nexus-task-list').bind(
			"keydown",
			function(event) {
				if (event.keyCode === jQuery.ui.keyCode.TAB
						&& $(this).data("autocomplete").menu.active) {
					event.preventDefault();
				}
			}).autocomplete(
			{
				minLength : 0,
				source : function(request, response) {
					// delegate back to autocomplete, but extract the last term
					response($.ui.autocomplete.filter(aveilableTasks,
							extractLast(request.term)));
				},
				focus : function() {
					// prevent value inserted on focus
					return false;
				},
				select : function(event, ui) {
					var terms = split(this.value);
					// remove the current input
					terms.pop();
					// add the selected item
					terms.push(ui.item.value);
					// add placeholder to get the comma-and-space at the end
					terms.push("");
					this.value = terms.join(", ");
					return false;
				}
			});
})(jQuery, NEXUS_TASKS);