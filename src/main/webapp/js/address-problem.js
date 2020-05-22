ymaps.ready(init);

    var coords = document.getElementById("prob-coords").value;
    getAddress(coords);

// Определяем адрес по координатам (обратное геокодирование).
function getAddress(coords) {
    ymaps.geocode(coords).then(function (res) {
        var GeoObject = res.geoObjects.get(0);
        address = GeoObject.getAddressLine();

        addressProbs.value = address;
    });
}