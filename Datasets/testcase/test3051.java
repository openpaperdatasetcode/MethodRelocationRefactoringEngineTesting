package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;import java.util.regex.Pattern;
protected @interface TargetClass {class TargetInnerRec {}int value();default void init() {Runnable r = new Runnable() {public void run() {}};}}
@interface SourceClass {class SourceInner {/**
Method Javadoc
*/
protected List<String> methodToMove(TargetClass.TargetInnerRec... params) {
return methodToMove(new ArrayList<>(), params);
}
protected List<String> methodToMove(List<? extends CharSequence> list, TargetClass.TargetInnerRec... params) {static class StaticInner {int field;void process() {while (true) {if (field == 0) {continue;}this.field = 1;}}}new StaticInner().process();
default void helper() {SuperType.super.method();new Object() {void innerMethod() {}}.innerMethod();}helper();
Pattern pattern = Pattern.compile(".");List<String> result = new ArrayList<>();try {Method method = SourceInner.class.getMethod("methodToMove", TargetClass.TargetInnerRec[].class);method.invoke(this, (Object) params);} catch (Exception e) {}return result;}}
void method() {class LocalInner {}}}
interface SuperType {default void method() {}}