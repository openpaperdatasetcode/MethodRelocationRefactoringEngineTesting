package test;

import java.util.List;
import java.util.ArrayList;

public enum SourceEnum {
VALUE1, VALUE2;

// Static nested class
static class NestedStatic {
private String data;

public NestedStatic(String data) {
this.data = data;
}

public String getData() {
return data;
}
}

// Abstract method to be refactored
protected abstract List<String> abstractMethod(TargetEnum target);

// Implementation with local inner class
static {
VALUE1 = new SourceEnum() {
@Override
protected List<String> abstractMethod(TargetEnum target) {
// Local inner class
class LocalProcessor {
List<String> process(TargetEnum t) {
// Variable call
List<String> result = new ArrayList<>(t.getValues());
return result;
}
}
return new LocalProcessor().process(target);
}
};

VALUE2 = new SourceEnum() {
@Override
protected List<String> abstractMethod(TargetEnum target) {
try {
// Call method in exception throwing statements
int code = OthersClass.privateMethod(() -> {
if (target == null) {
return 0;
}
return target.ordinal();
});
List<String> result = new ArrayList<>();
result.add(String.valueOf(code));
return result;
} catch (IllegalStateException e) {
throw e;
}
}
};
}
}

public enum TargetEnum {
TARGET_A, TARGET_B;

public List<String> getValues() {
// Local inner class
class ValueGenerator {
List<String> generate() {
return List.of(name());
}
}
return new ValueGenerator().generate();
}
}

class OthersClass {
// Private method with lambda parameter
private static int privateMethod(IntSupplier supplier) {
try {
return supplier.getAsInt();
} catch (Exception e) {
throw new IllegalStateException("Error", e);
}
}

@FunctionalInterface
interface IntSupplier {
int getAsInt();
}
}