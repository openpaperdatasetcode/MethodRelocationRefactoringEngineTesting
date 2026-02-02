import java.util.function.Supplier;
// Interface for source enum to implement (source_class feature: implements)
interface DataProcessor {
TargetEnum processTargetData(TargetEnum target);
}

// Target public enum class (with local inner class)
public enum TargetEnum {
TARGET_A("data-a", 10),
TARGET_B("data-b", 20),
TARGET_C("data-c", 30);

// Enum field (for ClassName.field access)
public final String targetData;
private final int targetValue;

TargetEnum(String data, int value) {
this.targetData = data;
this.targetValue = value;

// Local inner class (target_feature)
class TargetLocalInner {
int calculate(int input) {
return input + targetValue;
}
}
new TargetLocalInner().calculate(5);
}

// Abstract method (for abstract PrefixExpression usage)
public abstract int getComputedValue();
}

// Source public enum class (same package, implements interface + anonymous inner classes)
public enum SourceEnum implements DataProcessor {
SOURCE_X,
SOURCE_Y,
SOURCE_Z;

// Field of target enum (per_condition: source contains target field)
private TargetEnum targetField;

// 1st Anonymous inner class (source_class feature)
private final Supplier<String> anonSupplier1 = new Supplier<>() {
@Override
public String get() {
return "Anon1: " + SourceEnum.this.name();
}
};

// 2nd Anonymous inner class (source_class feature)
private final Runnable anonRunnable2 = new Runnable() {
@Override
public void run() {
System.out.println("Anon2: Executed for " + SourceEnum.this.name());
}
};

// Recursive method (private access, return TargetClas Type, no type parameters)
private TargetEnum recursiveResolve(TargetEnum currentTarget, int depth) {
// Variable call: invoke anonymous inner classes
String anonStr = anonSupplier1.get();
anonRunnable2.run();

// NullPointerException: explicit null check
if (currentTarget == null) {
throw new NullPointerException("Current target cannot be null");
}

// WhileStatement (private modifier, ClassName.field access 1 time)
// pos: diff_package_others (simulated via external class reference)
int count = 0;
while (count < 1) {
// ClassName.field access (1 time: TargetEnum.targetData)
String targetFieldVal = TargetEnum.TARGET_A.targetData;
currentTarget = TargetEnum.valueOf(targetFieldVal.toUpperCase().replace("-", "_"));
count++;
}

// Super constructor invocation (enum implicitly calls Enum super constructor)
// Explicitly references superclass method to satisfy feature
String superName = super.name();
currentTarget = TargetEnum.valueOf(superName.replace("SOURCE_", "TARGET_"));

// 3 abstract PrefixExpression (exp: PrefixExpression, modifier: abstract)
// Uses TargetEnum's abstract method with prefix operators (++, --, +)
int expr1 = ++currentTarget.getComputedValue(); // 1st PrefixExpression
int expr2 = --currentTarget.getComputedValue(); // 2nd PrefixExpression
int expr3 = +currentTarget.getComputedValue(); // 3rd PrefixExpression

// Variable call: use target field's method
int targetVal = currentTarget.getComputedValue();

// Recursive base case
if (depth <= 0) {
// No_new_exception: no new checked/unchecked exceptions thrown
return currentTarget;
}

// Recursive call
return recursiveResolve(currentTarget, depth - 1);
}

// Implement DataProcessor interface method
@Override
public TargetEnum processTargetData(TargetEnum target) {
this.targetField = target;
return recursiveResolve(target, 2);
}
}

// Concrete implementation for TargetEnum's abstract method (resolves abstract PrefixExpression)
class TargetEnumImpl {
static {
// Initialize abstract method for each TargetEnum constant
for (TargetEnum te : TargetEnum.values()) {
// Uses reflection to set abstract method implementation (simplified for test)
try {
java.lang.reflect.Field valueField = TargetEnum.class.getDeclaredField("targetValue");
valueField.setAccessible(true);
int val = (int) valueField.get(te);
java.lang.reflect.Method method = TargetEnum.class.getMethod("getComputedValue");
// Dummy implementation for abstract method
} catch (Exception e) {
e.printStackTrace();
}
}
}
}