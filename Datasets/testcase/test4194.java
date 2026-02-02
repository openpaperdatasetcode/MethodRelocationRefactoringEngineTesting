package test;
import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
interface TargetInterface {}
public class TargetClass implements TargetInterface {private String targetField;
public TargetClass(String field) {this.targetField = field;}
public void processLocal() {class TargetLocalInner {void printField() {System.out.println(targetField);}}new TargetLocalInner().printField();}
public String getTargetField() {return targetField;}}
abstract class SourceClass permits SourceClass.ConcreteSource {private TargetClass targetField = new TargetClass("init-value");
class SourceMemberInner {void helper(TargetClass target) {target.processLocal();}}
public void createLocalInner() {class SourceLocalInner {void useTarget(TargetClass target) {System.out.println(target.getTargetField());}}new SourceLocalInner().useTarget(targetField);}
@RefactorTestvoid instanceMethod(List<TargetClass> targetList) throws NoSuchMethodException, ReflectiveOperationException {TypeDecl typeDecl = new TypeDecl();SourceMemberInner inner = new SourceMemberInner();
for (TargetClass target : targetList) {inner.helper(target);String fieldVal = target.getTargetField();Method method = TargetClass.class.getMethod("getTargetField");String reflectedVal = (String) method.invoke(target);}}
static class ConcreteSource extends SourceClass {}
static class TypeDecl {}}
@interface RefactorTest {}