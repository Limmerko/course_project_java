ymaps.ready(init);


var problemsCoords = document.getElementsByName('problemsCoords');
var problemsId = document.getElementsByName('problemsId');

function init() {
    myMap = new ymaps.Map('map', {
        center: [56.14656449, 40.40288382],
        zoom: 13
    }, {
        searchControlProvider: 'yandex#search'
    });

    for (let i=0; i < problemsCoords.length; i++) {
        let details = "/problems/" + problemsId[i].value;
        console.log(details);
        // Создаем геообъект с типом геометрии "Точка".
        myGeoObject = new ymaps.GeoObject({
            // Описание геометрии.
            geometry: {
                type: "Point",
                coordinates: problemsCoords[i].value.split(",")
            },
            properties: {
                balloonContent: '<div><p><a href="'+details+'">Подробнее</a></p></div>'
            }
        });
        myMap.geoObjects.add(myGeoObject);
    }
}