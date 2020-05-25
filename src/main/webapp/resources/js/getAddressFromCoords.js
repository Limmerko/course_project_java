function init() {
    var myPlacemark,
        // Создание экземпляра карты и его привязка к контейнеру с заданным id ("map").
        myMap = new ymaps.Map('map', {
            center: [56.14656449, 40.40288382],
            zoom: 13
        }, {
            searchControlProvider: 'yandex#search'
        });
}

console.log("LOOOOOOOOOOOOOOG");
console.log(myInput.value);
var point = new myMap.GeoPoint(myInput.value);

console.log(point);
    getAddress(myInput.value);

    function getAddress(coords) {
        ymaps.geocode(coords).then(function (res) {
            var firstGeoObject = res.geoObjects.get(0);
            address = firstGeoObject.getAddressLine();
            myAddress.value = address;
        });
    }