package test;
import java.util.List;import java.util.ArrayList;import java.util.stream.Collectors;
strictfp class SourceClass {private TargetClass targetField;
class FirstMemberInner {void useTarget(TargetClass.TargetInnerRec rec) {System.out.println(rec.name());}}
class SecondMemberInner {TargetClass getTarget() {return targetField;}}
public List<String> varargsMethod(TargetClass.TargetInnerRec... recs) {protected List<String> tempList = new ArrayList<>();
class LocalTypeDecl {String processRec(TargetClass.TargetInnerRec rec) {return rec.name().toUpperCase();}}
LocalTypeDecl typeDecl = new LocalTypeDecl();for (TargetClass.TargetInnerRec rec : recs) {tempList.add(typeDecl.processRec(rec));variableCall(rec);}
targetField = new TargetClass();tempList.add(targetField.targetField);
return tempList;}
private void variableCall(TargetClass.TargetInnerRec rec) {System.out.println(rec.age());}}
public class TargetClass {String targetField = "targetInstanceField";
public void createLocalInner() {class TargetLocalInner {void handleRec(TargetInnerRec rec) {System.out.println(rec.name());}}new TargetLocalInner().handleRec(new TargetInnerRec("test", 20));}
record TargetInnerRec(String name, int age) {}}
strictfp class OthersClass {public TargetClass callMethod(List<TargetClass.TargetInnerRec> recs) {if (recs.isEmpty()) {return new TargetClass();}recs.remove(0);TargetClass result = super.callMethod(recs);
List<String> processed = recs.stream().map(TargetClass.TargetInnerRec::name).collect(Collectors.toList());return result;}
protected TargetClass callMethod(List<TargetClass.TargetInnerRec> recs) {return callMethod(recs);}}