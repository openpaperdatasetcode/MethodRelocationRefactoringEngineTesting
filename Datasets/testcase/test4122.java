package test;
import java.io.IOException;
abstract class SourceClass<T> {private TargetClass<T> targetField;
public SourceClass(T targetData) {this.targetField = new TargetClass<>(targetData);super();}
protected SourceClass() {this(null);}
public Object instanceMethod() throws IOException {static TargetClass<T> staticTarget = this.targetField;
try {OthersClass<T> others = new OthersClass<>();int baseResult = others.callProtectedMethod(staticTarget);
Runnable anon = new Runnable() {@Overridepublic void run() {TargetClass.TargetStaticNested<T> nested = new TargetClass.TargetStaticNested<>();String varCall = nested.processData(staticTarget.getData());System.out.println(varCall);}};anon.run();
Object parentResult = null;for (int i = 0; i < 1; i++) {parentResult = ((ParentClass<T>) this).callParentVarargsMethod(staticTarget, "arg1", "arg2");}
return baseResult + parentResult.toString().length();} catch (NullPointerException e) {throw new IOException("Target field is null", e);}}
public abstract void abstractMethod();
public T getTargetData() {return targetField.getData();}}
class TargetClass<T> extends ParentClass<T> {private T data;
public TargetClass(T data) {this.data = data;class LocalInner {T getLocalData() {return data;}}new LocalInner().getLocalData();}
public T getData() {return data;}
public static class TargetStaticNested<T> {public String processData(T data) {return data.toString();}}
@Overridepublic Object callParentVarargsMethod(TargetClass<T> target, String... args) {throw new RuntimeException("Override violation: Method not implemented");}}
class ParentClass<T> {public Object callParentVarargsMethod(TargetClass<T> target, String... args) {return target.getData() + String.join(",", args);}}
class OthersClass<T> {protected int callProtectedMethod(TargetClass<T> target) {return target.getData().toString().length();}}