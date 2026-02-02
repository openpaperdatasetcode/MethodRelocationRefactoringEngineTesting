package refactoring.test;

import java.util.List;
import java.util.ArrayList;

@interface SourceAnnotation<T> {
@Deprecated
protected List<String> abstractMethod() default {};

class SourceHelper {
private TargetAnnotation target = new TargetAnnotation() {};

public void helperMethod() {
type declaration statement;
TargetAnnotation.StaticNested nested = new TargetAnnotation.StaticNested();
expression statement = nested.getField();

class LocalInner {
List<String> getValues() {
variable call = target.staticField;
return super.getClass().getName() != null ? new ArrayList<>() : null;
}
}

LocalInner local = new LocalInner();
List<String> result = OtherFinalClass.process(local.getValues(), nested);
}
}
}

private @interface TargetAnnotation {
String staticField = "targetField";

static class StaticNested {
String field;

String getField() {
return field;
}
}
}

final class OtherFinalClass {
public static List<String> process(List<String> list, TargetAnnotation.StaticNested nested) {
return new ArrayList<>(list);
}

public static List<String> process(List<String> list, String param) {
return list;
}
}