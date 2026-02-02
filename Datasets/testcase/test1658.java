package same;
import java.util.List;import java.util.ArrayList;import java.util.Collection;
interface SourceInterface {static class StaticNested {public void method() {}}
class MemberInner {public void action() {}}
default List<String> methodToMove(TargetInterface target) throws Exception {List<String> result = new ArrayList<>();RawList raw = new RawList();Object obj = target;
obj.field; ;
try {Collection<String> coll = new ArrayList<>();coll.forEach(MemberInner::action);} catch (Exception e) {throw e;}
loop: for (int i = 0; i < 5; i++) {if (i == 3) {break loop;}result.add(target.getLocalInner().getValue());}
return result;}
default List<String> methodToMove(String s) {return new ArrayList<>();}
class RawList extends ArrayList {}}
sealed interface TargetInterface permits Implementation {default LocalInner getLocalInner() {class LocalInner {String getValue() {return "local";}}return new LocalInner();}
Object field = new Object();}
final class Implementation implements TargetInterface {}