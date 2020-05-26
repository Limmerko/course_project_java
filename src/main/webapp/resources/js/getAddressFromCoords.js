ymaps.ready(init);

var problemsCoords = document.getElementsByName('problemsCoords');
var problemsAddress = document.getElementsByName('problemsAddress');

function init() {
        for (let i=0; i < problemsCoords.length; i++) {
            // Определяем адрес по координатам (обратное геокодирование).
            ymaps.geocode(problemsCoords[i].value.split(",")).then(function (res) {
                var firstGeoObject = res.geoObjects.get(0);
                newAddress = firstGeoObject.getAddressLine();
                problemsAddress[i].innerHTML = newAddress;
            });
        }
}