import {Component, Inject, OnInit, PLATFORM_ID} from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import Overlay from 'ol/Overlay';
import { fromLonLat } from 'ol/proj';
import {isPlatformBrowser} from "@angular/common";

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [],
  templateUrl: './map.component.html',
  styleUrl: './map.component.css'
})
export class MapComponent implements OnInit {
  map!: Map;

  constructor(@Inject(PLATFORM_ID) private platformId: object) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      this.initMap();
    }
  }

  initMap() {
    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],
      view: new View({
        center: fromLonLat([14.3225, 48.33585]),
        zoom: 15
      })
    });

    const markerElement = document.getElementById('marker');
    if (markerElement) {
      const marker = new Overlay({
        position: fromLonLat([14.3225, 48.33585]),
        positioning: 'center-center',
        element: markerElement,
        stopEvent: false
      });

      markerElement.innerHTML = '<img src="../../../../../../../../OneDrive/Bernhard/JKU/6.%20Semester/TemporaryFrontendSave/src/assets/placeholder.png" alt="Location Icon" style="width: 30px; height: auto; border: none; padding: 0; margin: 0; background-color: transparent;" />'

      this.map.addOverlay(marker);
    } else {
      console.error('Marker element not found');
    }
  }

  setMarker(coordinates: [number, number]) {
    const markerElement = document.getElementById('marker');
    if (markerElement) {
      const marker = new Overlay({
        position: fromLonLat(coordinates),
        positioning: 'center-center',
        element: markerElement,
        stopEvent: false
      });
      this.map.addOverlay(marker);
    } else {
      console.error('Marker element not found');
    }
  }
}
