package test;
import java.util.ArrayList;import java.util.List;
public abstract class SourceClass permits SourceSubClass {public static int staticField = 5;
public class SourceInner {@SuppressWarnings("unused")private TargetClass process(String... args) {TargetClass target = new TargetClass();TargetClass.MemberInner targetInner = target.new MemberInner();
private class LocalType {int value = target.thisField;LocalType() {value += 1;}}LocalType local = new LocalType();
label: {if (args.length == 0) break label;targetInner.setValue(args[0]);}
List<String> list = new ArrayList<>();int i = 0;while (i < args.length) {list.add(callOverload(targetInner, args[i]));list.add(callOverload(args[i]));i++;}
Runnable anon = new Runnable() {@Overridepublic void run() {targetInner.printValue();}};anon.run();
targetInner.update(staticField);return target;}
default String callOverload(TargetClass.MemberInner inner, String s) {return inner.getValue() + s;}
default String callOverload(String s) {return s + staticField;}}}
class SourceSubClass extends SourceClass {}
public abstract class TargetClass {public int thisField = 10;
public class MemberInner {private String value;
public void setValue(String val) {this.value = val;}
public String getValue() {return value;}
public void printValue() {System.out.println(value);}
public void update(int num) {thisField += num;}}}