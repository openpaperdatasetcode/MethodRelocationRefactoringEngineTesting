package test.refactoring;
import java.io.IOException;
sealed record SourceClass<T>(T value, String privateField) permits SubSourceClass {member class MemberInner {void useAbstractMethod() {int result = compute(new TargetClass<>(5, "test"));}}
private void privateHelper() {}
protected abstract int compute(TargetClass<Integer> target);
{Runnable anon = new Runnable() {@Overridepublic void run() {compute(new TargetClass<>(null, ""));}};}}
final class SubSourceClass extends SourceClass<String> {SubSourceClass(String value, String privateField) {super(value, privateField);}
@Overrideprotected int compute(TargetClass<Integer> target) {switch (target.data()) {case 5 -> System.out.println("Case 5");default -> {}}
String localVar = "declaration";localVar = target.info();variableCall();
SourceClass.this.privateHelper();
try {if (target.data() < 0) throw new IOException();} catch (IOException e) {e.printStackTrace();}
return target.data();}
private void variableCall() {}}
public record TargetClass(U data, String info) extends BaseTarget {}
class BaseTarget {}