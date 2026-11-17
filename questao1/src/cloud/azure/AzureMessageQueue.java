package cloud.azure;
import cloud.MessageQueue;
public class AzureMessageQueue implements MessageQueue {
    private final String id;
    public AzureMessageQueue(int n) { id = "sb-" + n; }
    public String getId() { return id; }
    public String getProvider() { return "Azure"; }
    public double getMonthlyCost() { return 0.50; }
}
