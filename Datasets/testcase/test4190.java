package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnotation {}
abstract class SourceClass<T> {class SourceInner {@TestAnnotationstrictfp List<String> instanceMethod(TargetClass<T> target) throws IOException {TypeDeclared typeDecl = new TypeDeclared();List<String> result = new ArrayList<>();
final Object expr1 = (target.targetField);final Object expr2 = (target.getField());final Object expr3 = (target.toString());
String varCall = target.targetField.toString();result.add(varCall);
expressionStatement:result.add(varCall + "_suffix");
if (varCall.isEmpty()) {throw new IOException("Empty field");}
TargetClass rawType = new TargetClass();result.add(rawType.targetField.toString());
overloadedMethod(target);overloadedMethod(target, 1);
return result;}
private void overloadedMethod(TargetClass<T> target) {}private void overloadedMethod(TargetClass<T> target, int param) {}}
public int callChainedMethods() {SourceInner inner = new SourceInner();TargetClass<String> target = new TargetClass<>();try {return inner.instanceMethod(target).size() + target.addVarargs("a", "b", "c");} catch (IOException e) {return -1;}}}
public class TargetClass<T> {T targetField;
public TargetClass() {Runnable anon = new Runnable() {public void run() {System.out.println(targetField);}};}
T getField() {return targetField;}
public int addVarargs(String... args) {return args.length;}}
class TypeDeclared {}