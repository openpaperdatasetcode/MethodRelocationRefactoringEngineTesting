package same.pkg;
import java.util.ArrayList;import java.util.Collection;import java.io.IOException;
public class SourceClass implements Runnable permits SubSource {protected int outerProtectedField;private TargetClass targetField = new TargetClass();
public class SourceInner {void process() {}
void process(int value) {do {for (int i = 0; i < 5; i++) {try {Collection<Integer> coll = new ArrayList<>();coll.add(new ParentClass(SourceClass.this.outerProtectedField).getId());TargetClass.MemberInner inner = targetField.new MemberInner();inner.setValue(10);} catch (IOException e) {e.printStackTrace();}}} while (outerProtectedField < 10);}}
public void run() {class LocalInner {void useLambda() {Runnable r = () -> {String str = ((ParentInterface) new TargetClass()).getName();};}}}}
class SubSource extends SourceClass {}
/**
Represents a target class with member inner class*/public class TargetClass extends ParentClass {private String name;
public TargetClass() {super(0);}
class MemberInner {private int val;
public void setValue(int val) {this.val = val;}}}
class ParentClass {private int id;
public ParentClass(int id) {this.id = id;}
int getId() {return id;}}
interface ParentInterface {default String getName() {return "";}}