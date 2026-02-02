package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
public enum SourceEnum {VALUE1, VALUE2;
class InnerClass1 {protected List<String> overloadedMethod() {TargetEnum param = TargetEnum.VALUE_A;if (param == null) {throw new NullPointerException();}assert param != null : "Param is null";int i = 0;while (i < 5) {i++;}InnerClass2 inner = new InnerClass2();inner.doSomething();synchronized (OtherPackage.StaticField.class) {param.getValue();}try {Method method = InnerClass2.class.getMethod("doSomething");} catch (NoSuchMethodException e) {// Handle exception}return new ArrayList<>();}}
class InnerClass2 {void doSomething() {}}}
public enum TargetEnum {VALUE_A, VALUE_B;
String getValue() {class LocalInner {void helper() {}}return "value";}}
// In different package: otherpackagepackage otherpackage;
public class OtherPackage {public static Object StaticField;}
