package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
class TargetClass {public static class TargetStaticNested<T extends Number> {private T nestedField;
public TargetStaticNestedNested(T value) {this.nestedField = value;}
public T getNestedField() {return nestedField;}}}
public class SourceClass {protected String outerProtectedField = "protected_value";
class SourceInner {public final List<String> overloadingMethod(TargetClass target, int param) throws IOException {return processTarget(target, param, "default_suffix");}
public final List<String> overloadingMethod(TargetClass target, String param) throws IOException {return processTarget(target, 0, param);}
private List<String> processTarget(TargetClass target, int num, String suffix) throws IOException {List<String> result = new ArrayList<>();TargetClass.TargetStaticNested<Integer> staticNested = new TargetClass.TargetStaticNested<>(num);
class LocalInner {void validate() throws IOException {if (staticNested.getNestedField() < 0) {throw new IOException("Invalid number: " + staticNested.getNestedField());}}}
try {new LocalInner().validate();if (suffix.contains("special")) {variableCall(target, staticNested);result.add(outerProtectedField + "" + suffix);
} else {
result.add(staticNested.getNestedField() + "" + suffix);}} catch (IOException e) {result.add("error: " + e.getMessage());}
return result;}
private void variableCall(TargetClass target, TargetClass.TargetStaticNested<Integer> nested) {nested.getNestedField();}}
void methodWithLocalInner() {class SourceLocalInner {void useOverloading() throws IOException {SourceInner inner = new SourceInner();TargetClass target = new TargetClass();inner.overloadingMethod(target, 100);inner.overloadingMethod(target, "special_suffix");}}}}