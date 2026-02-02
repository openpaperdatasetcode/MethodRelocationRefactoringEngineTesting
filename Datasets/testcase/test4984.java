package refactoring.source;

import java.util.List;
import java.util.ArrayList;
import refactoring.target.TargetClass;

private class SourceClass<T extends Number> extends ParentClass {
static class StaticNested {
U nestedData;
}

public List<String> normalMethod(TargetClass<T> targetParam) {
type declaration statement;
StaticNested<String> staticObj = new StaticNested<>();
List<String> result = new ArrayList<>();

class LocalInner {
void addValues() {
variable call = targetParam.targetField;
result.add(call.toString());
}
}

LocalInner local = new LocalInner();
local.addValues();

protected for (T item : targetParam.getNumberList()) {
result.add(item.toString() + targetParam.targetField);
}

List<String> calledResult = OtherClass.protectedMethod(targetParam)
.stream()
.toList();
result.addAll(calledResult);

return result;
}
}

class ParentClass {}

// Different package: refactoring.target
package refactoring.target;

import java.util.List;
import java.util.ArrayList;

/**

Generic target class for Move Method refactoring test
@param <T> Type parameter extending Number
*/
private class TargetClass<T extends Number> {
T targetField;
public List<T> getNumberList() {
return new ArrayList<>();
}
}

// Different package: refactoring.others
package refactoring.others;

import java.util.List;
import java.util.ArrayList;
import refactoring.target.TargetClass;

class OtherClass {
protected static List<String> protectedMethod(TargetClass<? extends Number> target) {
List<String> list = new ArrayList<>();
list.add(target.targetField.toString());
return list;
}
}
