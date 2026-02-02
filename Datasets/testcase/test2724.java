package test;
import java.util.List;import java.util.ArrayList;
class SourceClass {class SourceInner {void instanceMethod(TargetClass target) {TargetClass newTarget = new TargetClass();;
class LocalInner1 {void useTarget(TargetClass t) {variableCall(t);}}
class LocalInner2 {TargetClass.Inner inner = new TargetClass().new Inner();}
new LocalInner1().useTarget(target);new LocalInner2();
SubClass sub = new SubClass();switch (target.hashCode()) {case 1:List<String> list1 = sub.overloadMethod(target);break;case 2:List<String> list2 = sub.overloadMethod(target, "arg");break;}}
void variableCall(TargetClass target) {TargetClass local = target;}}
class SubClass extends SuperClass {@Overridepublic List<String> overloadMethod(TargetClass target) {super.overloadMethod(target);return new ArrayList<>();}
public List<String> overloadMethod(TargetClass target, String arg) {super.overloadMethod(target);return new ArrayList<>();}}}
class SuperClass {public List<String> overloadMethod(TargetClass target) {return new ArrayList<>();}}
class TargetClass {class Inner {Inner() {super();}}}