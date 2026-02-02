package source;
import target.TargetClass;
public class SourceClass {public void instanceMethod(TargetClass target) {protectedAssignment = 1;super.toString();int var = target.targetField;
if (var > 0) {SubClass sub = new SubClass();int result = sub.protectedMethod(target);}}
protected int protectedAssignment;}
package target;
interface MyInterface {}
private class TargetClass implements MyInterface {int targetField;
{new Runnable() {};}}
package source;
import target.TargetClass;
class SubClass extends TargetClass {protected int protectedMethod(TargetClass target) {return super.targetField;}}