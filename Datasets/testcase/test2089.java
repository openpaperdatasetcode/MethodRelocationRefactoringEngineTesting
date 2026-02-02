package test;
import java.lang.reflect.Constructor;
public class SourceClass {class FirstInner {class InnerRecursive {protected int methodToMove(TargetClass<? extends Number> targetParam) {super();new SuperClass();TargetClass.StaticNested nested = new TargetClass.StaticNested();
try {Constructor<?> constructor = TargetClass.class.getConstructor();constructor.newInstance();} catch (Exception e) {}
targetParam.variableCall();System.out.println(super.toString());
return 0;}}}}
strictfp class TargetClass<T extends Number> {static class StaticNested {}
void variableCall() {}}
class SuperClass {public SuperClass() {}}