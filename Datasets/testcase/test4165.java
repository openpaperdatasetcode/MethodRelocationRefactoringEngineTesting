package test;
import java.util.List;import java.util.ArrayList;
interface MarkerInterface {}
abstract class SourceClass {private String outerPrivateField = "source_private_data";
public class FirstMemberInner {}public class SecondMemberInner {void handleTarget(TargetClass target) {TargetClass.StaticNested<String> genericNested = new TargetClass.StaticNested<>();List<String> resultList = genericNested.processGeneric("param1", "param2", "param3");
for (String item : resultList) {if (item.equals(super.toString())) {continue;}
if (target.superField == 1) {System.out.println(SourceClass.this.outerPrivateField);target.executeMethod();}}
FirstMemberInner innerInstance = new FirstMemberInner();}}}
abstract class TargetClass implements MarkerInterface {int superField = 1;
public abstract void executeMethod();
public static class StaticNested<T> {private List<T> dataList = new ArrayList<>();
public List<String> processGeneric(T... params) {List<String> stringList = new ArrayList<>();for (T param : params) {stringList.add(param.toString());}return stringList;}}}