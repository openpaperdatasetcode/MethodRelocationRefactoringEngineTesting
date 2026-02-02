package test;
import java.util.List;import java.util.ArrayList;
class ParentClass {}
class TargetClass extends ParentClass {String targetField;class TargetInnerRec {}
public void example() {class LocalInner {}}}
class OtherClass {public OtherClass() {super(); // super.methodName()}
public List<String> createList() {return new ArrayList<>();}}
public class SourceClass {static class StaticNested {}
public void example() {class LocalInner {}}
public final Object methodToMove(TargetClass.TargetInnerRec param) {// Constructor invocation + super constructor invocationStaticNested nested = new StaticNested();OtherClass other = new OtherClass();
// Property assignment with others_class constructorList<String> list = new OtherClass().createList();
// PrefixExpression with numbers:3int num = 0;++num;++num;++num;
// Variable callTargetClass target = new TargetClass();String var = target.targetField;list.add(var + num);
return list;}}