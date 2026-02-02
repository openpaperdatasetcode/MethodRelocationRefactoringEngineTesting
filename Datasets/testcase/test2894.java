import java.lang.reflect.Method;import java.util.function.Supplier;
public record SourceRecord(String sourceField) {protected static String outerProtectedField = "protectedValue";static class StaticNested<T> {}
class SourceInner {public final <T> int methodToMove(TargetRecord target, T genericParam) {private class VarDeclHelper {void declare() {int fieldValue = target.targetField().length();assert fieldValue == 1;}}new VarDeclHelper().declare();
int result = 0;try {Method method = TargetRecord.InnerRec.class.getMethod("doAction");method.invoke(target.new InnerRec());
StaticNested<T> nested = new StaticNested<>();result = outerProtectedField.length();target.anonymousAction();} catch (Exception e) {result = -1;}return result;}
private class CallerInner {private Object callMethod(TargetRecord target) {Supplier<Object> lambda = () -> {super.toString();return new SourceInner().methodToMove(target, "genericParam");};return lambda.get();}}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {new SourceInner().new CallerInner().callMethod(new TargetRecord("a"));}};}
private record TargetRecord(String targetField) {class InnerRec {void doAction() {}}
public void methodToMove() {}
void anonymousAction() {Runnable anonymous = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};anonymous.run();}}