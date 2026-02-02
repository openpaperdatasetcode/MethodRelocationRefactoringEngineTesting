package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
record SourceClass(String baseData) {class FirstMemberInner {void processRec(TargetClass.TargetInnerRec<String> rec) {System.out.println(rec.data());}}
class SecondMemberInner {TargetClass.TargetInnerRec<String> getDefaultRec() {return new TargetClass.TargetInnerRec<>("default");}}
private List<String> recursiveMethod(TargetClass.TargetInnerRec<String>... recs) {List<String> result = new ArrayList<>();assert recs != null : "Recs array cannot be null";
label: {if (recs.length == 0) break label;
class LocalTypeDecl {String extractData(TargetClass.TargetInnerRec<String> rec) {return rec.data();}}LocalTypeDecl typeDecl = new LocalTypeDecl();
String firstData = recs[0].data();String secondData = recs[1].data();result.add(firstData);result.add(secondData);
variableCall(recs[0]);
ArrayList rawList = new ArrayList();rawList.add(TargetClass.STATIC_FIELD);
if (recs.length > 2) {TargetClass.TargetInnerRec<String>[] subRecs = new TargetClass.TargetInnerRec[recs.length - 1];System.arraycopy(recs, 1, subRecs, 0, subRecs.length);result.addAll(recursiveMethod(subRecs));}}
try {Method method = SourceClass.class.getDeclaredMethod("recursiveMethod", TargetClass.TargetInnerRec[].class);method.setAccessible(true);method.invoke(this, (Object) recs);} catch (Exception e) {e.printStackTrace();}
return result;}
private void variableCall(TargetClass.TargetInnerRec<String> rec) {System.out.println(rec.data());}}
private record TargetClass<T>(T value) extends ParentClass<T> {static final String STATIC_FIELD = "targetStaticValue";
record TargetInnerRec<T>(T data) {}
public void createLocalInner() {class TargetLocalInner {<T> void handleRec(TargetInnerRec<T> rec) {System.out.println(rec.data());}}new TargetLocalInner().handleRec(new TargetInnerRec<>("localData"));}}
class ParentClass<T> {T parentValue;}