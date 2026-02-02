package refactoring.test;

import java.util.List;
import java.util.ArrayList;

private class SourceClass {
private TargetClass target = new TargetClass();

class SourceInner {
@Deprecated
protected int instanceMethod() throws IllegalArgumentException {
int result = 0;
labeledBlock: {
if (target.targetField == null) {
throw new IllegalArgumentException("Target field is null");
}

expression statement = target.targetField;
variable call = target.new TargetInner().overrideMethod();
result = call.size();

if (result > 5) {
break labeledBlock;
}
}

return result + super.hashCode();
}

@Override
public List<String> overrideMethod() {
return new ArrayList<>(List.of(target.targetField));
}
}

static {
SourceClass source = new SourceClass();
SourceInner inner = source.new SourceInner();
try {
inner.instanceMethod();
} catch (IllegalArgumentException e) {}
}
}

abstract class TargetClass {
String targetField = "targetValue";

class TargetInner {
public List<String> overrideMethod() {
return new ArrayList<>(List.of(targetField));
}
}
}
