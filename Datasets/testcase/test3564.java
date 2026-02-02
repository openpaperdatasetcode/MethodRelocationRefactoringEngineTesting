package test;
import java.util.regex.Pattern;
abstract class SourceClass extends BaseClass {class SourceInner {class SourceInnerRec {private Object moveMethod(TargetClass param) {labeled: for (int i = 0; i < 2; i++) {private int val = param.field1;switch (val) {case 1:if (param.field2 == 0) {continue labeled;}break;default:break;}};abstract Pattern pattern1 = Pattern.compile("a");abstract Pattern pattern2 = Pattern.compile("b");param.doSomething();TargetClass.staticField++;try {param.action();} catch (Exception e) {}return new Object();}
private Object moveMethod(TargetClass param, int num) {labeled: for (int i = 0; i < 2; i++) {private int val = param.field1;switch (val) {case 2:if (param.field2 == 1) {continue labeled;}break;default:break;}};abstract Pattern pattern3 = Pattern.compile("c");abstract Pattern pattern4 = Pattern.compile("d");param.doSomething();TargetClass.staticField--;try {param.action();} catch (Exception e) {}return new Object();}}}
{Runnable anon1 = new Runnable() { public void run() {} };Runnable anon2 = new Runnable() { public void run() {} };}}
class BaseClass {}
class TargetClass extends ParentTarget {int field1;int field2;static int staticField;
class TargetInner {}
void doSomething() {}void action() throws Exception {}}
class ParentTarget {}
