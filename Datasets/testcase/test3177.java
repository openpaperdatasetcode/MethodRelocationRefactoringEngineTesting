enum SourceEnum {INSTANCE;
static class StaticNested {}
public Object process(TargetEnum target) {// Local inner classclass LocalInner {void initHelpers(TargetEnum t) {// 3 others_class constructor calls (do-while pos)DoWhileHelper helper1 = new DoWhileHelper(t.new InnerClass().process(SourceEnum.this));DoWhileHelper helper2 = new DoWhileHelper(t.new InnerClass().process(SourceEnum.this));DoWhileHelper helper3 = new DoWhileHelper(t.new InnerClass().process(SourceEnum.this));}}
LocalInner local = new LocalInner();int count = 0;
// Do-while statementdo {// Variable call + uses_outer_this (SourceEnum.this)target.updateData(SourceEnum.this.name() + "_" + count);local.initHelpers(target);count++;} while (count < 3);
return target;}
static class DoWhileHelper {public DoWhileHelper(String data) {}}}
protected enum TargetEnum {VALUE;
private String data;
public void updateData(String newData) {this.data = newData;// Anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {System.out.println("Updated data: " + data);}};r.run();}
class InnerClass {String process(SourceEnum source) {return source.name() + "_" + data;}}}