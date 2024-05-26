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
        path: 'vehicle-deployment-service',
        loadChildren: () => import('./vehicle-deployment-service/vehicle-deployment-service.module').then(m => m.VehicleDeploymentServiceModule)
      }
    ]
  },
  {
    path: '**',
    redirectTo: '/starter'
  }
];
