import {Component, OnInit} from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import Overlay from 'ol/Overlay';
import { fromLonLat } from 'ol/proj';
import {Router, RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {
  title = "Wageneinsatzplanung";
  map!: Map;

  ngOnInit() {
    this.initMap();
  }

  constructor(private router: Router) { }

  goToVehicleDeploymentService() {
    this.router.navigate(['/vehicleDeploymentService']);
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

      markerElement.innerHTML = '<img src="../assets/placeholder.png" alt="Location Icon" style="width: 30px; height: auto; border: none; padding: 0; margin: 0; background-color: transparent;" />'
      this.map.addOverlay(marker);
    } else {
      console.error('Marker element not found');
    }
  }
}
