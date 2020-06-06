function validation() {
    console.log("validation");
 var address = $('#myInputBD')[0].defaultValue;
 var decs = $('#descText').val();
 var category = $('#category').val();

 if (address.length != 0 && decs.length != 0 && !(category ==="-1")) {
     $('#createBtn').removeAttr('disabled');
 } else {
     $('#createBtn').attr('disabled','disabled');
 }
}
