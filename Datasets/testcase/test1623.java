package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
interface GenericInterface<T> {TargetClass<T> process(TargetClass<T> target);}
public class SourceClass<T> implements GenericInterface<T> {private String outerPrivate = "private_data";
public class MemberInner implements GenericInterface<T> {// Overriding method in source_inner@Overridepublic TargetClass<T> process(TargetClass<T> target) {// Local inner classclass TargetHandler {TargetClass<T> handle(TargetClass<T> t) {// Access outer private fieldt.data = (T) (outerPrivate + "_handled");return t;}}TargetHandler handler = new TargetHandler();
// Private SuperConstructorInvocation with this.field (1 occurrence)private class SubTarget extends TargetClass<T> {SubTarget(T data) {super();this.data = data; // this.field}}TargetClass<T> subTarget = new SubTarget((T) "sub_data");
// Constructor invocationTargetClass<T> newTarget = new TargetClass<>();
// Labeled statementprocessLabel: {// Switch statementswitch (target.getType()) {case 1:newTarget.data = (T) "type1";break;case 2:newTarget.data = (T) "type2";break;default:break processLabel;}}
// Expression statementtarget.data = (T) "updated";
// Variable call - access target's fieldList<T> items = new ArrayList<>();items.add(target.data);
// Public varargs method in object initialization (3 parameters, super.method())Object wrapper = new Object() {public Object combine(T... values) {super.toString();return values[0] + values[1] + values[2];}};
// Used by reflectiontry {Method method = wrapper.getClass().getMethod("combine", Object[].class);method.invoke(wrapper, target.data, subTarget.data, newTarget.data);} catch (Exception e) {// no new exception}
return handler.handle(newTarget);}}
@Overridepublic TargetClass<T> process(TargetClass<T> target) {return new MemberInner().process(target);}}
class TargetClass<T> {public T data;private int type;
public TargetClass() {// Anonymous inner classRunnable initializer = new Runnable() {@Overridepublic void run() {data = (T) "initial";}};initializer.run();}
public int getType() {return type;}
public void setType(int type) {this.type = type;}}