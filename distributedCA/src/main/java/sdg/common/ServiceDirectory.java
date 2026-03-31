package sdg.common;

public final class ServiceDirectory {
    public static final String HOST = "localhost";

    public static final String NAMING_SERVICE = "NamingService";
    public static final String EMISSION_SERVICE = "EmissionAccountingService";
    public static final String CLIMATE_RISK_SERVICE = "ClimateRiskAlertService";
    public static final String MITIGATION_SERVICE = "MitigationOrchestratorService";

    public static final int NAMING_PORT = 50051;
    public static final int EMISSION_PORT = 50052;
    public static final int CLIMATE_RISK_PORT = 50053;
    public static final int MITIGATION_PORT = 50054;

    private ServiceDirectory() {
    }
}
