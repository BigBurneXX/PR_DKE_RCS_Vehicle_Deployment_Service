import {Injectable} from '@angular/core';
import axios from 'axios';
import * as polyline from '@mapbox/polyline';

@Injectable({
  providedIn: 'root'
})
export class OrsService {
  private apiKey = '5b3ce3597851110001cf62484632c7a923954e39bbdf8f1224470ce7';
  private apiUrl = 'https://api.openrouteservice.org/v2/directions/driving-car';

  constructor() { }

  async getRoute(coordinates: number[][]) {
    const url = `${this.apiUrl}`;
    const data = {
      coordinates: coordinates,
      format: 'geojson'
    };

    const response = await axios.post(url, data, {
      headers: {
        'Authorization': this.apiKey,
        'Content-Type': 'application/json'
      }
    });

    const route = response.data.routes[0];
    const decodedCoordinates = polyline.decode(route.geometry);

    return {
      type: 'FeatureCollection',
      features: [
        {
          type: 'Feature',
          properties: {},
          geometry: {
            type: 'LineString',
            coordinates: decodedCoordinates.map((coord: number[]) => [coord[1], coord[0]]) // Swap lat and lng
          }
        }
      ]
    };
  }
}
