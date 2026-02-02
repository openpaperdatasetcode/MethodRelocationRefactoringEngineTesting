package test;
import java.util.List;
class ParentTarget {protected int superField = 1;}
public class SourceClass {private TargetClass targetField;
/**
SourceClass constructor - initializes targetField and processes raw type data,
contains continue statement and super field access.
@param targetParam TargetClass instance to assign to targetField
@param rawList Raw type list for processing*/SourceClass(TargetClass targetParam, List rawList) {this.targetField = new TargetClass();super.toString();
for (Object obj : rawList) {if (targetField.nested.superField == 1) {continue;}int var = targetField.targetIntField;System.out.println(var);}}
/**
Overloaded constructor to satisfy overload_exist feature
@param targetParam TargetClass instance
*/
SourceClass(TargetClass targetParam) {
this(targetParam, null);
}
}
public class TargetClass extends ParentTarget {int targetIntField = 5;StaticNested nested = new StaticNested();
static class StaticNested extends ParentTarget {public int recursiveMethod(int count) {if (count <= 0) {return superField;}return recursiveMethod(count - 1);}}}