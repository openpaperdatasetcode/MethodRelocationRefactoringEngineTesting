import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public record SourceRecord(String sourceField) {public static final List<String> methodToMove(TargetRecord target) {class LocalInner {void doSomething() {assert target.targetField() != null;}}new LocalInner().doSomething();
Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println(target.targetField());}};anonymous.run();
class TypeDecl {}TypeDecl typeInst = new TypeDecl();System.out.println(typeInst);
super.toString();TargetSubclass subclass = new TargetSubclass("sub");subclass.protectedMethod();
int i = 0;do {new TargetRecord("temp").targetMethod();i++;} while (i < 1);
try {Method method = TargetRecord.class.getMethod("targetMethod");} catch (NoSuchMethodException e) {}
return new ArrayList<>();}}
private record TargetRecord(String targetField) {public void targetMethod() {}
Runnable targetAnonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};}
class TargetSubclass extends TargetRecord {public TargetSubclass(String targetField) {super(targetField);}
protected void protectedMethod() {}}