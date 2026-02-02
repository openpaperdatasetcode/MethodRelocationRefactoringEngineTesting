package test;
import other.OtherClass;
interface BaseInterface {}
interface SourceInterface extends BaseInterface {static class StaticNested {SourceInterface.Inner inner;
StaticNested(TargetInterface.InnerRec target) {target.field1 = 1;target.field2 = 2;target.field3 = 3;
Object obj1 = (String) target;Object obj2 = (Number) target;
inner = new Inner();inner.call();}}
class Inner {void call() {}}
default void sample() {class LocalInner {void method(TargetInterface.InnerRec target) {OtherClass oc = new OtherClass();Object result = (target != null) ? oc.process(target) : null;}}}}
interface TargetInterface {class Inner {class InnerRec {public int field1;public int field2;public int field3;}}
default void action() {class TargetLocalInner {}}}
package other;
import test.TargetInterface;
public class OtherClass {void process(TargetInterface.InnerRec target) {}}