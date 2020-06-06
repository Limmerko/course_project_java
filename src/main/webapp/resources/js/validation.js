function validation() {
    console.log("validation");
 var address = $('#myInput').val();
 var decs = $('#descText').val();
 var category = $('#category').val();

 if (address.length != 0 && decs.length != 0 && category.length != 0) {
     $('#createBtn').removeAttr('disabled');
 } else {
     $('#createBtn').attr('disabled','disabled');
 }
}