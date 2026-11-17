package cloud;

public final class EnvironmentRequest {
    private final String name;
    private final String provider;
    private final int vmCount;
    private final int storageGb;

    public EnvironmentRequest(String name, String provider, int vmCount, int storageGb) {
        if (name == null || name.isBlank() || provider == null || provider.isBlank() || vmCount < 1 || storageGb < 0) {
            throw new IllegalArgumentException("Dados da solicitação inválidos.");
        }
        this.name = name;
        this.provider = provider;
        this.vmCount = vmCount;
        this.storageGb = storageGb;
    }
    public String getName() { return name; }
    public String getProvider() { return provider; }
    public int getVmCount() { return vmCount; }
    public int getStorageGb() { return storageGb; }
}
