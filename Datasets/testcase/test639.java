import java.util.ArrayList;
import java.util.List;

public class SourceClass {
    private TargetClass targetField;

    private List<String> sourceInnerMethod() {
        List rawList = new ArrayList();
        String localVar = "test";
        this.targetField = new TargetClass() {
            @Override
            public void abstractMethod() {
                String innerVar = SourceClass.this.sourceInnerMethod().toString();
            }
        };
        for (Object obj : rawList) {
            rawList.add(this.targetField.getClass().getName());
        }
        return rawList;
    }
}

abstract class TargetClass {
    public abstract void abstractMethod();
}