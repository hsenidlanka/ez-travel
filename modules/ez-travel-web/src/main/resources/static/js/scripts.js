var pickupLang;
var pickupLat;
var dropLang;
var dropLat;

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        mapTypeControl: false,
        center: {lat: 6.9271, lng: 79.8612},
        zoom: 13
    });

    new AutocompleteDirectionsHandler(map);
}

/**
 * @constructor
 */
function AutocompleteDirectionsHandler(map) {
    this.map = map;
    this.originPlaceId = null;
    this.destinationPlaceId = null;
    this.travelMode = 'WALKING';
    var start = document.getElementById('pickup');
    var end = document.getElementById('drop');
    this.directionsService = new google.maps.DirectionsService;
    this.directionsDisplay = new google.maps.DirectionsRenderer;
    this.directionsDisplay.setMap(map);

    var originAutocomplete = new google.maps.places.Autocomplete(
        start, {placeIdOnly: false});
    var destinationAutocomplete = new google.maps.places.Autocomplete(
        end, {placeIdOnly: false});

    this.setupPlaceChangedListener(originAutocomplete, 'ORIG');
    this.setupPlaceChangedListener(destinationAutocomplete, 'DEST');

}

/**
 * Sets a listener on a radio button to change the filter type on Places
 * Autocomplete.
 */
AutocompleteDirectionsHandler.prototype.setupClickListener = function (id, mode) {
    var radioButton = document.getElementById(id);
    var me = this;
    radioButton.addEventListener('click', function () {
        me.travelMode = mode;
        me.route();
    });
};

/**
 * pickup and drop location boxes auto complete by giving google's places suggestions
 */
AutocompleteDirectionsHandler.prototype.setupPlaceChangedListener = function (autocomplete, mode) {
    var me = this;
    autocomplete.bindTo('bounds', this.map);
    autocomplete.addListener('place_changed', function () {
        var place = autocomplete.getPlace();
        if (!place.place_id) {
            window.alert("Please select an option from the dropdown list.");
            return;
        }
        if (mode === 'ORIG') {
            me.originPlaceId = place.place_id;
            pickupLang = place.geometry.location.lng();
            pickupLat = place.geometry.location.lat();
            document.getElementById("pickupLat").value = pickupLat;
            document.getElementById("pickupLng").value = pickupLang;
        } else {
            me.destinationPlaceId = place.place_id;
            dropLang = place.geometry.location.lng();
            dropLat = place.geometry.location.lat();
        }
        me.route();
    });

};

/**
 * Draw the root between the pickup and drop location
 */
AutocompleteDirectionsHandler.prototype.route = function () {
    if (!this.originPlaceId || !this.destinationPlaceId) {
        return;
    }
    var me = this;

    this.directionsService.route({
        origin: {'placeId': this.originPlaceId},
        destination: {'placeId': this.destinationPlaceId},
        travelMode: this.travelMode
    }, function (response, status) {
        if (status === 'OK') {
            me.directionsDisplay.setDirections(response);
            distanceInKM = (response.routes[0].legs[0].distance.value) / 1000;
            document.getElementById("length").value = distanceInKM;
        } else {
            window.alert('Directions request failed due to ' + status);
        }

    });
};

