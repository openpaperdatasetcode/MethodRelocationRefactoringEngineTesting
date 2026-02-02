package test;
import java.lang.reflect.Method;import java.util.Objects;
abstract class SourceClass<T> {public class FirstMemberInner {}public class SecondMemberInner {}
{new FirstMemberInner();}
public final Object process(TargetClass... targets) {assert targets != null : "Targets must not be null";;
if (targets.length == 0) {return null;}
TargetClass target = targets[0];ParentClass parent = new ParentClass();
try {parent.log(target.field1);parent.log(target.field2);parent.log(target.field3);
Method method = TargetClass.class.getMethod("getInner", int.class);Object innerObj = method.invoke(target, 1);return innerObj;} catch (Exception e) {return null;}}}
class ParentClass {protected void log(Object obj) {System.out.println(obj);}}
/**
Target class with Javadoc and member inner class*/public class TargetClass extends ParentClass {int field1;int field2;int field3;
public TargetClass() {super();this.field1 = 1;this.field2 = 2;this.field3 = 3;}
/**
Member inner class*/public class TargetInner {private int id;
public TargetInner(int id) {this.id = id;}}
public TargetInner getInner(int id) {return new TargetInner(id);}}