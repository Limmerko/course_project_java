ymaps.ready(init);


var problemsCoords = document.getElementsByName('problemsCoords');
var problemsId = document.getElementsByName('problemsId');
var problemsStatus = document.getElementsByName('problemsStatus');
var problemsPhoto = document.getElementsByName('problemsPhoto');
var problemsDate = document.getElementsByName('problemsDate');

var options = { year: 'numeric', month: 'long', day: 'numeric' };

console.log(today.toLocaleDateString("en-US")); // 9/17/2016
console.log(today.toLocaleDateString("en-US", options));

function init() {
    myMap = new ymaps.Map('map', {
        center: [56.14656449, 40.40288382],
        zoom: 13
    }, {
        searchControlProvider: 'yandex#search'
    });

    for (let i=0; i < problemsCoords.length; i++) {
        let details = "/problems/" + problemsId[i].value;
        let now = new Date(problemsDate[i].value);
        // Создаем геообъект с типом геометрии "Точка".
        myGeoObject = new ymaps.GeoObject({
            // Описание геометрии.
            geometry: {
                type: "Point",
                coordinates: problemsCoords[i].value.split(",")
            },
            properties: {
                balloonContent: '<div><p><img src='+problemsPhoto[i].value+' style=" width="100px;" height="100px" "/></p></div>' +
                                '<div><p>Статус: '+problemsStatus[i].value+'</p></div>' +
                                '<div><p>Отправлено: '+now.toLocaleDateString("ru-RU", options)+'</p></div>' +
                                '<div><p><a href="'+details+'">Подробнее</a></p></div>'
            }
        });
        myMap.geoObjects.add(myGeoObject);
    }
}