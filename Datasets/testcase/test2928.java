import java.util.function.Supplier;
abstract class SourceClass {public void outerMethod(TargetClass target) throws Exception {class LocalInner {private TargetClass.InnerRec innerRec;
public LocalInner(TargetClass target) throws Exception {this.innerRec = target.new InnerRec(target::new,TargetClass.StaticField::getValue);}
protected void methodToMove() throws Exception {innerRec.invokeConstructors();System.out.println(TargetClass.STATIC_FIELD);target.anonymousAction();}}
Runnable anonymous = new Runnable() {@Overridepublic void run() {try {new LocalInner(target).methodToMove();} catch (Exception e) {}}};anonymous.run();}}
public class TargetClass {static final StaticField STATIC_FIELD = new StaticField();String targetField;
public TargetClass() {}public TargetClass(String field) { this.targetField = field; }
static class StaticField {String getValue() { return "staticValue"; }}
class InnerRec {private final Supplier<TargetClass> constructor1;private final Supplier<String> constructor2;
public InnerRec(Supplier<TargetClass> constructor1, Supplier<String> constructor2) {super();this.constructor1 = constructor1;this.constructor2 = constructor2;}
public void invokeConstructors() {TargetClass instance = constructor1.get();String value = constructor2.get();}}
void anonymousAction() {Runnable anonymous = new Runnable() {@Overridepublic void run() {targetField = "updatedByAnonymous";}};anonymous.run();}}