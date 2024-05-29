import {AfterViewInit, Component, OnInit} from '@angular/core';
import 'leaflet/dist/leaflet.css';
import * as L from 'leaflet';
import { OrsService } from '../../services/ors.service';

@Component({
  selector: 'app-route-map',
  standalone: true,
  templateUrl: './route-map.component.html',
  styleUrls: ['./route-map.component.css']
})
export class RouteMapComponent implements AfterViewInit {
  private map: any;

  constructor(private orsService: OrsService) { }

  ngAfterViewInit(): void {
    this.initMap();
    this.loadRoute();
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: [51.1657, 10.4515], // Center the map to Germany
      zoom: 6
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(this.map);
  }

  private loadRoute(): void {
    const coordinates = [
      [8.681495, 49.41461],  // Start coordinate
      [8.687872, 49.420318]  // End coordinate
    ];

    this.orsService.getRoute(coordinates).then(response => {
      const geojson = response.data;

      L.geoJSON(geojson, {
        style: {
          color: 'blue',
          weight: 4,
          opacity: 0.7
        }
      }).addTo(this.map);

      // Zoom the map to fit the route
      this.map.fitBounds(L.geoJSON(geojson).getBounds());
    }).catch(error => {
      console.error('Error fetching route', error);
    });
  }
}
