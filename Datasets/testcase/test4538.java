package test;
import java.lang.reflect.Method;import java.io.IOException;
private class SourceClass {static class SourceStaticNested {class InnerRecursive {final TargetClass instanceMethod(TargetClass targetParam) throws IOException {Runnable anon = new Runnable() {@Overridepublic void run() {targetParam.updateField(10);}};anon.run();
expressionStatement(targetParam);if (targetParam.getField() < 5) {throw new IOException("Field value too small");}
try {Method targetMethod = TargetClass.class.getMethod("synchronizedMethod", String.class);String reflectResult = (String) targetMethod.invoke(targetParam, "reflection_call");targetParam.setField(reflectResult.length());} catch (Exception e) {throw new IOException("Reflection failed", e);}
TargetClass newTarget = new TargetClass(this);return newTarget.getField() > targetParam.getField() ? newTarget : targetParam;}
private void expressionStatement(TargetClass target) {int val = target.getField() * 2;target.setField(val);}}}
public TargetClass processTarget(TargetClass target) throws IOException {return new SourceStaticNested.InnerRecursive().instanceMethod(target);}}
public class TargetClass {private int targetField;
public TargetClass(SourceClass.SourceStaticNested.InnerRecursive inner) {this.targetField = 3;}
public synchronized String synchronizedMethod(String param) {return "processed_" + param;}
public int getField() {Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Target anon class run");}};anon.run();return targetField;}
public void setField(int targetField) {this.targetField = targetField;}
public void updateField(int val) {this.targetField = val;}}