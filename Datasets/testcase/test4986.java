package refactoring.test;

import java.util.List;
import java.util.ArrayList;

abstract class SourceClass {
protected String outerProtectedField = "protectedValue";
private TargetClass target = new TargetClass();

static class SourceStaticNested {
String staticNestedField = "staticNestedValue";
}

class SourceMemberInner {
/**

Retrieves combined list of values from target class and outer class
@return List<String> containing field values
*/
@Deprecated
List<String> getCombinedValues() {
List<String> result = new ArrayList<>();
variable call = target.targetInstanceField;
result.add(call);
result.add(target.TargetStaticNested.staticNestedField);
result.add(SourceClass.this.outerProtectedField);
result.add(new SourceStaticNested().staticNestedField);
return result;
}
}
}

class TargetClass {
String targetInstanceField = "targetInstanceValue";

static class TargetStaticNested {
static String staticNestedField = "targetStaticNestedValue";
}
}