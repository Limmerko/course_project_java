ymaps.ready(init);

var problemsMainCoords = document.getElementsByName('problemsMainCoords');
var problemsId = document.getElementsByName('problemsId');
var problemsStatus = document.getElementsByName('problemsStatus');
var problemsPhoto = document.getElementsByName('problemsPhoto');
var problemsDate = document.getElementsByName('problemsDate');
var problemsCategory = document.getElementsByName('problemsCategory');

var options = { year: 'numeric', month: 'long', day: 'numeric' };

function init() {
    myMap = new ymaps.Map('map', {
        center: [56.14656449, 40.40288382],
        zoom: 13
    }, {
        searchControlProvider: 'yandex#search'
    });

    for (let i=0; i < problemsMainCoords.length; i++) {
        let details = "/problems/" + problemsId[i].value;
        let now = new Date(problemsDate[i].value);
        // Создаем геообъект с типом геометрии "Точка".
        myGeoObject = new ymaps.GeoObject({
            // Описание геометрии.
            geometry: {
                type: "Point",
                coordinates: problemsMainCoords[i].value.split(",")
            },
            properties: {
                balloonContent: '<div><p><img src='+problemsPhoto[i].value+' style=" width="100px;" height="100px" "/></p></div>' +
                                '<div><p>Статус: '+problemsStatus[i].value+'</p></div>' +
                                '<div><p>Категория: '+problemsCategory[i].value+'</p></div>' +
                                '<div><p>Отправлено: '+now.toLocaleDateString("ru-RU", options)+'</p></div>' +
                                '<div style="margin-top: 5px; text-align:right"><strong><p><a href="'+details+'">Подробнее</a></p></strong></div>'
            }
        });
        myMap.geoObjects.add(myGeoObject);
    }
}