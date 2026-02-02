package same;
abstract class Source {private Target sourceTargetField = new Target();
Runnable firstAnonInner = new Runnable () {@Overridepublic void run () {System.out.println ("First anonymous inner class runs");}};
CustomAbstractClass secondAnonInner = new CustomAbstractClass () {@Overridevoid doSomething () {System.out.println ("Second anonymous inner class does something");}};
strictfp Object varargsMethod (Target... targets) {
Object var = sourceTargetField;
Target.TargetMemberInner inner = sourceTargetField.new TargetMemberInner ();inner.innerMethod ("varargs_process");
if (targets.length> 0) {return targets [0].getTargetField ();}return var;}
abstract static class CustomAbstractClass {abstract void doSomething ();}}
class Target implements TargetInterface {private String targetField = "target_default_val";
class TargetMemberInner {void innerMethod (String param) {System.out.println ("Target inner method:" + param);}}
@Overridepublic void interfaceMethod () {System.out.println ("Target implements TargetInterface");}
public String getTargetField () {return targetField;}}
interface TargetInterface {void interfaceMethod ();}