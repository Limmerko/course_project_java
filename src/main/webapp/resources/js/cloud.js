$('.cloudinary-fileupload').bind('cloudinarydone', function(e, data) {
    $('.preview').html(
        $.cloudinary.imageTag(data.result.public_id,
            { format: data.result.format, version: data.result.version,
                crop: 'scale', width: 200 }));
    $('.image_public_id').val(data.result.public_id);
    return true;});

$('.cloudinary-fileupload').bind('cloudinaryprogress', function(e, data) {
    $('.progress_bar').css('width', Math.round((data.loaded * 100.0) / data.total) + '%');});

$(document).ready(function() {
    if($.fn.cloudinary_fileupload !== undefined) {
        $("input.cloudinary-fileupload[type=file]").cloudinary_fileupload();
    }
});


$.cloudinary.config({api_key:"545317982164621", cloud_name: 'konoha', secure: true});

//валидация

$(document).ready(function() {
    $('.cloudinary-fileupload').cloudinary_fileupload({
        disableImageResize: false,
        imageMaxWidth: 800,                   // 800 is an example value - no default
        imageMaxHeight: 600,                  // 600 is an example value - no default
        maxFileSize: 20000000,                // 20 MB is an example value - no default
        loadImageMaxFileSize: 20000000,       // default is 10 MB
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png|bmp|ico)$/i
    });
});