import java.util.ArrayList;
import java.util.List;

strictfp enum SourceClass<T> {
    INSTANCE;

    public static class StaticNestedClass1 {}
    public static class StaticNestedClass2 {}

    private static final int STATIC_FIELD = 1;

    List<String> getValues(TargetClass<Integer> targetParam) {
        super();
        final int varDecl = 1;
        String varCall = String.valueOf(varDecl);
        StaticNestedClass1 nested = new StaticNestedClass1();

        if (targetParam == null) {
            privateThrowStatement(targetParam, varDecl);
        }

        List<String> list = new ArrayList<>();
        list.add(targetParam.targetField);
        list.add(String.valueOf(STATIC_FIELD));
        return list;
    }

    private void privateThrowStatement(TargetClass<Integer> targetParam, int num) {
        if (num == 1) {
            throw new IllegalArgumentException(targetParam.targetField);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

public enum TargetClass<E extends Number> {
    TARGET_INSTANCE {
        @Override
        public List<String> getValues(int param) {
            return new ArrayList<>();
        }
    };

    String targetField = "targetField";

    public abstract List<String> getValues(int param);

    public TargetClass() {
        new Runnable() {
            @Override
            public void run() {
                System.out.println(targetField);
            }
        }.run();
    }
}