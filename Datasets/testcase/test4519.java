package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
interface SourceInterface {static class SourceStaticNested {int staticField = 5;}
default TargetInterface.TargetInner constructorMethod(TargetInterface targetParam) {class LocalInner {@MethodAnnoTargetInterface.TargetInner createInner(TargetInterface target) {RawType raw = new RawType();int var = target.targetField + SourceStaticNested.staticField + raw.getVal(target);return target.new TargetInner(var);}
@MethodAnnoTargetInterface.TargetInner createInner(TargetInterface target, int extra) {int var = target.targetField + extra;return target.new TargetInner(var);}}
LocalInner local = new LocalInner();return local.createInner(targetParam);}
class RawType {int getVal(TargetInterface target) {return target.targetField;}}}
public interface TargetInterface extends ParentInterface {int targetField = 3;
class TargetInner {int innerField;
TargetInner(int innerField) {this.innerField = innerField;}}
default void sampleMethod() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(targetField);}};runnable.run();}}
interface ParentInterface {}