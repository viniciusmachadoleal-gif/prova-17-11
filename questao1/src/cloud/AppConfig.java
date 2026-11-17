package cloud;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.Properties;

// Singleton: a configuração é carregada uma única vez (Initialization-on-demand holder).
public final class AppConfig {
    private final String region;
    private final double monthlyBudget;

    private AppConfig() {
        Properties properties = new Properties();
        try (InputStream input = openConfig()) {
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao ler config.properties.", e);
        }
        region = properties.getProperty("regiao", "sa-east-1");
        monthlyBudget = Double.parseDouble(properties.getProperty("orcamento.mensal.maximo"));
    }

    // Procura no classpath; se não achar, no diretório de execução (ou em questao1/src).
    private static InputStream openConfig() throws IOException {
        InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("config.properties");
        if (input != null) return input;
        for (String candidate : new String[] {"config.properties", "src/config.properties",
                "questao1/src/config.properties"}) {
            Path path = Path.of(candidate);
            if (Files.exists(path)) return Files.newInputStream(path);
        }
        throw new IllegalStateException("config.properties não encontrado.");
    }

    private static class Holder {
        private static final AppConfig INSTANCE = new AppConfig();
    }
    public static AppConfig getInstance() { return Holder.INSTANCE; }
    public String getRegion() { return region; }
    public double getMonthlyBudget() { return monthlyBudget; }
}
