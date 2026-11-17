package cloud;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class AuditRecord {
    private final long sequence;
    private final LocalDateTime dateTime;
    private final String environment;
    private final String provider;
    private final String result;

    public AuditRecord(long sequence, LocalDateTime dateTime, String environment,
                       String provider, String result) {
        this.sequence = sequence;
        this.dateTime = dateTime;
        this.environment = environment;
        this.provider = provider;
        this.result = result;
    }
    public long getSequence() { return sequence; }
    @Override public String toString() {
        return sequence + " | " + dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS"))
                + " | Ambiente: " + environment + " | Provedor: " + provider + " | " + result;
    }
}
