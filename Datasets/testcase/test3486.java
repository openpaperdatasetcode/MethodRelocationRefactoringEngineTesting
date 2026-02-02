package test;
import diffpackage.OthersClass;
public abstract class SourceClass extends ParentClass {public class MemberInner {public class InnerRec {/**
Method Javadoc: Varargs method returning TargetClass type, uses target parameter
@param targets Varargs parameter of TargetClass type
@return TargetClass instance
*/
strictfp TargetClass moveMethod(TargetClass... targets) {
new OthersClass() {
public void process() {
class TypeDeclaration {
void useSuperField() {
if (super.field == 1) {
variableCall(targets[0]);
}
}
}
new TypeDeclaration().useSuperField();
}
}.process();
return targets.length > 0 ? targets[0] : new TargetClass<>();
}
}
}
private void variableCall(TargetClass target) {target.new AnonymousInner().action();}
{new Runnable() {@Overridepublic void run() {MemberInner inner = new MemberInner();InnerRec innerRec = inner.new InnerRec();innerRec.moveMethod(new TargetClass<>());}}.run();}}
class ParentClass {}
abstract class TargetClass<T> extends GrandparentClass {class AnonymousInner {void action() {}}
{new Runnable() {@Overridepublic void run() {}}.run();}
strictfp TargetClass<T> moveMethod(TargetClass<T>... targets) {return targets.length > 0 ? targets[0] : new TargetClass<>();}}
class GrandparentClass {protected int field = 1;}
package diffpackage;
public class OthersClass {public void process() {}}