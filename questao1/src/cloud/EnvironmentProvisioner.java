package cloud;

// Builder/Director simplificado: monta o ambiente sem conhecer classes concretas.
public final class EnvironmentProvisioner {
    private final CloudFactory factory;

    public EnvironmentProvisioner(CloudFactory factory) {
        this.factory = factory;
    }

    public Environment provision(EnvironmentRequest request) {
        AppConfig config = AppConfig.getInstance();
        // Calcula primeiro usando os preços informados pela fábrica. Se exceder o
        // orçamento, nenhum recurso concreto é criado.
        double cost = request.getVmCount() * factory.getVmHourlyCost() * 730.0
                + request.getStorageGb() * factory.getStorageCostPerGb()
                + factory.getQueueMonthlyCost();
        if (cost > config.getMonthlyBudget()) {
            throw new BudgetExceededException(String.format(
                    java.util.Locale.US, "Custo R$ %.2f excede o orçamento de R$ %.2f.",
                    cost, config.getMonthlyBudget()));
        }

        VirtualMachine[] machines = new VirtualMachine[request.getVmCount()];
        for (int i = 0; i < machines.length; i++) {
            machines[i] = factory.createVirtualMachine(i + 1);
        }
        ObjectStorage storage = factory.createObjectStorage(1);
        MessageQueue queue = factory.createMessageQueue(1);
        return new Environment(request.getName(), factory.getProviderName(),
                config.getRegion(), machines, storage, queue, cost);
    }
}
