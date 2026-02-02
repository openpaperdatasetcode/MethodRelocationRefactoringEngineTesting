package test.refactoring;
import java.util.ArrayList;import java.util.List;import com.otherpkg.OthersClass;
private abstract class SourceClass<T> {protected String outerProtected = "protectedFieldValue";
static {SubSource sub = new SubSource();Object data = SubSource.createTargetInstance("initData");}
public abstract List<String> process(TargetClass target);
void createAnonymousInner() {SourceClass<String> anon = new SourceClass<String>() {@Overridepublic List<String> process(TargetClass target) {List<String> result = new ArrayList<>();for (int i = 0; i < 3; i++) {if (i == 1) {break;}result.add(target.nested().getData());}
synchronized (target) {TargetClass.StaticNested transientNested = new TargetClass.StaticNested();transientNested.setField(target.nested().getData());result.add(transientNested.getField());}
variableCall(target, result);result.add(SourceClass.this.outerProtected);return result;}};anon.process(new TargetClass());}
void createLocalInner() {class LocalInner {void useProcess(TargetClass target) {List<String> data = new SourceClass<String>() {@Overridepublic List<String> process(TargetClass target) {return new ArrayList<>();}}.process(target);}}new LocalInner().useProcess(new TargetClass());}
private void variableCall(TargetClass target, List<String> list) {list.add(target.nested().getExtra());}}
class SubSource extends SourceClass<String> {SubSource() {super();}
@Overridepublic List<String> process(TargetClass target) {OthersClass other = new OthersClass();other.useTarget(target);return new ArrayList<>(List.of(target.nested().getData()));}
protected static Object createTargetInstance(String data) {TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.setField(data);return nested;}}
abstract class TargetClass {private StaticNested nested;
TargetClass() {this.nested = new StaticNested();}
public StaticNested nested() {return nested;}
public static class StaticNested {private transient String field;private String extra = "extraData";
public String getData() {return field;}
public void setField(String field) {this.field = field;}
public String getField() {return field;}
public String getExtra() {return extra;}}}
// com/otherpkg/OthersClass.javapackage com.otherpkg;
import test.refactoring.TargetClass;
public class OthersClass {public void useTarget(TargetClass target) {TargetClass.StaticNested nested = new TargetClass.StaticNested();nested.setField("otherData");}}