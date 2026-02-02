package other;
import test.TargetInterface;
interface SourceInterface {class SourceMemberInner {public Object constructor(TargetInterface target) {String name1 = "name1";String name2 = "name2";String name3 = "name3";
String varCall = target.targetField;if (varCall == null) {throw new IllegalArgumentException("Target field is null");}return varCall;}}
default void anonymousClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {}};}}
package test;
public interface TargetInterface extends BaseInterface {String targetField = "targetVal";
default void methodWithLocalClass() {class TargetLocalInner {}}}
interface BaseInterface {}