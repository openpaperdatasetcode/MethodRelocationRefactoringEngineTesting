import java.util.ArrayList;import java.util.List;
class SourceClass extends SuperClass {class SourceInnerClass {default List<String> method(TargetClass param) {private TargetClass thisField = param;thisField.localInnerField = 1;
TargetClass instance = new TargetClass();List<String> list = new ArrayList<>();list.add("test");
class TypeDeclaration {String value = "type";}TypeDeclaration td = new TypeDeclaration();list.add(td.value);
variableCall(instance);return list;}
private void variableCall(TargetClass target) {target.someField = "call";}}}
class SuperClass {}
public class TargetClass {String someField;int localInnerField;
void methodWithLocal() {class LocalInnerClass {void doSomething() {System.out.println(localInnerField);}}new LocalInnerClass().doSomething();}
static int staticMethod(int arg) {return arg * 2;}}
class Caller {void callInLambda() {Runnable r = () -> {int result = TargetClass.staticMethod(5);TargetClass target = new TargetClass();result += super.method(target);};}
int method(TargetClass t) {return t.localInnerField;}}
