import java.util.List;

/**
 * Target enum class with javadoc and anonymous inner class
 */
protected enum TargetEnum {
    INSTANCE1(1),
    INSTANCE2(2);

    public static final String STATIC_FIELD = "target_static";
    private final int value;

    // Anonymous inner class
    private Runnable action = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target action: " + value);
        }
    };

    TargetEnum(int value) {
        this.value = value;
    }
}

private enum SourceEnum {
    SOURCE1,
    SOURCE2;

    // Static nested class
    static class StaticNested {
        private TargetEnum targetField;

        // Constructor to be moved (source_inner)
        private StaticNested(TargetEnum target) {
            // Type declaration statement with raw type
            List names;
            names = java.util.Arrays.asList("raw_type");

            // Variable call
            String data = target.name();
            
            // Depends on static field
            this.targetField = TargetEnum.valueOf(TargetEnum.STATIC_FIELD + data);
        }

        // Method with local inner class
        public void process() {
            class LocalInner {
                void execute() {
                    System.out.println("Local inner processing");
                }
            }
            new LocalInner().execute();
        }
    }
}
    