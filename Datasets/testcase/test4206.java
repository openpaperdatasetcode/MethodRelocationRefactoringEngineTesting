package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorAnnotation {}
public record TargetClass(String data) {public class TargetMemberInner {private String innerData;
public TargetMemberInner(String innerData) {this.innerData = innerData;}
public String getInnerData() {return innerData;}
public void setInnerData(String innerData) {this.innerData = innerData;}}}
public record SourceClass(String sourceField) {@RefactorAnnotationprotected void setTargetData(TargetClass target, String value) {TargetClass.TargetMemberInner inner = new TargetClass.TargetMemberInner(target.data());
Runnable anon1 = new Runnable() {@Overridepublic void run() {variableCall(inner, value + "_anon1");}};
Runnable anon2 = new Runnable() {@Overridepublic void run() {synchronized (target) {inner.setInnerData(value + "_anon2");}}};
synchronized (target) {inner.setInnerData(value);}
anon1.run();anon2.run();}
private void variableCall(TargetClass.TargetMemberInner inner, String value) {inner.setInnerData(value);}}