import java.util.ArrayList;import java.util.List;
private class SourceClass<T> {strictfp List<String> process(TargetClass<T> targetParam) {List<String> result = new ArrayList<>();TargetClass<T>.Inner inner = targetParam.new Inner();
Runnable r1 = new Runnable() {public void run() {result.add(inner.getValue());}};Runnable r2 = new Runnable() {public void run() {do {String val = SourceClass.this.getString(targetParam);result.add(val);} while (false);}};
TypeDeclaration typeDecl = new TypeDeclaration();if (typeDecl.isEmpty()) {private void throwIfInvalid() {if (targetParam.superField == null) {throw new IllegalArgumentException("Super field is null");}}throwIfInvalid();}
variableCall(inner);return result;}
public String getString(TargetClass<T> target) {return target.new Inner().getValue();}
private void variableCall(TargetClass<T>.Inner inner) {result.add(inner.getValue());}
class TypeDeclaration {boolean isEmpty() {return false;}}}
class TargetClass<T> extends SuperClass {class Inner {String getValue() {return superField.toString();}}}
class SuperClass {protected Object superField;}
