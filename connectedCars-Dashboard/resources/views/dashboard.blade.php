
<!DOCTYPE html>
<html>
<head>	
	<title>Dashboard</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" type="image/x-icon" href="docs/images/favicon.ico" />
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.4.0/dist/leaflet.css" integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA==" crossorigin=""/>
  <script src="https://unpkg.com/leaflet@1.4.0/dist/leaflet.js" integrity="sha512-QVftwZFqvtRNi0ZyCtsznlKSWOStnDORoefr1enyq5mVL4tmKB3S/EnC3rRJcxCPavG10IcrVGSmPh6Qw5lwrg==" crossorigin=""></script>	
</head>
<body>
  <div id="mapid" style="width: 1345px; height: 608px;"></div>
  <script type="text/javascript">
        setTimeout(function(){
            location.reload();
        },5000);
  </script>
  <script>
    var mymap = L.map('mapid').setView([51.505, -0.09], 13);

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
      maxZoom: 18,
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
      id: 'mapbox.streets'
    }).addTo(mymap);

    <?php
      $last = array();
      $readings = array();
      $redis = app()->make('redis');
      $data =  $redis->rpop("data");
      if ($data)
      {
        $redis->set($data[0],$data);
      }
      for ($x = 1; $x <= 8; $x++)
      {
        $car = $redis->get($x);
        $car = explode (",", $car)
    ?>
      L.marker(
        <?php
        echo
        '['.$car[5].','.$car[6].']';
        ?>
        ).addTo(mymap)
       .bindPopup(
        <?php
        echo '"'.'VIN: '.$car[0].'<br>'
                .'speed: '.$car[2].'<br>'
                .'fuel level: '.$car[3].'<br>'
                .'temperature: '.$car[4].'<br>'
                .'"';
        ?>
      ).openPopup();
    <?php    
  }
    ?>

    var popup = L.popup();

    function onMapClick(e) {
      popup
        .setLatLng(e.latlng)
        .setContent("You clicked the map at " + e.latlng.toString())
        .openOn(mymap);
    }

    mymap.on('click', onMapClick);
  </script>
</body>
</html>