package test;
import java.util.List;import java.util.ArrayList;
interface MyInterface {}class ParentClass {List<String> getInnerData(TargetClass.InnerRec rec) {return new ArrayList<>(List.of(String.valueOf(rec.value)));}}
public class SourceClass<T> {private int outerPrivate = 20;static class StaticNested<T> {}
{new Runnable() {@Overridepublic void run() {}};}
public void handle(TargetClass.InnerRec... innerRecs) {TargetClass target = new TargetClass();for (TargetClass.InnerRec rec : innerRecs) {rec.value += outerPrivate;System.out.println(rec.value);}
try {if (innerRecs.length == 0) {throw new IllegalArgumentException(ParentClass.getInnerData(target.new InnerRec()).toString());}} catch (Exception e) {e.printStackTrace();}}}
public class TargetClass extends ParentClass implements MyInterface {class Inner {class InnerRec {int value;}}
InnerRec new InnerRec() {return new Inner().new InnerRec();}}