package test;
import java.util.List;import java.util.ArrayList;
/**
Javadoc for TargetClass
Contains local inner class and methods for refactoring test*/public class TargetClass {public int targetField;
public void example() {class LocalInner {}}
public Object baseMethod() {return null;}}
public class TargetSubClass extends TargetClass {@Overridepublic Object baseMethod() {super.baseMethod();return new TargetClass();}}
public class SourceClass {class MemberInner {}
public SourceClass() {Runnable r = new Runnable() {@Overridepublic void run() {}};}
@MyAnnotation(attr = TargetSubClass::baseMethod)TargetClass methodToMove(TargetClass target) {class TypeDecl {}TypeDecl type = new TypeDecl();
MemberInner inner = SourceClass.this.new MemberInner();int var = target.targetField;
try {for (Object obj : new ArrayList<>()) {if (obj == null) {continue;}target.targetField++;}} finally {}
TargetSubClass sub = new TargetSubClass();sub.baseMethod();return target;}}
@interface MyAnnotation {Class<?> attr();}