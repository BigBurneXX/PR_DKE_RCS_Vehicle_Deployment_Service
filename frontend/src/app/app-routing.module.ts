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
        path: 'vehicle-deployment-plannings',
        loadChildren: () => import('./vehicle-deployment-plannings/vehicle-deployment-plannings-all/vehicle-deployment-plannings-all.module')
            .then(m => m.VehicleDeploymentPlanningsAllModule)
      },
      {
        path: 'vehicle-deployment-plannings/new',
        loadChildren: () => import('./vehicle-deployment-plannings/vehicle-deployment-planning-new/vehicle-deployment-plannings-new.module')
            .then(m => m.VehicleDeploymentPlanningsNewModule)
      },
      {
        path: 'vehicle-deployment-plannings/details/:id',
        loadChildren: () => import('./vehicle-deployment-plannings/vehicle-deployment-plannings-plans/vehicle-deployment-plannings-plans.module')
            .then(m => m.VehicleDeploymentPlanningsPlansModule)
      },
      {
        path: 'vehicle-deployment-plans',
        loadChildren: () => import('./vehicle-deployment-plans/vehicle-deployment-plan/vehicle-deployment-plan.module')
            .then(m => m.VehicleDeploymentPlanModule)
      },
      {
        path: 'vehicle-deployment-plans/details/:id',
        loadChildren: () => import('./vehicle-deployment-plans/vehicle-deployment-plan-details/vehicle-deployment-plan-details.module')
            .then(m => m.VehicleDeploymentPlanDetailsModule)
      },
      {
        path: 'trip-sheets/new',
        loadChildren: () => import('./trip-sheets/trip-sheet-new/new-trip-sheet.module').then(m => m.NewTripSheetModule)
      },
      {
        path: 'trip-sheets',
        loadChildren: () => import('./trip-sheets/trip-sheet/trip-sheet.module').then(m => m.TripSheetModule)
      },
    ]
  },
  {
    path: '**',
    redirectTo: '/starter'
  }
];
