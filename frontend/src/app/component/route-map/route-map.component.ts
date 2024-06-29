import { Component, OnInit } from '@angular/core';
import 'leaflet/dist/leaflet.css';
import * as L from 'leaflet';
import { OrsService } from './ors.service';
import * as chroma from 'chroma-js';
import {ActivatedRoute} from "@angular/router";
import {GeoJSON} from "leaflet";
import {VehicleDeploymentPlanOutputDto} from "../../dtos/VehicleDeploymentPlanOutput.dto";

@Component({
  selector: 'app-route-map',
  standalone: true,
  templateUrl: './route-map.component.html',
  styleUrls: ['./route-map.component.css']
})
export class RouteMapComponent implements OnInit {
  private map: any;

  constructor(private orsService: OrsService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.initMap();
    this.route.queryParams.subscribe(params => {
      console.log('Query Params:', params);

      const coordinates = params['coordinates'] ? JSON.parse(params['coordinates']) : null;
      if (coordinates) {
        console.log('Loading single route with coordinates:', coordinates);
        const bounds = this.loadRoute(coordinates, "blue");
        bounds.then(b => this.map.fitBounds(b));
      }

      const plans = params['plans'] ? JSON.parse(params['plans']) : null;
      if (plans) {
        console.log('Loading multiple routes with plans:', plans);
        this.loadRoutes(plans);
      }
    });
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

  private async loadRoute(coordinates: number[][], color: string): Promise<L.LatLngBounds> {
    try {
      const geojson = await this.orsService.getRoute(coordinates);
      if (geojson.type !== 'FeatureCollection') {
        throw new Error('Invalid GeoJSON type');
      }

      const geoJsonLayer = L.geoJSON(geojson as GeoJSON.FeatureCollection, {
        style: {
          color: color,
          weight: 4,
          opacity: 0.7
        }
      }).addTo(this.map);

      const bounds = geoJsonLayer.getBounds();
      this.map.fitBounds(bounds);
      return bounds;
    } catch (error) {
      console.error('Failed to load route:', error);
      throw error;
    }
  }

  private async loadRoutes(plans: VehicleDeploymentPlanOutputDto[]): Promise<void> {
    const colorScale = chroma.scale(['blue', 'red', 'green']).mode('lab').colors(plans.length);

    try {
      const allBounds: L.LatLngBounds[] = [];

      for (let i = 0; i < plans.length; i++) {
        const coordinates = plans[i].locations.map((location: any) => [location.longitude, location.latitude]);
        console.log(coordinates);
        const bounds = await this.loadRoute(coordinates, colorScale[i]);
        allBounds.push(bounds);
      }

      if (allBounds.length > 0) {
        const overallBounds = allBounds.reduce((acc, bounds) => acc.extend(bounds), L.latLngBounds([]));
        this.map.fitBounds(overallBounds);
      }
    } catch (error) {
      console.error('Failed to load routes:', error);
      alert('Failed to load routes. Please try again later.');
    }
  }

  private async loadRoutes1(): Promise<void> {
    const persons = [
      {
        name: 'Herbert',
        startCoords: [14.294858, 48.287784],
        endCoords: [14.290843, 48.294131],
        startLabel: '43 Elbognerstraße, Linz, 4020, AT',
        endLabel: '2 Lunaplatz, Linz, 4030, AT',
        startIconUrl: 'start-icon.png',
        endIconUrl: 'end-icon.png'
      },
      {
        name: 'Felia',
        startCoords: [14.291496, 48.283372],
        endCoords: [14.280034, 48.280587],
        startLabel: '129 Onionstraße, Linz, 4030, AT',
        endLabel: '125 Neufelderstraße, Linz, 4020, AT',
        startIconUrl: 'start-icon.png',
        endIconUrl: 'end-icon.png'
      },
      {
        name: 'Franz',
        startCoords: [14.286544, 48.288762],
        endCoords: [14.293104, 48.280667],
        startLabel: '10 Hartheimerstraße, Linz, 4020, AT',
        endLabel: '140 Heliosallee, Linz, 4030, AT',
        startIconUrl: 'start-icon.png',
        endIconUrl: 'end-icon.png'
      }
    ];

    const colorScale = chroma.scale(['blue', 'red', 'green']).mode('lab').colors(persons.length);

    try {
      const geoJsonLayers = [];

      for (let i = 0; i < persons.length; i++) {
        const person = persons[i];
        const color = colorScale[i];

        const coordsArray = [person.startCoords, person.endCoords];
        const geojson = await this.orsService.getRoute(coordsArray);

        if (geojson.type !== 'FeatureCollection') {
          throw new Error('Invalid GeoJSON type');
        }

        const geoJsonLayer = L.geoJSON(geojson as GeoJSON.FeatureCollection, {
          style: {
            color: color,
            weight: 4,
            opacity: 0.7
          }
        }).addTo(this.map);

        geoJsonLayers.push(geoJsonLayer);

        const startCustomIcon = L.icon({
          iconUrl: person.startIconUrl,
          iconSize: [32, 32],
          iconAnchor: [16, 32],
          popupAnchor: [0, -32]
        });

        const endCustomIcon = L.icon({
          iconUrl: person.endIconUrl,
          iconSize: [32, 32],
          iconAnchor: [16, 32],
          popupAnchor: [0, -32]
        });

        const startMarker = L.marker([person.startCoords[1], person.startCoords[0]], {icon: startCustomIcon}).addTo(this.map);
        startMarker.bindPopup(`<b>${person.startLabel}</b>`).openPopup();

        const endMarker = L.marker([person.endCoords[1], person.endCoords[0]], {icon: endCustomIcon}).addTo(this.map);
        endMarker.bindPopup(`<b>${person.endLabel}</b>`).openPopup();
      }

      // Fit the map bounds to include all route layers
      const bounds = geoJsonLayers.reduce((acc, layer) => acc.extend(layer.getBounds()), L.latLngBounds([]));
      this.map.fitBounds(bounds);
    } catch (error) {
      console.error();
    }
  }
}
