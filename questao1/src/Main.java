import cloud.*;
import cloud.aws.AwsFactory;
import cloud.azure.AzureFactory;
import java.util.concurrent.*;

public class Main {
    // Único ponto de seleção do provedor: adicionar GCP exige nova factory/produtos
    // e incluir sua escolha aqui, sem alterar o provisionador.
    private static CloudFactory chooseProvider(String provider) {
        return switch (provider.toLowerCase()) {
            case "aws" -> new AwsFactory();
            case "azure" -> new AzureFactory();
            default -> throw new IllegalArgumentException("Provedor não suportado: " + provider);
        };
    }

    private static void execute(String name, String provider, int vms, int gb) {
        String providerName = provider; // nome usado na auditoria (padronizado após escolher a factory)
        try {
            EnvironmentRequest request = new EnvironmentRequest(name, provider, vms, gb);
            CloudFactory factory = chooseProvider(request.getProvider());
            providerName = factory.getProviderName();
            Environment environment = new EnvironmentProvisioner(factory).provision(request);
            environment.printSummary();
            AuditLog.getInstance().register(name, providerName, "ACEITO");
        } catch (BudgetExceededException e) {
            AuditLog.getInstance().register(name, providerName, "RECUSADO - " + e.getMessage());
            System.out.println("Solicitação " + name + " recusada: " + e.getMessage());
        } catch (RuntimeException e) {
            AuditLog.getInstance().register(name, providerName, "RECUSADO - " + e.getMessage());
            System.out.println("Falha em " + name + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch done = new CountDownLatch(4);

        Runnable[] jobs = {
            () -> execute("Ambiente-AWS-1", "aws", 2, 100),
            () -> execute("Ambiente-AWS-2", "aws", 3, 200),
            () -> execute("Ambiente-Azure", "azure", 2, 100),
            () -> execute("Ambiente-Excedente", "azure", 100, 10000)
        };
        for (Runnable job : jobs) {
            executor.submit(() -> { try { job.run(); } finally { done.countDown(); } });
        }
        done.await();
        executor.shutdown();
        AuditLog.getInstance().printAll();
    }
}
