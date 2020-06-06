function validation() {
    console.log("validation");
 var address = $('#myInput').val();
 var decs = $('#descText').val();
 var category = $('#category').val();
 console.log(category);

 if (address.length != 0 && decs.length != 0 && !(category ==="-1")) {
     $('#createBtn').removeAttr('disabled');
 } else {
     $('#createBtn').attr('disabled','disabled');
 }
}