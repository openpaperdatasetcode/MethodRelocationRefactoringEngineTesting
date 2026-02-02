package com.source;
import com.target.TargetClass;import java.util.ArrayList;import java.util.List;
public class SourceClass {protected String outerProtected = "protectedValue";
class MemberInner {List<String> process(TargetClass target) {TargetClass.MemberInner targetInner = target.new MemberInner();List rawList = new ArrayList();rawList.add(targetInner.getData());
variableCall(targetInner, rawList);rawList.add(SourceClass.this.outerProtected);
return rawList;}
private void variableCall(TargetClass.MemberInner inner, List list) {list.addAll(inner.getItems());}}
void useLocalInner() {class LocalInner {void execute(TargetClass target) {List<String> result = new MemberInner().process(target);System.out.println(result);}}
TargetClass target = new TargetClass();new LocalInner().execute(target);}
List<String> callMethod(int flag, TargetClass target) {return (flag > 0) ?new MemberInner().process(target) :new ArrayList<>();}}
package com.target;
import java.util.List;import java.util.ArrayList;
class TargetClass {class MemberInner {String getData() {return "targetData";}
List<String> getItems() {return new ArrayList<>(List.of("item1", "item2"));}}}