package cloud;

// Abstract Factory: cria uma família de recursos de um único provedor.
public interface CloudFactory {
    VirtualMachine createVirtualMachine(int sequence);
    ObjectStorage createObjectStorage(int sequence);
    MessageQueue createMessageQueue(int sequence);
    String getProviderName();
    double getVmHourlyCost();
    double getStorageCostPerGb();
    double getQueueMonthlyCost();
}
