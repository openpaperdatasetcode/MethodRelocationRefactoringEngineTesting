.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Others class for call_method feature
class OthersClass {
// Final varargs method (call_method: others_class, final, varargs)
public final String combineStrings(String... parts) {
StringBuilder sb = new StringBuilder();
for (String part : parts) {
sb.append(part);
}
return sb.toString();
}
}

// Target public interface (with member inner class)
public interface TargetInterface {
// Member inner class (target_feature)
class TargetInner {
private String innerData;

public TargetInner(String data) {
this.innerData = data;
}

// Generic method for inner_class method feature
public <T> List<T> wrapData(T item) {
List<T> list = new ArrayList<>();
list.add(item);
return list;
}

public String getInnerData() {
return innerData;
}
}

String DEFAULT_VALUE = "default-target";
}

// Source interface (no modifier, same package, with local/anonymous inner classes)
interface SourceInterface {
// Method with local inner class (source_class feature)
default void createLocalInner() {
class SourceLocalInner {
String process(String input) {
return input + "_local-processed";
}
}
new SourceLocalInner().process("test");
}

// Anonymous inner class (source_class feature)
Runnable sourceAnonymous = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous executed");
}
};

// Source inner class (source_inner: method_position)
class SourceInner {
// Instance method to be moved (public access, return TargetClas Type)
// Per_condition: contains TargetInterface.TargetInner parameter
public TargetInterface.TargetInner processData(TargetInterface.TargetInner targetParam) {
// Type declaration statement
OthersClass others = new OthersClass();
List<String> results = new ArrayList<>();

// Try statement
try {
// 2 protected StringLiteral expressions
String lit1 = "protected-literal-1";
String lit2 = "protected-literal-2";
results.add(lit1);
results.add(lit2);
} catch (Exception e) {
// No_new_exception: no new checked/unchecked exceptions thrown
results.add(TargetInterface.DEFAULT_VALUE);
}

// Do-while loop with 1 generic inner_class method call
int count = 0;
do {
// inner_class generic method call: instanceReference.methodName(arguments)
List<String> wrapped = targetParam.wrapData("loop-" + count); // 1 call
results.addAll(wrapped);
count++;
} while (count < 2);

// For loop with break statement
for (String item : results) {
if (item.contains("loop-1")) {
break; // Break statement
}
System.out.println(item);
}

// Expression statement
targetParam.getInnerData();

// Variable call: use target parameter and anonymous inner class
sourceAnonymous.run();
String paramData = targetParam.getInnerData();

// Call_method: others_class method via method reference in ternary operator
Function<String[], String> combiner = others::combineStrings; // instanceReference::methodName
String combined = (paramData.isEmpty())
? combiner.apply(new String[]{"empty", "data"})
: combiner.apply(new String[]{paramData, "processed"});

// Return new TargetClas Type instance
return new TargetInterface.TargetInner(combined);
}
}
}