package same;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;import java.util.function.Consumer;import other.DiffPackageOthers;
public class Source {protected Target sourceTargetField = new Target();protected String outerProtectedField = "source_protected_val";
static class SourceStaticNested {public String nestedMethod(String param) {return "nested_" + param;}}
class SourceInner {class SourceInnerRec {@MethodAnnoList<String> varargsMethod(String... params) throws SQLException {private void privateSynchronizedInDiff() {DiffPackageOthers diffObj = new DiffPackageOthers();synchronized (this) {diffObj.setField1("val1");diffObj.setField2("val2");}}privateSynchronizedInDiff();
List<String> result = new ArrayList<>();SourceStaticNested staticNested = new SourceStaticNested();Target.TargetInner.TargetInnerRec targetInnerRec = sourceTargetField.new TargetInner().new TargetInnerRec();
try {for (int i = 0; i < params.length; i++) {result.add("loop_" + i + "_" + params[i]);}
for (String param : params) {String processed = this.processParam(param);result.add(processed);result.add(staticNested.nestedMethod(processed));}
Object var = sourceTargetField;result.add(targetInnerRec.innerRecMethod(outerProtectedField));result.add(sourceTargetField.targetInstanceMethod("instance_param"));} catch (Exception e) {throw new SQLException("Processing failed", e);}
return result;}
private String processParam(String param) {return "processed_" + param;}
private void callSourceMethod() {Consumer<String> consumer = param ->Source.this.new SourceInner().new SourceInnerRec().processParam(param);consumer.accept("functional_param");}}}
@Overridepublic boolean equals(Object obj) {return super.equals(obj);}}
abstract class Target extends TargetSuper {class TargetInner {class TargetInnerRec {public String innerRecMethod(String param) {return "target_inner_rec_" + param;}}}
public String targetInstanceMethod(String param) {return "target_instance_" + param;}}
class TargetSuper {}
package other;
public class DiffPackageOthers {private String field1;private String field2;
public void setField1(String field1) {this.field1 = field1;}
public void setField2(String field2) {this.field2 = field2;}
public String getField1() {return field1;}
public String getField2() {return field2;}}
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
package same;
import org.junit.Test;import java.sql.SQLException;import java.util.List;import static org.junit.Assert.*;
public class MoveMethodRefactoringTest {@Testpublic void testMethodBeforeMove() throws SQLException {Source source = new Source();Source.SourceInner sourceInner = source.new SourceInner();Source.SourceInner.SourceInnerRec innerRec = sourceInner.new SourceInnerRec();
String[] params = {"test1", "test2"};List<String> result = innerRec.varargsMethod(params);
assertNotNull(result);assertTrue(result.contains("loop_0_test1"));assertTrue(result.contains("processed_test2"));assertTrue(result.contains("target_inner_rec_source_protected_val"));}
@Test(expected = SQLException.class)public void testMethodBeforeMoveWithException() throws SQLException {Source source = new Source();Source.SourceInner sourceInner = source.new SourceInner();Source.SourceInner.SourceInnerRec innerRec = sourceInner.new SourceInnerRec();
innerRec.varargsMethod((String[]) null);}
@Testpublic void testMethodAfterMove() throws SQLException {Target target = new Target() {};Target.TargetInner.TargetInnerRec movedRec = target.new TargetInner().new TargetInnerRec();
String[] params = {"test1", "test2"};List<String> result = ((MovedMethod) movedRec).varargsMethod(params, target);
assertNotNull(result);assertTrue(result.contains("loop_0_test1"));assertTrue(result.contains("processed_test2"));assertTrue(result.contains("target_inner_rec_source_protected_val"));}
private interface MovedMethod {List<String> varargsMethod(String[] params, Target target) throws SQLException;}}