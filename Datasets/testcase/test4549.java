package test;
import java.util.function.Function;import java.io.IOException;
interface SourceInterface extends Function<TargetInterface<String>, Object> {
static class SourceStaticNested {
class InnerRecursive {
private Object recursiveMethod (TargetInterface<String> target, int depth) throws IOException {
class LocalHandler {Object processKeyword (Object keyword) {return keyword.toString () + "_processed";}}LocalHandler handler = new LocalHandler ();Object result;
if (depth <= 0) {
result = target.targetField + "_" + target.innerClass ().handle ();return result;}
Object thisKeywordResult = handler.processKeyword (this);Object superKeywordResult = handler.processKeyword (super);
target.updateField (target.targetField + "_depth" + depth);
Object recursiveResult = recursiveMethod (target, depth - 1);
result = thisKeywordResult + "|" + superKeywordResult + "|" + recursiveResult;return result;}}
private final InnerRecursive inner = new InnerRecursive();
public Object startRecursion (TargetInterface<String> target) throws IOException {
Runnable anon = new Runnable () {
@Override
public void run () {
try {
inner.recursiveMethod (target, 2);
} catch (IOException e) {
e.printStackTrace ();
}
}
};
anon.run ();
return inner.recursiveMethod (target, 3);
}
}
@Override
default Object apply (TargetInterface<String> target) {
try {
return new SourceStaticNested().startRecursion(target);
} catch (IOException e) {
return e.getMessage();
}
}
}
public interface TargetInterface<K extends CharSequence> {
String targetField = "initial_value";
default void updateField (K newField) {
Supplier<K> anonSupplier = new Supplier<K>() {
@Override
public K get() {
return newField;
}
};
targetField = anonSupplier.get().toString();
}
default TargetInner innerClass () {
return new TargetInner ();
}
class TargetInner {
public String handle () {
return "inner_class_result";
}
}
}