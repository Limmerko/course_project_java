ymaps.ready(init);


var problemsCoords = document.getElementsByName('promlemsCoords');

function init() {
    myMap = new ymaps.Map('map', {
        center: [56.14656449, 40.40288382],
        zoom: 13
    }, {
        searchControlProvider: 'yandex#search'
    });

    for (let i=0; i< problemsCoords.length; i++) {
        // Создаем геообъект с типом геометрии "Точка".
        myGeoObject = new ymaps.GeoObject({
            // Описание геометрии.
            geometry: {
                type: "Point",
                coordinates: problemsCoords[i].value.split(",")
            }
        });
        myMap.geoObjects.add(myGeoObject);
    }
}