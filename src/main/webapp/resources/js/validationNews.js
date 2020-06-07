function validationNews() {
    console.log("validation");
    var decs = $('#descText').val();
    var title = $('#title').val();


    if (title.length != 0 && decs.length != 0) {
        $('#createBtn').removeAttr('disabled');
    } else {
        $('#createBtn').attr('disabled','disabled');
    }

}