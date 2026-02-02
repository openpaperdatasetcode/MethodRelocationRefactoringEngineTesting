package test;
import java.util.ArrayList;import java.util.List;
class SourceClass {static class FirstStaticNested {class SourceInner {strictfp List<String> process(TargetClass target) {List<String> result = new ArrayList<>();target.addItem("item1");result.add(target.getItem());return result;}}}
static class SecondStaticNested {void useProcess() {TargetClass target = new TargetClass();List<String> list = new FirstStaticNested.SourceInner().process(target);}}}
class TargetClass {private String item;
void addItem(String item) {this.item = item;}
String getItem() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(item);}};runnable.run();return item;}}