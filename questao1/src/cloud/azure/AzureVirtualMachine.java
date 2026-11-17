package cloud.azure;
import cloud.VirtualMachine;
public class AzureVirtualMachine implements VirtualMachine {
    private final String id;
    public AzureVirtualMachine(int n) { id = "vm-" + n; }
    public String getId() { return id; }
    public String getProvider() { return "Azure"; }
    public double getHourlyCost() { return 0.12; }
    public double getMonthlyCost() { return getHourlyCost() * 730.0; }
}
