// handle enter button when
jQuery('#mapsearch').on("keypress", function (e) {
    if (e.keyCode == 13) {
        e.preventDefault();
    }
});

//set map to the corresponding place in html
var map = new google.maps.Map(document.getElementById("map-canvas"), {
    center: {
        lat: 42.7339,
        lng: 25.4858
    },
    zoom: 8
});

$('#map-canvas').width(700).height(300);

//set marker on the map
var marker = new google.maps.Marker({
    position: {
        lat: 42.7339,
        lng: 25.4858
    },
    map:map,
    draggable: true
});

// handle autocomplete in search input
var searchBox = new google.maps.places.SearchBox(document.getElementById('mapsearch'));

// set event for dragging marker by the user and set the lat and lng from new marker location
var lat;
var lng;
google.maps.event.addListener(searchBox, 'places_changed', function () {
    var places = searchBox.getPlaces();
    var bounds = new google.maps.LatLngBounds();

    var i, place;
    for(i = 0; place = places[i]; i++) {
        bounds.extend(place.geometry.location);
        marker.setPosition(place.geometry.location);
    }

    lat = marker.getPosition().lat();
    lng = marker.getPosition().lng();

    $('#lat').val(lat);
    $('#lng').val(lng);

    map.fitBounds(bounds);
    map.setZoom(12);
});

// set into the html field the new lat and lng after marker is dragged
google.maps.event.addListener(marker, 'dragend', function () {
    lat = marker.getPosition().lat();
    lng = marker.getPosition().lng();

    $('#lat').val(lat);
    $('#lng').val(lng);
});

// add and preview image script
function filePreview(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var file = e.target;
            $("<span class=\"pip\">" +
                "<img class=\"mainImg\" src=\"" + e.target.result + "\" title=\"" + file.name + "\"/>" +
                "<span class=\"remove\">Изтрий</span>" +
                "</span>").insertAfter("#file");
            $(".remove").click(function(){
                $(this).parent(".pip").remove();
            });
        };
        reader.readAsDataURL(input.files[0]);
    }
    $('#images').css("margin-top", 240);
}

$("#file").change(function () {
    filePreview(this);
});

var margin = 140;
$(document).ready(function() {
    if (window.File && window.FileList && window.FileReader) {
        $("#files").on("change", function(e) {
            var files = e.target.files,
                filesLength = files.length;
            for (var i = 0; i < filesLength; i++) {
                var f = files[i];
                var fileReader = new FileReader();
                fileReader.onload = (function(e) {
                    var file = e.target;
                    $("<span class=\"pip\">" +
                        "<img class=\"imageThumb\" src=\"" + e.target.result + "\" title=\"" + file.name + "\"/>" +
                        "<span class=\"remove\">Изтрий/Промени</span>" +
                        "</span>").insertAfter("#files");
                    $(".remove").click(function(){
                        $(this).parent(".pip").remove();
                    });

                    var imageThumbCount = $('.imageThumb').length;

                    if (imageThumbCount % 3 === 0) {
                        margin += 140;
                    }
                    $('#submitBtn').css('margin-top', margin);

                });
                fileReader.readAsDataURL(f);
            }
        });
    } else {
        alert("Твоят браузър не поддържа такъв формат файл!")
    }
});

// $(function () {
//     $.each('form').on('submit', function (e) {
//         $.ajax({
//             type: 'post',
//             url: '/',
//             data: $(this).serialize(),
//             success: function () {
//                 location.reload();
//             }
//         });
//         e.preventDefault();
//     });
// });

