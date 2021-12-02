package de.dueto.backend.controller.v1;

import de.dueto.backend.service.DashboardService;
import org.springframework.stereotype.Controller;

@Controller("/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

}
