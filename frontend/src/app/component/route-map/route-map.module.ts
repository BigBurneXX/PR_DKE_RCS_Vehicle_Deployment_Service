import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { RouteMapComponent } from "./route-map.component";

const routes: Routes = [
    {
        path: "",
        data: {
            title: "Showing predefined route",
            urls: [{ title: "Route", url: "/route-map" }, { title: "Route" }],
        },
        component: RouteMapComponent,
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
export class RouteMapModule {}