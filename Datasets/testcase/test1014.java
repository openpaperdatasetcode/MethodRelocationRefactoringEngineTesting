import java.util.ArrayList;
import java.util.List;

final class SourceClass<T> {
    protected String outerProtectedField = "protectedValue";

    static class NestedStaticClass1 {}
    static class NestedStaticClass2 {}

    public int moveCandidateMethod(TargetClass<String> targetParam) {
        super();
        int localVar = 10;
        localVar += targetParam.instanceMethod().size();
        this.outerProtectedField = "modified";
        String typeDeclaredVar = targetParam.toString();
        return localVar;
    }

    public String callMethod() {
        try {
            TargetClass<String> targetObj = new TargetClass<>() {
                @Override
                public List<String> instanceMethod() {
                    return new ArrayList<>();
                }
            };
            return targetObj.instanceMethod().add("test").toString();
        } catch (NullPointerException e) {
            throw new RuntimeException("Call failed", e);
        }
    }
}

public class TargetClass<E> {
    public List<String> instanceMethod() {
        return new ArrayList<>();
    }
}