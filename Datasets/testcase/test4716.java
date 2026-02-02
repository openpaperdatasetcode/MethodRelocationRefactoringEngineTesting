package source;
import target.TargetClass;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.List;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessAnnotation {}
public class SourceClass {private TargetClass targetField = new TargetClass();
@ProcessAnnotationpublic void handleTarget(int type) {try {TargetClass.TargetInner inner = targetField.new TargetInner();TargetClass result = inner.process();
switch (type) {case 1:inner.updateValue("type1");break;case 2:inner.updateValue("type2");break;default:inner.updateValue("default");}
String fieldVal = inner.value;} catch (Exception e) {// No new exception}}
class SourceInner extends TargetClass.TargetInner {@Overridepublic TargetClass process() {return super.process();}}
void useLambda() {class LocalInner {void execute() {Supplier<List<String>> lambda = () -> new TargetClass.TargetInner().getItems();List<String> items = lambda.get();}}new LocalInner().execute();}}
package target;
import java.util.ArrayList;import java.util.List;
public class TargetClass {public class TargetInner {protected String value;
public TargetClass process() {return TargetClass.this;}
public void updateValue(String val) {this.value = val;}
public List<String> getItems() {return new ArrayList<>();}}}