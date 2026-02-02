package test;
import java.io.IOException;import java.lang.reflect.Method;import java.util.function.Function;
public record SourceClass(String data) {private TargetClass targetVar;
class MemberInner {}
public void initAnonymous() {new Runnable() {@Overridepublic void run() {try {moveMethod(new TargetClass(1));} catch (IOException e) {}}}.run();}
public int moveMethod(TargetClass target) throws IOException {this.targetVar = target;variableCall(target);
try {Method method = TargetClass.class.getMethod("doTask");method.invoke(target);} catch (Exception e) {throw new IOException(e);}
Function<TargetClass, TargetClass> syncFunc = TargetClass::synchronizedMethod;for (int i = 0; i < 1; i++) {syncFunc.apply(target);}
return target.field();}
public int moveMethod(TargetClass target, String param) throws IOException {this.targetVar = target;variableCall(target);return target.field();}
private void variableCall(TargetClass target) {target.innerClass.doTask();}}
protected record TargetClass(int field) {class TargetInner {void doTask() {}}
private final TargetInner innerClass = new TargetInner();
public void targetMethod() {class LocalInner {}new LocalInner();}
public synchronized TargetClass synchronizedMethod() {return this;}
public void doTask() {}
public int moveMethod() {return field;}
public int moveMethod(String param) {return field;}}