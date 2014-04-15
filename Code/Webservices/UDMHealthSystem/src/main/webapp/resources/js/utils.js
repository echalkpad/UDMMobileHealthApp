// This method is not complete
function clearFormField(field) {
	if (field.type == 'checkbox') {
		field.checked = false;
	} else if (field.type == 'select-multiple' || field.type == 'select-one') {
		$(field).empty();
	} else if (field.type != 'button' && field.type != 'submit') {
		$(field).val('');
	}
}

function displayMessage(text) {
	$('#messageBox p').text(text);
	$('#messageBox').dialog('open');
}