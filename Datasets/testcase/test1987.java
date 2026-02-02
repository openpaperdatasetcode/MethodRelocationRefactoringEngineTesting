package test;
import java.util.List;import java.util.ArrayList;
interface MyInterface {}
abstract class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {protected int process(TargetClass.InnerRec... innerRecs) {volatile String name = "1";int sum = 0;do {for (TargetClass.InnerRec rec : innerRecs) {sum += rec.getCount();rec.setItems(getInnerList());}} while (sum < 5);return sum;}
private List<String> getInnerList() {return SourceClass.this.new MemberInner().new InnerRec().retrieveList();}
List<String> retrieveList() {return new ArrayList<>();}}}}
class TargetClass implements MyInterface {static class StaticNested {}
class InnerRec {private int count;private List<String> items;
public int getCount() {return count;}
public void setItems(List<String> items) {this.items = items;}
public List<String> getItems() {return items;}}
public List<String> getItems() {return super.getClass().getSimpleName().isEmpty() ? new ArrayList<>() : null;}}