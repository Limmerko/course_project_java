ymaps.ready(init);

var problemInputEdit = document.getElementsByName('problemInputEdit');
var coords = problemInputEdit[0].value.split(",");

function init() {
    var myPlacemark,
    myMap = new ymaps.Map('map', {
        center: coords,
        zoom: 13
    }, {
        searchControlProvider: 'yandex#search'
    });

    myPlacemark = createPlacemark(coords);
    myMap.geoObjects.add(myPlacemark);
    getAddress(myPlacemark.geometry.getCoordinates());
    console.log("Логи: " + myMap.geoObjects.toString());
    // Клик на карте.
    myMap.events.add('click', function (e) {
       myMap.geoObjects.remove(myPlacemark);
       var coords = e.get('coords');
        if (myPlacemark) {
            myPlacemark.geometry.setCoordinates(coords);
        }
        myMap.geoObjects.add(myPlacemark);
            // Событие окончания перетаскивания на метке.
            myPlacemark.events.add('dragend', function () {
                getAddress(myPlacemark.geometry.getCoordinates());
            });
        getAddress(coords);
        myInputBD.value = coords;
    });

    // Создание метки.
    function createPlacemark(coords) {
        return new ymaps.Placemark(coords, {
            iconCaption: 'поиск...'
        }, {
            draggable: true
        });
    }


    // Определяем адрес по координатам (обратное геокодирование).
    function getAddress(coords) {
        myPlacemark.properties.set('iconCaption', 'поиск...');
        ymaps.geocode(coords).then(function (res) {
            var firstGeoObject = res.geoObjects.get(0);
            address = firstGeoObject.getAddressLine();

            myPlacemark.properties
                .set({
                    // Формируем строку с данными об объекте.
                    iconCaption: [
                        // Название населенного пункта или вышестоящее административно-территориальное образование.
                        firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                        // Получаем путь до топонима, если метод вернул null, запрашиваем наименование здания.
                        firstGeoObject.getAddressLine() || firstGeoObject.getPremise()
                    ].filter(Boolean).join(', '),
                    // В качестве контента балуна задаем строку с адресом объекта.
                    balloonContent: firstGeoObject.getAddressLine()
                });
            problemInput.value = address;
        });
    }

}