package test;
import java.util.function.Consumer;
class SourceClass<S> extends TargetClass<S> {public static int staticField = 5;
public static class SourceStaticNested {}
private Consumer<S> anonymous = new Consumer<>() {@Overridepublic void accept(S s) {}};
// Override violation: parent method is protected, here private@Overrideprivate void overridingMethod(TargetClass<S> target) {super();SourceStaticNested staticNested = new SourceStaticNested();TargetClass<S> targetInstance = new TargetClass<>();
labeledLoop: while (target.inner.field < staticField) {try {target.inner.field++;variableCall = target.inner.field * SourceClass.staticField;
if (variableCall > 20) {break labeledLoop;}} catch (Exception e) {}}
S outerThisRef = SourceClass.this.genericField;}
private int variableCall;private S genericField;}
public class TargetClass<T> {public InnerClass inner = new InnerClass();private T genericField;
public class InnerClass {int field;}
protected TargetClass() {}
protected void overridingMethod(TargetClass<T> target) {}}