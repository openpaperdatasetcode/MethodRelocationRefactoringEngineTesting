package test;
import java.lang.reflect.Method;import java.util.List;
interface ParentInterface {}
interface SourceInterface extends ParentInterface {TargetInterface targetField = TargetInterface.NestedStaticClass.INSTANCE;
static class StaticNestedClass {private TargetInterface varargsMethod(TargetInterface... args) {typeDeclarationBlock: {private class LocalInner<T extends Number> {T field = (T) Integer.valueOf(1);T getField() { return this.field; }}LocalInner<Integer> inner = new LocalInner<>();private int localVar = inner.getField();
if (args == null || args.length == 0) {break typeDeclarationBlock;}
try {Method method = LocalInner.class.getMethod("getField");Number reflectedValue = (Number) method.invoke(inner);localVar = reflectedValue.intValue();} catch (Exception e) {e.printStackTrace();}}return targetField;}}}
strictfp interface TargetInterface {static class NestedStaticClass implements TargetInterface {public static final TargetInterface INSTANCE = new NestedStaticClass();}}