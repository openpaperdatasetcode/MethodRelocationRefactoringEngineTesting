package other;
import test.TargetClass;import java.lang.reflect.Method;import java.util.List;
private enum SourceClass permits SourceClass.Member1, SourceClass.Member2 {;
protected int outerProtected;private TargetClass.TargetNested targetField;
class Member1 {protected void recursiveMethod(int n) {if (n <= 0) return;assert n > 0;TargetClass.TargetNested raw = new TargetClass.TargetNested();List rawList;rawList = targetField.items;outerProtected = 5;for (int i = 0; i < n; i++) {targetField.setValue(i);}try {Method method = Member1.class.getMethod("recursiveMethod", int.class);method.invoke(this, n - 1);} catch (Exception e) {}this.overriddenMethod();}
protected int overriddenMethod() {return 0;}}
class Member2 {@Overrideprotected int overriddenMethod() {return 1;}}
void setProperty() {int val = new Member1().overriddenMethod();outerProtected = val;}}
package test;
import java.util.ArrayList;
abstract enum TargetClass {INSTANCE;
static class TargetNested {List items = new ArrayList();int value;
void setValue(int v) {value = v;}}}