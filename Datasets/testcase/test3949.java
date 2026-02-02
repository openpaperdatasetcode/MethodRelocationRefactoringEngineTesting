package test;
import java.lang.reflect.Method;import java.io.IOException;
protected class TargetClass {static class TargetNestedClass {TargetClass targetInstance;}}
private class SourceClass {private TargetClass.TargetNestedClass targetField;
static class SourceNestedClass {TargetClass methodToMove() {TargetClass result = null;try {result = targetField.targetInstance;Method method = TargetClass.class.getMethod("toString");method.invoke(result);throw new IOException();} catch (Exception | IOException e) {}return result;}}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}}
