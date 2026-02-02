package test;
import java.util.List;import java.util.ArrayList;import java.sql.SQLException;
final class SourceClass {// Member inner class (source_feature)protected class SourceMemberInner {public SourceMemberInner() {this("default"); // this(arguments)}
public SourceMemberInner(String val) {}}
/**
Method Javadoc for varargs method testing Move Method refactoring
@param targets Varargs of TargetClass instances
@return Processed object array*/protected Object methodToMove(TargetClass... targets) {super(); // Super constructor invocationList<Object> results = new ArrayList<>();SourceMemberInner inner = new SourceMemberInner();
for (TargetClass target : targets) {// Constructor invocationTargetClass.Inner.InnerRec rec = target.new Inner().new InnerRec();// Variable call + contains target parameter (per_condition)target.toString();
// DoStatement with super.field (count 1, pos: diff_package_others)private String superFieldVal;do {superFieldVal = target.superField;} while (superFieldVal == null);
// Instance code blocks with source static method call (obj.m1().m2().m3()){List<String> staticResult1 = SourceStaticUtil.method1(target).process().getResult();List<String> staticResult2 = SourceStaticUtil.method2(target).process().getResult();results.add(staticResult1);results.add(staticResult2);}
// SQLException handling (no_new_exception)try {if (superFieldVal.isEmpty()) throw new SQLException("Super field is empty");rec.setField(superFieldVal);results.add(rec.getField());} catch (SQLException e) {results.add("error_default");}
// Local inner class (source_feature)class SourceLocalInner {public void addToResults() {results.add(rec);}}new SourceLocalInner().addToResults();}
return results.toArray();}
// Source static utility class (for static method features)public static class SourceStaticUtil {public static ProcessStep method1(TargetClass target) {return new ProcessStep(target.superField);}
public static ProcessStep method2(TargetClass target) {return new ProcessStep(target.new Inner().new InnerRec().getField());}
public static class ProcessStep {private String data;
public ProcessStep(String data) {this.data = data;}
public ProcessStep process() {this.data = data.toUpperCase();return this;}
public List<String> getResult() {List<String> list = new ArrayList<>();list.add(data);return list;}}}}
final class TargetClass extends ParentTargetClass {public String superField = "targetSuperField"; // Super.field (target_feature: extends)
// Member inner class (target_feature)public class Inner {public class InnerRec {private String field;
public String getField() {return field;}
public void setField(String field) {this.field = field;}}}}
class ParentTargetClass {}