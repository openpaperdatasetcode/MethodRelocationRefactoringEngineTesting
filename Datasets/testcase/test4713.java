package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
interface SourceInterface extends SuperInterface {class SourceInner {record SourceInnerRec(String id) {@ProcessAnnotationprotected TargetInterface.TargetInner process(TargetInterface.TargetInner target, String... args) {for (int i = 0; i < args.length; i++) {target.setValue(args[i]);String current = target.getValue();}return target;}}}
static class StaticNested {void useProcess() {TargetInterface.TargetInner target = new TargetInterface.TargetInner();SourceInnerRec innerRec = new SourceInner.SourceInnerRec("proc-001");TargetInterface.TargetInner result = innerRec.process(target, "arg1", "arg2");}}}
interface SuperInterface {default void baseMethod() {}}
final interface TargetInterface {class TargetInner {private String value;
public void setValue(String value) {this.value = value;}
public String getValue() {return value;}}}