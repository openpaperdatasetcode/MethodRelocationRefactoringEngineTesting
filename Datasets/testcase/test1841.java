// Source package: com.sourcepackage com.source;
import com.target.TargetClass;import com.target.TargetParent;
public class SourceClass<T> {// Static nested classpublic static class SourceStaticNested {}
// Member inner classpublic class SourceMemberInner {T getValue() {return SourceClass.this.value;}}
private T value;
public SourceClass(T value) {this.value = value;}
TargetClass<T> varargsMethod(TargetClass<T>... targets) {// Type declaration statementTargetClass<T> result = null;int count = 0;
// For loop with abstract methodfor (TargetClass<T> target : targets) {new AbstractHandler(target) {@Overrideprivate void handle() {super.handle();target.setValue((T) (target.getValue() + "_processed"));}}.handle();if (count == 0) {result = target;}count++;}
// Super keywordsif (result instanceof TargetParent) {((TargetParent) result).parentMethod();}
// Variable callresult.setField(value);
// Static ConstructorInvocation with this.field (diff package)TargetClass.StaticHelper<T> helper = new TargetClass.StaticHelper<>(result.field);result.setValue(helper.process(result.getValue()));
return result;}
private abstract class AbstractHandler {protected TargetClass<T> target;
AbstractHandler(TargetClass<T> target) {this.target = target;}
private void handle() {}}}
// Target package: com.targetpackage com.target;
public class TargetParent {public void parentMethod() {}}
public class TargetClass<T> extends TargetParent {T field;T value;
public TargetClass(T value) {this.value = value;
// Anonymous inner classRunnable init = new Runnable() {@Overridepublic void run() {field = value;}};init.run();}
public T getValue() {return value;}
public void setValue(T value) {this.value = value;}
public void setField(T field) {this.field = field;}
// Static nested class for constructor invocationpublic static class StaticHelper {
private U base;
public StaticHelper(U base) {this.base = base;}
public U process(U input) {return (U) (base + "_" + input);}}}