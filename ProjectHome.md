GMaps Helper aims to make easier to execute Google Maps web services.

Example 1, get latitude and longitude:
```
final Geocoder geocoder = Geocoder.getInstance();
Address address = new Address("Rúa do Rouco, Nº 2, 36002, Pontevedra, Pontevedra");
geocoder.geocode(address);
System.out.println("Address latitude: " + address.getLatitude());
System.out.ptintln("Address longitude: " + address.getLongitude());
```

Example 2, get address from latitude and longitude:
```
final Geocoder geocoder = Geocoder.getInstance();
Address address = geocoder.reverseGeocode(-27.124841, -109.372673);
System.out.println("Hello " + address.getLocality());
```

The only dependency in order to use this library is log4j.