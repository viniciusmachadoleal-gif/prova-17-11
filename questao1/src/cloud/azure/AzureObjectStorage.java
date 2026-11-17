package cloud.azure;
import cloud.ObjectStorage;
public class AzureObjectStorage implements ObjectStorage {
    private final String id;
    public AzureObjectStorage(int n) { id = "blob://" + n; }
    public String getId() { return id; }
    public String getProvider() { return "Azure"; }
    public double getCostPerGb() { return 0.02; }
    public double getMonthlyCost() { return getCostPerGb(); }
}
