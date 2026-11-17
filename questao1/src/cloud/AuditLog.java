package cloud;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Singleton + seção crítica sincronizada: sequência e inclusão são atômicas.
public final class AuditLog {
    private final List<AuditRecord> records = new ArrayList<>();
    private long nextSequence = 1;
    private AuditLog() {}
    private static class Holder {
        private static final AuditLog INSTANCE = new AuditLog();
    }
    public static AuditLog getInstance() { return Holder.INSTANCE; }

    public synchronized void register(String environment, String provider, String result) {
        records.add(new AuditRecord(nextSequence++, LocalDateTime.now(),
                environment, provider, result));
    }
    public synchronized void printAll() {
        System.out.println("\n=== HISTÓRICO DE AUDITORIA ===");
        for (AuditRecord record : records) System.out.println(record);
        System.out.println("Total de registros: " + records.size());
    }
}
