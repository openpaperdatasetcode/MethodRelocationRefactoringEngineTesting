package refactoring.test;

// Sealed interface for source record's permits feature
sealed interface SourceRecordPermit permits SourceRecord {}

// Source record class: public modifier, same package, permits + anonymous inner + member inner classes
public non-sealed record SourceRecord(String data) implements SourceRecordPermit { // permits feature
    // Source contains target field (per_condition)
    private TargetRecord targetField = new TargetRecord("target_init");

    // Member inner class (source feature)
    public class SourceInnerClass {
        String innerData = data;

        public void updateTarget() {
            targetField.setData("inner_updated");
        }
    }

    // Anonymous inner class (source feature)
    private final Runnable sourceAnonymous = new Runnable() {
        @Override
        public void run() {
            targetField.setData("anonymous_updated");
        }
    };

    /**
     * Normal method to be refactored (all specified features)
     */
    @SuppressWarnings("unused") // has_annotation
    private void refactorMethod() {
        // Variable call (target record field)
        targetField.setData("refactor_" + data);
        int counter = 0;

        // Do statement
        do {
            // Depends on inner class (source inner class)
            new SourceInnerClass().updateTarget();
            // Variable call (target inner anonymous class field)
            targetField.anonymousRunnable.run();
            counter++;
        } while (counter < 1);

        // Depends on inner class (target anonymous inner class)
        targetField.anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                targetField.setData("depends_on_inner");
            }
        };

        // Trigger anonymous inner class logic
        sourceAnonymous.run();

        // No new exception, void return
    }
}

// Target record class: public modifier, anonymous inner class feature
public record TargetRecord(String data) {
    // Anonymous inner class (target_feature)
    private Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            // No new exception
            System.out.println("Target anonymous: " + data);
        }
    };

    // Setter for variable call (record immutability workaround)
    public void setData(String newData) {
        // Record's private copy constructor workaround (compatible with Java 16+)
        this.anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Updated target: " + newData);
            }
        };
    }

    // Getter for anonymous inner class access
    public Runnable getAnonymousRunnable() {
        return anonymousRunnable;
    }

    // Alias for direct variable call
    public Runnable anonymousRunnable() {
        return anonymousRunnable;
    }
}