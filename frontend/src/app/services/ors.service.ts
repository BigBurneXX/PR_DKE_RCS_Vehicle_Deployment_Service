import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class OrsService {
  private apiKey = '5b3ce3597851110001cf62484632c7a923954e39bbdf8f1224470ce7';
  private apiUrl = 'https://api.openrouteservice.org/v2/directions/driving-car';

  constructor() { }

  getRoute(coordinates: number[][]) {
    const url = `${this.apiUrl}`;
    const data = {
      coordinates: coordinates,
      format: 'geojson'
    };

    return axios.post(url, data, {
      headers: {
        'Authorization': this.apiKey,
        'Content-Type': 'application/json'
      }
    });
  }
}
