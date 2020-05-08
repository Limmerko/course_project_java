<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="css/main-menu.css">
    <link rel="stylesheet" href="css/table-style.css">
    <link rel="stylesheet" href="css/buttonReportProblem.css">

    <script src="../../../../node_modules/jquery/dist/jquery.js" type="text/javascript"></script>
    <script src="../../../../node_modules/jquery.ui/ui/widget.js" type="text/javascript"></script>
    <script src="../../../../node_modules/blueimp-load-image/js/load-image.all.min.js"></script>
    <script src="../../../../node_modules/blueimp-canvas-to-blob/js/canvas-to-blob.min.js"></script>
    <script src="../../../../node_modules/blueimp-file-upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
    <script src="../../../../node_modules/blueimp-file-upload/js/jquery.fileupload.js" type="text/javascript"></script>
    <script src="../../../../node_modules/blueimp-file-upload/js/jquery.fileupload-process.js"></script>
    <script src="../../../../node_modules/blueimp-file-upload/js/jquery.fileupload-image.js" type="text/javascript"></script>
    <script src="../../../../node_modules/blueimp-file-upload/js/jquery.fileupload-validate.js"></script>
    <script src="../../../../node_modules/cloudinary-jquery-file-upload/cloudinary-jquery-file-upload.js" type="text/javascript"></script>

    <script type = "text/javascript">
        $('.cloudinary-fileupload').bind('cloudinarydone', function(e, data) {
            $('.preview').html(
                $.cloudinary.imageTag(data.result.public_id,
                    { format: data.result.format, version: data.result.version,
                        crop: 'scale', width: 200 }));
            $('.image_public_id').val(data.result.public_id);
            return true;});


        $.cloudinary.config({
            "cloud_name": "konoha",
            "api_key": "545317982164621",
            "private_cdn": false,
            "cdn_subdomain": false
        });

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


    </script>


    <title>Проблемы</title>


    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="">Главная</a></li>
        <li><a href="" class="current">Проблемы</a></li>
        <li><a href="">Новости</a></li>
        <li><form action="reportProblem.html">
            <button class="glo" type="submit">Сообщить о проблеме</button>
        </form></li>
    </ul>



</head>
<body>
<h1></h1>
    <c:forEach var="item" items="${problems}">
        ${item}
    </c:forEach>

<h1>Загрузить фотку</h1>

<input name="file" type="file" class="cloudinary-fileupload" data-cloudinary-field="image_upload"
       data-from-data="" />

<button type="submit" class="submit btn">Загрузить</button>



</body>
</html>