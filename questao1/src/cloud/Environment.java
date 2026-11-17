package cloud;

public final class Environment {
    private final String name;
    private final String provider;
    private final String region;
    private final VirtualMachine[] machines;
    private final ObjectStorage storage;
    private final MessageQueue queue;
    private final double monthlyCost;

    public Environment(String name, String provider, String region,
                       VirtualMachine[] machines, ObjectStorage storage,
                       MessageQueue queue, double monthlyCost) {
        this.name = name;
        this.provider = provider;
        this.region = region;
        this.machines = machines.clone();
        this.storage = storage;
        this.queue = queue;
        this.monthlyCost = monthlyCost;
        // Proteção adicional: impede misturar recursos de provedores diferentes.
        for (VirtualMachine machine : this.machines) {
            if (!provider.equals(machine.getProvider())) {
                throw new IllegalArgumentException("Máquina de provedor diferente.");
            }
        }
        if (!provider.equals(storage.getProvider()) || !provider.equals(queue.getProvider())) {
            throw new IllegalArgumentException("Recursos de provedores diferentes.");
        }
    }

    public String getName() { return name; }
    public String getProvider() { return provider; }
    public String getRegion() { return region; }
    public VirtualMachine[] getMachines() { return machines.clone(); }
    public ObjectStorage getStorage() { return storage; }
    public MessageQueue getQueue() { return queue; }
    public double getMonthlyCost() { return monthlyCost; }

    public void printSummary() {
        System.out.println("\n=== Ambiente provisionado ===");
        System.out.println("Nome: " + name);
        System.out.println("Provedor: " + provider);
        System.out.println("Região: " + region);
        for (VirtualMachine machine : machines) System.out.println("VM: " + machine.getId());
        System.out.println("Armazenamento: " + storage.getId());
        System.out.println("Fila: " + queue.getId());
        System.out.printf(java.util.Locale.US, "Custo mensal estimado: R$ %.2f%n", monthlyCost);
    }
}
