package cloud.aws;
import cloud.VirtualMachine;
public class AwsVirtualMachine implements VirtualMachine {
    private final String id;
    public AwsVirtualMachine(int n) { id = "i-" + n; }
    public String getId() { return id; }
    public String getProvider() { return "AWS"; }
    public double getHourlyCost() { return 0.10; }
    public double getMonthlyCost() { return getHourlyCost() * 730.0; }
}
