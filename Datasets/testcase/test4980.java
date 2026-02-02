package refactoring.source;

import refactoring.target.TargetClass;

private class SourceClass<T extends Number> extends ParentSourceClass<T> {
static class SourceStaticNested {
U nestedData;
}

protected TargetClass<T> instanceMethod(TargetClass<T> targetParam) {
super();

private String stringLiteral = "source_literal_1";
variable call = targetParam.targetField;

class LocalInner {
TargetClass<T> getTargetWithNested() {
TargetClass.SourceStaticNested<T> targetNested = new TargetClass.SourceStaticNested<>();
targetNested.nestedField = targetParam.targetField;
return targetParam;
}
}

LocalInner local = new LocalInner();
return local.getTargetWithNested();
}
}

class ParentSourceClass<T extends Number> {
protected ParentSourceClass() {}
}

// Different package: refactoring.target
package refactoring.target;

public class TargetClass<T extends Number> {
T targetField;

static class SourceStaticNested {
U nestedField;
}
}