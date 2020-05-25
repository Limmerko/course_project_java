ymaps.ready(init);

function init() {
        myMap = new ymaps.Map('map', {
            center: [56.14656449, 40.40288382],
            zoom: 13
        }, {
            searchControlProvider: 'yandex#search'
        });

}