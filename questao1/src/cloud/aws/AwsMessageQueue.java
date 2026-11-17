package cloud.aws;
import cloud.MessageQueue;
public class AwsMessageQueue implements MessageQueue {
    private final String id;
    public AwsMessageQueue(int n) { id = "sqs-" + n; }
    public String getId() { return id; }
    public String getProvider() { return "AWS"; }
    public double getMonthlyCost() { return 0.40; }
}
