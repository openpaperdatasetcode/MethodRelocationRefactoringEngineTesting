package same.pkg;
import java.lang.reflect.Method;import java.util.function.Supplier;
enum Source<T extends Number> {INSTANCE;
static class StaticNested {}
strictfp Target instance instanceMethod(Target targetParam) {// Constructor invocationTarget.MemberInner inner = targetParam.new MemberInner();
// If statementif (targetParam.field > 0) {System.out.println("Positive field value");}
// Synchronized statementsynchronized (targetParam) {targetParam.field++;}
// Expression statementint val = targetParam.field;
// Variable callTarget result = Target.INSTANCE;
// With boundsSupplier<? extends Number> supplier = () -> targetParam.field;
// Used by reflectiontry {Method method = Target.class.getMethod("getValue");method.invoke(targetParam);} catch (Exception e) {}
return result;}
void methodWithLocal() {class LocalInner {}}}
private enum Target {INSTANCE;
int field;
class MemberInner {}
int getValue() {return field;}}