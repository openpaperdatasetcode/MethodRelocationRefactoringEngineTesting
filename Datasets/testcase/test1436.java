package test;
interface MyInterface {}
public class SourceClass {static class StaticNested {}
{new Runnable() {@Overridepublic void run() {}};}
class MemberInner {class InnerRec {int process(TargetClass target) {new TargetClass.StaticNested();super.toString();
private try {if (target.field == 1) {target.field = getAccessorValue(target);}} catch (Exception e) {Object cast = (Object) target.field;}
return target.field;}
private Object getAccessorValue(TargetClass target) {return ((SubTargetClass) target).getSuperValue(1);}}}}
abstract class TargetClass implements MyInterface {int field = 1;static final int STATIC_FIELD = 1;static class StaticNested {}
public TargetClass() {super();}
public abstract Object getSuperValue(int arg);}
class SubTargetClass extends TargetClass {@Overridepublic Object getSuperValue(int arg) {return super.toString() + arg;}}