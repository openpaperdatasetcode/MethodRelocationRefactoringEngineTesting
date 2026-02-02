package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
private class SourceClass {{new Runnable() {};new Thread() {};}
private Object overloadedMethod(TargetClass param) {TargetClass target = new TargetClass();target.instanceMethod();variableCall();
List<String>[] arrays = new List[] { ParentClass::protectedMethod };
return new Object();}
private Object overloadedMethod(int num) {return new Object();}
private void variableCall() {}}
public class TargetClass extends ParentClass {void instanceMethod() {class LocalInner {}}}
class ParentClass {protected List<String> protectedMethod() {return new ArrayList<>();}}
class SubTargetClass extends TargetClass {final String overloadedMethod() {return "";}
final String overloadedMethod(int i) {return String.valueOf(i);}}
class Test {void test() {for (int i = 0; i < 5; i++) {Supplier<String> supplier = SubTargetClass::overloadedMethod;}}}