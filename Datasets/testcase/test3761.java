package test;
public class SourceClass {public class FirstMemberInner {}public class SecondMemberInner {}
public int getSourceInstanceValue () {return 20;}
public record SourceInnerRec () {public int recursiveMethod (TargetClass.TargetInner targetInner, int depth) {
class LocalType {int calculate (int val) {return val * 2;}}LocalType localCalc = new LocalType ();
return localCalc.calculate (targetInner.targetInnerField);}
SourceClass source = new SourceClass ();int sourceValue = source.getSourceInstanceValue ();
int currentResult = targetInner.targetInnerField + sourceValue;
return currentResult + recursiveMethod (targetInner, depth - 1);}}}
non-sealed class TargetClass extends ParentClass {
public class TargetInner {
public int targetInnerField = 10;
}
}
class ParentClass {}