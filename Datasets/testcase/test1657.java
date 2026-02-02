package same;
import java.io.IOException;import java.util.ArrayList;import java.util.List;import java.util.Collection;
public class SourceClass {public class MemberInner1 {public class NestedInner {private String field = "nested";
List<String> methodToMove(TargetClass... targets) throws IOException {List<String> list = new ArrayList<>();RawList rawList = new RawList();
for (TargetClass target : targets) {list.add(target.staticNested.getValue());rawList.add(this.field);}
Collection<String> coll = new ArrayList<>();coll.forEach(s -> {ParentClass parent = new ParentClass();parent.protectedMethod(new SourceClass.MemberInner2());});
return list;}}}
public class MemberInner2 {}
class RawList extends ArrayList {}}
package same;
protected class TargetClass {protected static class StaticNested {public String getValue() {return "static";}}}
package same;
public class ParentClass {protected TargetClass protectedMethod(SourceClass.MemberInner2 inner) {return new TargetClass();}}