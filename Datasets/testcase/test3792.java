import java.lang.reflect.Constructor;import java.util.ArrayList;import java.util.List;
public class TargetClass {
public String field1;public String field2;public String field3;
public void setFields (String f1, String f2, String f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3;}
public List<String> getFieldList() {List<String> fields = new ArrayList<>();fields.add(field1);fields.add(field2);fields.add(field3);return fields;}
public List<String> processWithLocalInner() {class LocalFieldProcessor {List<String> process() {List<String> result = new ArrayList<>();result.add("Processed: " + field1);result.add("Processed: " + field2);result.add("Processed: " + field3);return result;}}return new LocalFieldProcessor().process();}}
// SourceClass: normal, package-private, same package as target, with 2 static nested classes (meets source_class specs)class SourceClass {
public static class FirstStaticNested {
public static class SecondStaticNested {private List<String> resultList;
private SecondStaticNested (TargetClass target) {
 this.fieldthis.resultList = new ArrayList<>();
target.field1 = "init_field1";target.field2 = "init_field2";target.field3 = "init_field3";
List<String> tempFields = new ArrayList<>();
tempFields.addAll (target.getFieldList ());
List<String> processed = target.processWithLocalInner();this.resultList.addAll(processed);}
public List<String> getResult() {return resultList;}}
public static List<String> createNestedInstance (TargetClass target) {try {
Constructor<SecondStaticNested> constructor = SecondStaticNested.class.getDeclaredConstructor(TargetClass.class);constructor.setAccessible(true);SecondStaticNested instance = constructor.newInstance(target);return instance.getResult(); // no_new_exception} catch (Exception e) {throw new RuntimeException(e);}}}
public static List<String> testTargetProcessing(TargetClass target) {return FirstStaticNested.createNestedInstance(target);}}

import org.junit.Test;import static org.junit.Assert.*;
public class SourceClassTest {@Testpublic void testConstructorWithTargetParameter () {TargetClass target = new TargetClass ();
List<String> result = SourceClass.testTargetProcessing(target);
assertNotNull ("Result should not be null", result);assertEquals ("Result size should be 3", 3, result.size ());assertTrue ("Result should contain processed field1", result.contains ("Processed: init_field1"));assertTrue ("Result should contain processed field2", result.contains ("Processed: init_field2"));assertTrue ("Result should contain processed field3", result.contains ("Processed: init_field3"));}}