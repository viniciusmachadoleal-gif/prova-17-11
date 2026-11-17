package cloud.aws;
import cloud.*;
public class AwsFactory implements CloudFactory {
    public VirtualMachine createVirtualMachine(int n) { return new AwsVirtualMachine(n); }
    public ObjectStorage createObjectStorage(int n) { return new AwsObjectStorage(n); }
    public MessageQueue createMessageQueue(int n) { return new AwsMessageQueue(n); }
    public double getVmHourlyCost() { return 0.10; }
    public double getStorageCostPerGb() { return 0.023; }
    public double getQueueMonthlyCost() { return 0.40; }
    public String getProviderName() { return "AWS"; }
}
