package refactoring.test;

/**
 * Target enum class Javadoc (target_feature: javadoc)
 * Final modifier, implements interface, anonymous inner class (target_feature)
 */
final enum TargetEnum implements TargetProcessable {
    TARGET_1("value1"), TARGET_2("value2");

    // Target inner class (target_inner - target class of method)
    public class TargetInner {
        private String innerData;

        public TargetInner(String innerData) {
            this.innerData = innerData;
        }

        public String getInnerData() {
            return innerData;
        }
    }

    private final String enumValue;

    // Anonymous inner class (target_feature)
    private final TargetProcessable anonymousProcessor = new TargetProcessable() {
        @Override
        public String process(String input) {
            return input + "_processed";
        }
    };

    TargetEnum(String enumValue) {
        this.enumValue = enumValue;
    }

    public String getEnumValue() {
        return enumValue;
    }

    public TargetInner createInner() {
        return new TargetInner(this.enumValue);
    }

    @Override
    public String process(String input) {
        return anonymousProcessor.process(input);
    }
}

// Interface for TargetEnum implements feature
interface TargetProcessable {
    String process(String input);
}

// Source enum class: public modifier, type parameter, member inner + anonymous inner class (source_feature)
public enum SourceEnum<T> {
    SOURCE_1("source1"), SOURCE_2("source2");

    private final T enumData;

    // Member inner class (source_feature)
    public class SourceMemberInner<T> {
        public T innerValue;

        public SourceMemberInner(T innerValue) {
            this.innerValue = innerValue;
        }
    }

    // Anonymous inner class (source_feature)
    private final Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class executed");
        }
    };

    SourceEnum(T enumData) {
        this.enumData = enumData;
    }

    // Instance method: public access, TargetInner return type (TargetClas Type), target parameter (per_condition)
    public TargetEnum.TargetInner refactorMethod(TargetEnum targetParam) {
        // Empty statement feature
        ;

        // Variable call feature
        String varCall = targetParam.getEnumValue();
        TargetEnum.TargetInner targetInner = targetParam.createInner();

        // Break statement feature
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                break;
            }
            System.out.println("Loop iteration: " + i);
        }

        // Execute anonymous inner class
        anonymousInner.run();

        // No new exception thrown feature
        return new TargetEnum.TargetInner(varCall + "_" + source1);
    }
}