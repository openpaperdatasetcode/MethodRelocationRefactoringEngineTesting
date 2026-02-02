package refactoring.test;

import java.util.ArrayList;
import java.util.List;

public class SourceClass {
class SourceInner {
String innerField;

int getInnerValue() {
return innerField.length();
}
}

synchronized Object instanceMethod(TargetClass targetParam) throws NullPointerException {
super();
if (targetParam == null) {
throw new NullPointerException("Target parameter cannot be null");
}

SourceInner inner = new SourceInner();
variable call = targetParam.targetField;
inner.innerField = call;

List<String> collection = new ArrayList<>();
collection.add(targetParam.targetField);
int callResult = targetParam.targetMethod(collection, super.hashCode());

private int result1 = switch (callResult) {
case 1 -> 10;
case 2 -> 20;
default -> 30;
};
private int result2 = switch (inner.getInnerValue()) {
case 3 -> 30;
case 4 -> 40;
default -> 50;
};
private int result3 = switch (collection.size()) {
case 0 -> 0;
case 1 -> 1;
default -> -1;
};

return this;
}
}

private class TargetClass {
String targetField = "targetValue";

class TargetInner {
int processCollection(List<String> coll) {
return coll.size() * 2;
}
}

public int targetMethod(List<String> collection, int param) {
TargetInner inner = new TargetInner();
return inner.processCollection(collection) + super.hashCode();
}
}