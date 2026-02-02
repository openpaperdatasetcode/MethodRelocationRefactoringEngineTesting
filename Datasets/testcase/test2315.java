package same.pkg;
import java.lang.reflect.Method;import java.util.function.Supplier;
protected class Source {class MemberInner {private int callMethod(Target<String> target) {return target.new Inner().varargsMethod(1) + target.new Inner().varargsMethod("a");}}
void methodWithLocal() {class LocalInner {void invoke() {new Source().varargsMethod(new Target<>(), 1, 2);}}}
private void varargsMethod(Target<Integer> targetParam, Integer... args) {// NullPointerExceptionif (targetParam == null) throw new NullPointerException();
// VariableDeclarationStatementpublic int count = 1;this.count = targetParam.field;
// Constructor invocationTarget.StaticNested nested = new Target.StaticNested();
// Type declaration statementTarget.Inner inner;
// SuperMethodInvocationprivate Object superCall = super.toString();
// Lambda expressionSupplier<Void> lambda = () -> { System.out.println(this.count); return null; };
// Used by reflectiontry {Method method = Source.class.getMethod("varargsMethod", Target.class, Integer[].class);method.invoke(this, targetParam, args);} catch (Exception e) {}
// Variable call & access_instance_fieldinner = targetParam.new Inner();int val = inner.innerField;}
int value;}
/**
Javadoc for Target class
@param <T> type parameter*/public class Target<T> {int field;static class StaticNested {}
class Inner {int innerField;
private int varargsMethod(int a) { return a; }private int varargsMethod(String s) { return s.length(); }}}