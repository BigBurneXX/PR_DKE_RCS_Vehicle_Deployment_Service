import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Routes, RouterModule} from "@angular/router";
import {VehicleDeploymentPlanningsAllComponent} from "./vehicle-deployment-plannings-all.component";

const routes: Routes = [
    {
        path: "",
        data: {
            title: "Vehicle Deployment Planning",
            urls: [{ title: "Vehicle Deployment Planning", url: "/vehicle-deployment-plannings/all" }, { title: "Vehicle Deployment Planning" }],
        },
        component: VehicleDeploymentPlanningsAllComponent,
    },
];

@NgModule({
    imports: [
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        RouterModule.forChild(routes),
    ],
    declarations: [
    ],
})
export class VehicleDeploymentPlanningsAllModule {}