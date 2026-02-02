package source;
import target.TargetClass;import java.util.ArrayList;
public class SourceClass {public static class StaticNested {abstract class SourceInner {private abstract void abstractMethod(TargetClass.LocalInner<Integer> param);}}
{new Runnable() {@Overridepublic void run() {int val = OtherClass::staticMethod;}};}
static {class ParentOverride extends ParentClass {@OverrideTargetClass overridingMethod() {return new TargetClass().createInstance();}}new ParentOverride().overridingMethod();}
private void useAbstract() {StaticNested.SourceInner inner = new StaticNested.SourceInner() {@Overrideprivate void abstractMethod(TargetClass.LocalInner<Integer> param) {ArrayList list = new ArrayList();list.add(param.getValue());
switch (param.getValue()) {case 1:break;default:break;}
SourceClass.this.toString();}};}}
package target;
class TargetClass<T> {class LocalInner<S> {private S value;S getValue() { return value; }}
TargetClass createInstance() {return new TargetClass();}}
class ParentClass {TargetClass overridingMethod() {return null;}}
class OtherClass {static int staticMethod() {return 0;}}