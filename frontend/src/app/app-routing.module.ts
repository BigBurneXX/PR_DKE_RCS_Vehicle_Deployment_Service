import { Routes } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';

export const AppRoutes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'about',
        loadChildren: () => import('./about/about.module').then(m => m.AboutModule)
      },
      {
        path: 'component',
        loadChildren: () => import('./component/component.module').then(m => m.ComponentsModule)
      },
      {
        path: 'route-map',
        loadChildren: () => import('./component/route-map/route-map.module').then(m => m.RouteMapModule)
      },
      {
        path: 'trip-sheet',
        loadChildren: () => import('./trip-sheet/trip-sheet.module').then(m => m.TripSheetModule)
      },
      {
        path: 'vehicle-deployment-plan',
        loadChildren: () => import('./vehicle-deployment-plan/vehicle-deployment-plan.module').then(m => m.VehicleDeploymentPlanModule)
      },
      {
        path: 'vehicle-deployment-planning',
        loadChildren: () => import('./vehicle-deployment-planning/vehicle-deployment-planning.module').then(m => m.VehicleDeploymentPlanningModule)
      },
      {
        path: 'new-vehicle-deployment-planning',
        loadChildren: () => import('./vehicle-deployment-planning-new/new-vehicle-deployment-planning.module').then(m => m.NewVehicleDeploymentPlanningModule)
      },
      {
        path: 'new-trip-sheet',
        loadChildren: () => import('./trip-sheet-new/new-trip-sheet.module').then(m => m.NewTripSheetModule)
      }
    ]
  },
  {
    path: '**',
    redirectTo: '/starter'
  }
];
