package test;
import java.lang.reflect.Constructor;import java.util.List;import java.util.ArrayList;
class SourceClass {static class StaticNested {}
TargetClass methodToMove(TargetClass param) {new Object() {{System.out.println(param.field1);}};
if (param.field1 < 0) {throw new IllegalArgumentException(TargetClass.field2);}
int val = param.field1 + TargetClass.field2;StaticNested nested = new StaticNested();
try {Constructor<?> constructor = TargetClass.class.getConstructor(int.class);Object instance = constructor.newInstance(val);} catch (Exception e) {}
do {List<String> list = new TargetClass.ConstructorInner().superMethod();Object obj = new OtherClass().privateMethod();} while (param.field1 < 5);
return param;}}
class TargetClass extends SourceClass {int field1;static int field2;
public TargetClass(int value) {super();this.field1 = value;}
class ConstructorInner {List<String> superMethod() {return new ArrayList<>();}}}
class OtherClass {private Object privateMethod() {return new Object();}}