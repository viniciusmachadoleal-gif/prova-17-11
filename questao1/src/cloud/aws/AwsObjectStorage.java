package cloud.aws;
import cloud.ObjectStorage;
public class AwsObjectStorage implements ObjectStorage {
    private final String id;
    public AwsObjectStorage(int n) { id = "s3://" + n; }
    public String getId() { return id; }
    public String getProvider() { return "AWS"; }
    public double getCostPerGb() { return 0.023; }
    public double getMonthlyCost() { return getCostPerGb(); }
}
