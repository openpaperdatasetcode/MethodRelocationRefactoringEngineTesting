package test;
import java.lang.reflect.Method;
interface MyInterface {}
class ParentClass {}
protected class SourceClass extends ParentClass implements MyInterface {class MemberInner {class InnerRec {public void handle(TargetClass.InnerRec param) {try {new Runnable() {@Overridepublic void run() {super.toString();}};
for (int i = 0; i < 5; i++) {param.setValue(i);System.out.println(param.getValue());}
Method method = TargetClass.InnerRec.class.getMethod("getValue");Object value = method.invoke(param);
if (value != null) {TargetClass result = new SubClass().callMethod(param);} else {TargetClass result = new SubClass().callMethod(null);}} catch (Exception e) {e.printStackTrace();}}}}}
public class TargetClass {class InnerRec {private int value;
public void setValue(int val) {this.value = val;}
public int getValue() {return value;}}}
class SubClass extends TargetClass {TargetClass callMethod(TargetClass.InnerRec inner) {if (inner != null) {return super.new InnerRec().getValue() > 0 ? new TargetClass() : null;} else {return new TargetClass();}}}