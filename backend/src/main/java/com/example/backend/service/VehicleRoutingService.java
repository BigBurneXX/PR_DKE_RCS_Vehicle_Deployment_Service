package com.example.backend.service;

import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.model.VehicleDeploymentPlanning;
import lombok.RequiredArgsConstructor;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleRoutingService {
    private final SolverFactory<VehicleDeploymentPlanning> solverFactory;

    public VehicleDeploymentPlanning solve(VehicleDeploymentPlanningInputDTO inputDTO) {
        Solver<VehicleDeploymentPlanning> solver = solverFactory.buildSolver();
        VehicleDeploymentPlanning solution = new VehicleDeploymentPlanning(inputDTO);
        return solver.solve(solution);
    }
}

