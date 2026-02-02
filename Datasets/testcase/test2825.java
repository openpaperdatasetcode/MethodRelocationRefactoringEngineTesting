package test;
public class SourceClass {protected int outerProtected = 1;
public class MemberInner {public class SourceInnerRec {/**
Method Javadoc: Instance method with no parameters*/private int methodToMove() {try {// Type declaration statementTargetClass target = new TargetClass();TargetClass.MemberInnerTarget inner = target.new MemberInnerTarget();
// Object initialization with abstract method featureAbstractSubSource abstractObj = new AbstractSubSource() {@Overrideprotected TargetClass abstractMethod() {super.toString(); // Super constructor invocationreturn target;}};TargetClass resultTarget = abstractObj.abstractMethod();
// Local inner classclass LocalInner {void process() {// Access_outer_protected + variable callinner.field = SourceClass.this.outerProtected;System.out.println(inner.field);}}new LocalInner().process();
return resultTarget.field;} catch (Exception e) {// Requires_try_catch handlingreturn -1;}}}}}
abstract class AbstractSubSource {protected abstract TargetClass abstractMethod();}
class TargetClass {int field;
public class MemberInnerTarget {int field;}}