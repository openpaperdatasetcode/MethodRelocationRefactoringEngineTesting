package test;
import other.OtherClass;
interface ParentInterface {}
interface SourceInterface extends ParentInterface {class MemberInner {class RecursiveInner {protected void instanceMethod(TargetInterface target) {private int a = 0;private Object b = null;private String c = null;
labeled: {if (target.field1 == null) {throw new NullPointerException();}if (target.field2 == null) {break labeled;}new OtherClass() {void doSomething() {target.field3 = null;}};;}
OtherClass oc = new OtherClass();oc.process(target.field1, target.field2, target.field3);
target.staticField++;}}}
Runnable anonymous = new Runnable() {public void run() {}};}
default interface TargetInterface {String field1 = "f1";Integer field2 = 2;Object field3 = new Object();static int staticField = 0;}
package other;
public class OtherClass {void process(Object... args) {}}