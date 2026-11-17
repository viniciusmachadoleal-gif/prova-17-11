package cloud.azure;
import cloud.*;
public class AzureFactory implements CloudFactory {
    public VirtualMachine createVirtualMachine(int n) { return new AzureVirtualMachine(n); }
    public ObjectStorage createObjectStorage(int n) { return new AzureObjectStorage(n); }
    public MessageQueue createMessageQueue(int n) { return new AzureMessageQueue(n); }
    public double getVmHourlyCost() { return 0.12; }
    public double getStorageCostPerGb() { return 0.02; }
    public double getQueueMonthlyCost() { return 0.50; }
    public String getProviderName() { return "Azure"; }
}
