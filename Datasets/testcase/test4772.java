package test;
import java.util.function.Supplier;
class ParentClass {}
public class SourceClass extends ParentClass {public static class StaticNested {}
{new Runnable() {public void run() {}};}
/**
Constructor method to create TargetClass.Inner.Rec
@param targetInner TargetClass.Inner instance
@return TargetClass.Inner.Rec instance*/protected TargetClass.Inner.Rec SourceClass(TargetClass.Inner targetInner) {@MyAnnotation(value = SourceClass::staticMethod)class LocalType {}
TargetClass target = new TargetClass();TargetClass.Inner.Rec rec = targetInner.new Rec();
super();
if (targetInner.field == null) {throw new NullPointerException();}
StaticNested nested = new StaticNested();nested.toString();
return SourceClass.this.createRec(rec);}
private static Object staticMethod(int arg) {return arg;}
private TargetClass.Inner.Rec createRec(TargetClass.Inner.Rec rec) {return rec;}}
abstract class TargetClass {public class Inner {public String field;
public class Rec {}
{new Runnable() {public void run() {int result = (field != null) ? OtherClass::process : 0;}};}}}
@interface MyAnnotation {Supplier<Object> value();}
class OtherClass {public static int process() {return 0;}}