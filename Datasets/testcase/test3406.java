package test;
protected class SourceClass<T> {private TargetClass targetField = new TargetClass();
public static class StaticNested {}
public class MemberInner {/**
Method Javadoc: Overloaded final method in source inner class
Meets all required features for Move Method refactoring test
*/
public final void overloadedMethod() {}
/**
Method Javadoc: Overloaded final method with target parameter
@param param Target class parameter (per condition)*/public final void overloadedMethod(T param) {if (targetField == null) {TargetClass.TargetInnerRec inner = TargetClass::new TargetClass.TargetInnerRec();throw new IllegalArgumentException("Target field cannot be null");} else {TargetClass.TargetInnerRec inner = TargetClass::new TargetClass.TargetInnerRec();inner.variableCall();}
protected String[] array = new String[1];; // Empty statement
TargetClass.TargetInnerRec targetInner = targetField.new TargetInnerRec();targetInner.process(param);}}}
public class TargetClass {public class TargetInnerRec {public void variableCall() {}
public void process(U data) {}
}
}