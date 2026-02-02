package source;

import target.TargetEnum;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

// Source: public enum class in different package with static nested and member inner classes
public enum SourceEnum {
VALUE1, VALUE2;

private String sourceField = "source_field"; // For TypeDeclarationStatement target_feature

// Static nested class
public static class SourceStaticNested {
public static int getStaticValue() {
return 1; // "1" for method_feature
}
}

// Member inner class
public class SourceMemberInner {
public TargetEnum processTarget(TargetEnum target) {
return target;
}
}

// Abstract method to be refactored
public abstract TargetEnum abstractMethod(TargetEnum target) throws IOException;

// Implement abstract method for VALUE1
static {
VALUE1 = new SourceEnum() {
@Override
public TargetEnum abstractMethod(TargetEnum target) throws IOException {
// Variable call
if (target == null) {
// NullPointerException
throw new NullPointerException("Target cannot be null");
}

// Private TypeDeclarationStatement with this.field and 1
private class LocalType {
String fieldValue = SourceEnum.this.sourceField; // this.field
int count = 1; // "1"
}
LocalType local = new LocalType();

// If statement
if (target == TargetEnum.TARGET1) {
return this.processHelper(target); // this.methodName(arguments)
}

// For loop with public instance method
List<TargetEnum> targets = new ArrayList<>();
for (int i = 0; i < SourceStaticNested.getStaticValue(); i++) {
TargetEnum processed = new SourceMemberInner().processTarget(target);
targets.add(processed);
}

// IOException
if (targets.isEmpty()) {
throw new IOException("No targets processed");
}

return targets.get(0);
}

private TargetEnum processHelper(TargetEnum target) {
return target;
}
};

VALUE2 = new SourceEnum() {
@Override
public TargetEnum abstractMethod(TargetEnum target) throws IOException {
// Call super method (from Enum class)
super.toString();
return target;
}
};
}
}

package target;

import java.util.List;

// Target: default enum class with member inner class
enum TargetEnum {
TARGET1, TARGET2;

// Member inner class (target_feature)
public class TargetMemberInner {
private TargetEnum outerEnum;

public TargetMemberInner(TargetEnum outer) {
this.outerEnum = outer;
}

public TargetEnum getOuterEnum() {
return outerEnum;
}
}

// Instance method for super.methodName(arguments)
public TargetEnum process(String arg) {
return this;
}
}