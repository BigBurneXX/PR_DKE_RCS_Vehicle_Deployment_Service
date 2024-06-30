import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
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
  @Input() plans: any;

  private map: any;

  constructor(private orsService: OrsService,
              private route: ActivatedRoute,
              private cdr: ChangeDetectorRef) {
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

      this.cdr.detectChanges();
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

      const customIcon = L.icon({
        iconUrl: 'assets/images/markers/gps.png',
        iconSize: [32, 32], // Adjust the size as needed
        iconAnchor: [16, 32], // Adjust the anchor point as needed
        popupAnchor: [0, -32] // Adjust the popup anchor point as needed
      });

      for (let i = 0; i < coordinates.length; i++) {
        const [longitude, latitude] = coordinates[i];
        let label = '';
        if (i === 0) {
          label = 'Vehicle starting position';
        } else if (i === coordinates.length - 1) {
          label = 'Vehicle end position';
        } else {
          label = `Tabstop ${i}`;
        }

        const address = await this.getAddress(latitude, longitude);
        const popupContent = `${label}<br>${address}`;

        L.marker([latitude, longitude], {icon: customIcon}).addTo(this.map).bindPopup(popupContent);
      }

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

  private async getAddress(lat: number, lon: number): Promise<string> {
    const url = `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${lat}&lon=${lon}`;
    try {
      const response = await fetch(url);
      const data = await response.json();
      return data.display_name || 'No address found';
    } catch (error) {
      console.error('Failed to fetch address:', error);
      return 'Address lookup failed';
    }
  }
}